package net.snadschlaeger.inference;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import net.snadschlaeger.inference.model.Clause;
import net.snadschlaeger.inference.model.Fact;
import net.snadschlaeger.inference.model.Rule;
import net.snadschlaeger.inference.model.Slot;

public abstract class InferenceEngine {

	/**
	 * Trigger the inference engine with an initial {@link Fact}.
	 * 
	 * @param startFact
	 */
	public void trigger(Fact startFact) {
		assertFact(startFact);
		checkRules();
	}

	protected void checkRules() {
		List<Rule> rules = getAllRules();
		for (Rule rule : rules) {
			if (checkRuleConditions(getRuleOperator(rule), getRuleConditions(rule))) {
				executeConsequences(getRuleConsequences(rule));
			}
		}
	}

	protected abstract List<Rule> getAllRules();

	protected abstract RuleOperator getRuleOperator(Rule rule);

	protected abstract List<Clause> getRuleConditions(Rule rule);

	protected abstract List<Clause> getRuleConsequences(Rule rule);

	protected abstract String getConditionExpression(Clause condition);

	protected abstract String getConsequenceExpression(Clause consequence);

	/**
	 * The returned parameter can be a fact object, or a String.
	 * 
	 * @param consequence
	 * @return
	 */
	protected abstract Fact getConsequenceParameter(Clause consequence);

	protected abstract Fact getConditionParameter(Clause condition);

	private void executeConsequences(List<Clause> consequences) {
		clearRuntimeFactStorage();
		for (Clause consequence : consequences) {
			if (getConsequenceExpression(consequence).equals(ClauseOperator.print.name())) {
				System.out.println(getConsequenceParameter(consequence));
			} else if (getConsequenceExpression(consequence).equals(ClauseOperator.assertFact.name())) {
				Fact fact = getConsequenceParameter(consequence);
				evaluateSlots(fact);
				assertFact(fact);
			}
		}
	}

	/**
	 * Completely clean runtime storage.
	 */
	protected abstract void clearRuntimeFactStorage();

	protected abstract List<Fact> getAllFactsInRuntimeStorage();

	/**
	 * Put new fact to runtime storage.
	 * 
	 * @param fact
	 */
	protected abstract void assertFact(Fact fact);

	private void evaluateSlots(Fact fact) {
		for (Slot property : getFactProperties(fact)) {
			if (propertyRequiresInputFromUser(property)) {
				try {
					System.out.println("Please enter " + getFactPropertyName(property) + ": ");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String s = br.readLine();
					Constructor<?> ctor = Class.forName(getFactPropertyType(property)).getConstructor(String.class);
					setFactPropertyValue(property, ctor.newInstance(s));
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}
	}

	protected abstract List<Slot> getFactProperties(Fact fact);

	protected abstract String getFactName(Fact fact);

	protected abstract boolean propertyRequiresInputFromUser(Slot property);

	/**
	 * This method MUST return a valid fully qualified java class name!
	 * 
	 * @param property
	 * @return
	 */
	protected abstract String getFactPropertyType(Slot property);

	protected abstract void setFactPropertyValue(Slot property, Object value);

	protected abstract String getFactPropertyName(Slot property);

	protected abstract void setConditionParameter(Clause condition, Fact parameter);

	private boolean checkRuleConditions(RuleOperator operator, List<Clause> conditions) {
		boolean conditionMet = true;
		List<Fact> retreivedFacts = new ArrayList<>();
		for (Clause clause : conditions) {
			conditionMet = conditionMet & evaluateClause(clause, retreivedFacts);
		}
		return conditionMet;
	}

	private boolean evaluateClause(Clause clause, List<Fact> retreivedFacts) {
		if (getConditionExpression(clause).equals(ClauseOperator.exists.name())) {
			if (getConditionParameter(clause) == null) {
				setConditionParameter(clause, retreivedFacts.get(0));
			}
			return exists(getConditionParameter(clause));
		} else if (getConditionExpression(clause).equals(ClauseOperator.take.name())) {
			Fact fact = getFirstFact(getAllFactsInRuntimeStorage(), getConditionParameter(clause).getName());
			if (fact != null) {
				retreivedFacts.add(fact);
				return true;
			}
		}

		return false;
	}

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

	protected abstract List<Fact> getAllFacts();

	protected abstract int getNrOfFactProperties(Fact fact);

	protected abstract Slot getFactPropertyOnPosition(Fact fact, int position);

	protected abstract boolean isFactPropertyMandatory(Slot property);

	protected abstract Object getFactPropertyValue(Slot property);

	private boolean exists(Fact factToCheck) {
		List<Fact> facts = getAllFacts();
		for (Fact fact : facts) {
			boolean factExists = true;

			factExists = factExists && getFactName(fact).equals(getFactName(factToCheck));
			factExists = factExists && (getNrOfFactProperties(fact) == getNrOfFactProperties(factToCheck));

			if (factExists) {
				for (int i = 0; i < getNrOfFactProperties(fact); i++) {
					Slot slot = getFactPropertyOnPosition(fact, i);
					if (isFactPropertyMandatory(slot)) {
						Slot slotToCheck = getFactPropertyOnPosition(factToCheck, i);

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

}
