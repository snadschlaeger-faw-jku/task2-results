package main.java;

import java.util.ArrayList;
import java.util.List;

import main.java.model.Clause;
import main.java.model.Fact;
import main.java.model.Rule;
import main.java.model.Slot;

/**
 * Created by snadschlaeger on 28.12.2016.
 */
public class BruteForceIE extends AbstractIE {

	@Override
	protected void assertFact(Fact fact) {
		WorkingMemory.assertFact(fact);
	}

	@Override
	protected void clearRuntimeFactStorage() {
		WorkingMemory.inferredFacts.clear();
	}

	@Override
	protected List<Fact> getAllFacts() {
		List<Fact> ret = new ArrayList<>();
		ret.addAll(WorkingMemory.globalFacts);
		ret.addAll(WorkingMemory.inferredFacts);
		ret.addAll(KnowledgeBase.facts);
		return ret;
	}

	@Override
	protected List<Fact> getAllFactsInRuntimeStorage() {
		return WorkingMemory.inferredFacts;
	}

	@Override
	protected List<Rule> getAllRules() {
		List<Rule> ret = new ArrayList<>();
		ret.addAll(KnowledgeBase.rules);
		return ret;
	}

	@Override
	protected String getConditionExpression(Clause condition) {
		return condition.expression.name();
	}

	@Override
	protected Object getConditionParameter(Clause condition) {
		return condition.parameter;
	}

	@Override
	protected String getConsequenceExpression(Clause consequence) {
		return consequence.expression.name();
	}

	@Override
	protected Object getConsequenceParameter(Clause consequence) {
		return consequence.parameter;
	}

	@Override
	protected String getFactName(Fact fact) {
		return fact.name;
	}

	@Override
	protected List<Slot> getFactProperties(Fact fact) {
		return fact.slots;
	}

	@Override
	protected String getFactPropertyName(Slot property) {
		return property.name;
	}

	@Override
	protected Slot getFactPropertyOnPosition(Fact fact, int position) {
		return fact.slots.get(position);
	}

	@Override
	protected String getFactPropertyType(Slot property) {
		return property.type;
	}

	@Override
	protected Object getFactPropertyValue(Fact fact, Slot property) {
		return fact.getSlot(property).value;
	}

	@Override
	protected int getNrOfFactProperties(Fact fact) {
		return fact.slots.size();
	}

	@Override
	protected List<Clause> getRuleConditions(Rule rule) {
		return rule.conditions;
	}

	@Override
	protected List<Clause> getRuleConsequences(Rule rule) {
		return rule.consequences;
	}

	@Override
	protected RuleOperator getRuleOperator(Rule rule) {
		return rule.logicalOperator;
	}

	@Override
	protected boolean isFactPropertyMandatory(Slot property) {
		return property.mandatory;
	}

	@Override
	protected boolean propertyRequiresInputFromUser(Slot property) {
		return true;
	}

	@Override
	protected void setConditionParameter(Clause clause, Object parameter) {
		clause.parameter = parameter;
	}

	@Override
	protected void setFactPropertyValue(Fact fact, Slot property, Object value) {
		fact.getSlot(property).value=value;
	}

}
