package net.nadschlaeger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import net.nadschlaeger.model.Consequence;
import net.nadschlaeger.model.Clause;
import net.nadschlaeger.model.Condition;
import net.nadschlaeger.model.Fact;
import net.nadschlaeger.model.Rule;
import net.nadschlaeger.model.Slot;

public abstract class AbstractIE {

	/**
	 * Put new fact to runtime storage.
	 *
	 * @param fact
	 */
	protected abstract void assertFact(Fact fact);

	private boolean checkRuleConditions(RuleOperator operator, List<Condition> conditions) {
		// System.out.println("checkRuleConditions " + operator);
		boolean conditionMet = true;
		final List<Fact> retreivedFacts = new ArrayList<>();
		switch (operator) {
		case OR:
			conditionMet = evaluateClause(conditions.get(0), retreivedFacts);
			break;
		case AND:
			for (final Clause clause : conditions) {
				conditionMet = conditionMet & evaluateClause(clause, retreivedFacts);
			}
			break;
		default:
			break;
		}

		return conditionMet;
	}

	protected void checkRules() {
		// System.out.println("checkRules");
		final List<Rule> rules = getAllRules();
		// System.out.println(rules);
		for (final Rule rule : rules) {
			// System.out.println(rule);
			if (checkRuleConditions(getRuleOperator(rule), getRuleConditions(rule))) {
				executeConsequences(getRuleConsequences(rule));
			}
		}
	}

	/**
	 * Completely clean runtime storage.
	 */
	protected abstract void clearRuntimeFactStorage();

	private boolean evaluateClause(Clause clause, List<Fact> retreivedFacts) {
		// System.out.println("evaluateClause " + clause);
		if (getConditionExpression(clause) == ClauseOperators.exists) {
			if (getConditionParameter(clause) == null) {
				// System.out.println("with retreived");
				setConditionParameter(clause, retreivedFacts.get(0));
			}
			return exists(getConditionParameter(clause));
		} else if (getConditionExpression(clause) == ClauseOperators.take) {
			final Fact fact = getFirstFact(getAllFactsInRuntimeStorage(),
					getConditionParameter(clause).getName().toString());
			if (fact != null) {
				retreivedFacts.add(fact);
				return true;
			}
		}

		return false;
	}

	private void evaluateSlots(Fact fact) {
		for (final Slot property : getFactProperties(fact)) {
			if (propertyRequiresInputFromUser(property)) {
				try {
					System.out.println(property.getText());
					final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					final String s = br.readLine();
					final String factPropertyType = getFactPropertyType(property);
					final Class<?> forName = Class.forName(factPropertyType);
					final Constructor<?> ctor = forName.getConstructor(String.class);
					setFactPropertyValue(property, ctor.newInstance(s));
				} catch (final Exception ex) {
					ex.printStackTrace();
				}

			}
		}
	}

	private void executeConsequences(List<Consequence> consequences) {
		clearRuntimeFactStorage();
		for (final Clause consequence : consequences) {
			if (getConsequenceExpression(consequence) == ClauseOperators.print) {
				System.out.println(getConsequenceParameter(consequence));
			} else if (getConsequenceExpression(consequence) == ClauseOperators.assertFact) {
				final Fact fact = getConsequenceParameter(consequence);
				evaluateSlots(fact);
				assertFact(fact);
			}
		}
	}

	private boolean exists(Fact factToCheck) {
		// System.out.println("exists " + factToCheck);
		final List<Fact> facts = getAllFacts();
		for (final Fact fact : facts) {
			boolean factExists = true;

			factExists = factExists && getFactName(fact).equals(getFactName(factToCheck));
			factExists = factExists && getNrOfFactProperties(fact) == getNrOfFactProperties(factToCheck);
			if(factExists) {
				for (int i = 0; i < getNrOfFactProperties(fact); i++) {
					final Slot slot = getFactPropertyOnPosition(fact, i);
					if (isFactPropertyMandatory(slot)) {
						final Slot slotToCheck = getFactPropertyOnPosition(factToCheck, i);

						factExists = factExists && getFactPropertyName(slot).equals(getFactPropertyName(slotToCheck));
						factExists = factExists && getFactPropertyType(slot).equals(getFactPropertyType(slotToCheck));
						factExists = factExists && getFactPropertyValue(slot).equals(getFactPropertyValue(slotToCheck));
					}
				}
			}

			if (factExists) {
				return true;
			}
		}

		return false;
	}

	protected abstract List<Fact> getAllFacts();

	protected abstract List<Fact> getAllFactsInRuntimeStorage();

	protected abstract List<Rule> getAllRules();

	protected abstract ClauseOperators getConditionExpression(Clause condition);

	protected abstract Fact getConditionParameter(Clause condition);

	protected abstract ClauseOperators getConsequenceExpression(Clause consequence);

	/**
	 * The returned parameter can be a fact object, or a String.
	 *
	 * @param consequence
	 * @return
	 */
	protected abstract Fact getConsequenceParameter(Clause consequence);

	protected abstract String getFactName(Fact fact);

	protected abstract List<Slot> getFactProperties(Fact fact);

	protected abstract String getFactPropertyName(Slot property);

	protected abstract Slot getFactPropertyOnPosition(Fact fact, int position);

	/**
	 * This method MUST return a valid fully qualified java class name!
	 *
	 * @param property
	 * @return
	 */
	protected abstract String getFactPropertyType(Slot property);

	protected abstract Object getFactPropertyValue(Slot property);

	private Fact getFirstFact(List<Fact> inferredFacts, String factName) {
		Fact fact = null;
		int i = 0;
		while (fact == null && i < inferredFacts.size()) {
			if (getFactName(inferredFacts.get(i)).equals(factName)) {
				fact = inferredFacts.get(i);
			}
			i++;
		}
		return fact;
	}

	protected abstract int getNrOfFactProperties(Fact fact);

	protected abstract List<Condition> getRuleConditions(Rule rule);

	protected abstract List<Consequence> getRuleConsequences(Rule rule);

	protected abstract RuleOperator getRuleOperator(Rule rule);

	protected abstract boolean isFactPropertyMandatory(Slot property);

	protected abstract boolean propertyRequiresInputFromUser(Slot property);

	protected abstract void setConditionParameter(Clause condition, Fact parameter);

	protected abstract void setFactPropertyValue(Slot property, Object value);

}
