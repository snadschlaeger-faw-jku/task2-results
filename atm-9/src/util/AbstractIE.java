package util;

import ATM.Account;
import domain.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractIE {

	protected void checkRules() throws SQLException, ClassNotFoundException {
		List<Rule> rules = getAllRules();
		for (Rule rule : rules) {
			if (checkRuleConditions(getRuleOperator(rule), getRuleConditions(rule))) {
				executeConsequences(getRuleConsequences(rule));
			}
		}
	}

	protected abstract List<domain.Rule> getAllRules();

	protected abstract RuleOperator getRuleOperator(Object rule);

	protected abstract List<Condition> getRuleConditions(Object rule);

	protected abstract List<domain.Action> getRuleConsequences(Object rule);

	protected abstract String getConditionExpression(Object condition);

	protected abstract String getConsequenceExpression(Object consequence);

	/**
	 * The returned parameter can be a fact object, or a String.
	 * 
	 * @param consequence
	 * @return
	 */
	protected abstract Object getConsequenceParameter(Object consequence);

	protected abstract Object getConditionParameter(Object condition);

	private void executeConsequences(List<Action> consequences) {
		clearRuntimeFactStorage();
		for (Action consequence : consequences) {
			if (getConsequenceExpression(consequence).equals(ClauseOperators.print.name())) {
				System.out.println(getConsequenceParameter(consequence));
			} else if (getConsequenceExpression(consequence).equals(ClauseOperators.assertFact.name())) {
				Fact fact = (Fact) getConsequenceParameter(consequence);
				evaluateSlots(fact);
				assertFact(fact);
			}
		}
	}

	/**
	 * Completely clean runtime storage.
	 */
	protected abstract void clearRuntimeFactStorage();

	protected abstract List<domain.Fact> getAllFactsInRuntimeStorage() throws SQLException, ClassNotFoundException;

	/**
	 * Put new fact to runtime storage.
	 * 
	 * @param fact
	 */
	protected abstract void assertFact(Fact fact);

	private void evaluateSlots(Object fact) {
		for (Object property : getFactProperties(fact)) {
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

	protected abstract List<Property> getFactProperties(Object fact);

	protected abstract String getFactName(Object fact);

	protected abstract boolean propertyRequiresInputFromUser(Object property);

	/**
	 * This method MUST return a valid fully qualified java class name!
	 * 
	 * @param property
	 * @return
	 */
	protected abstract String getFactPropertyType(Object property);

	protected abstract void setFactPropertyValue(Object property, Object value);

	protected abstract String getFactPropertyName(Object property);

	protected abstract void setConditionParameter(Condition clause, Fact parameter);

	private boolean checkRuleConditions(RuleOperator operator, List<Condition> conditions) throws SQLException, ClassNotFoundException {
		boolean conditionMet = true;
		List<Fact> retreivedFacts = new ArrayList<>(); //getfacts?
		for (Condition clause : conditions) {
			conditionMet = conditionMet & evaluateClause(clause, retreivedFacts);
		}
		return conditionMet;
	}

	private boolean evaluateClause(Condition clause, List<Fact> retreivedFacts) throws SQLException, ClassNotFoundException {
		if (getConditionExpression(clause).equals(ClauseOperators.exists.name())) {
			if (getConditionParameter(clause) == null) {
				setConditionParameter(clause, retreivedFacts.get(0));
			}
			return exists(getConditionParameter(clause));
		} else if (getConditionExpression(clause).equals(ClauseOperators.take.name())) {
			Fact fact = getFirstFact(getAllFactsInRuntimeStorage(), getConditionParameter(clause).toString());
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

	protected abstract List<Fact> getAllFacts() throws SQLException;

	protected abstract int getNrOfFactProperties(Object fact);

	protected abstract Object getFactPropertyOnPosition(Object fact, int position);

	protected abstract boolean isFactPropertyMandatory(Object property);

	protected abstract Object getFactPropertyValue(Object property);

	private boolean exists(Object factToCheck) throws SQLException, ClassNotFoundException {
		List<Fact> facts = getAllFacts();
		for (Fact fact : facts) {
			boolean factExists = true;

			factExists = factExists && getFactName(fact).equals(getFactName(factToCheck));
			factExists = factExists && (getNrOfFactProperties(fact) == getNrOfFactProperties(factToCheck));

			for (int i = 0; i < getNrOfFactProperties(fact); i++) {
				Object slot = getFactPropertyOnPosition(fact, i);
				if (isFactPropertyMandatory(slot)) {
					Object slotToCheck = getFactPropertyOnPosition(factToCheck, i);

					factExists = factExists && getFactPropertyName(slot).equals(getFactPropertyName(slotToCheck));
					factExists = factExists && getFactPropertyType(slot).equals(getFactPropertyType(slotToCheck));
					factExists = factExists && getFactPropertyValue(slot).equals(getFactPropertyValue(slotToCheck));
				}
			}

			if (factExists) {
				return true;
			}
		}

		return false;
	}

}
