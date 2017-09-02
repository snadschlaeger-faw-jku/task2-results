package net.snadschlaeger.inference;

import java.util.List;

import net.snadschlaeger.inference.memory.KnowledgeBase;
import net.snadschlaeger.inference.memory.WorkingMemory;
import net.snadschlaeger.inference.model.Clause;
import net.snadschlaeger.inference.model.Fact;
import net.snadschlaeger.inference.model.Rule;
import net.snadschlaeger.inference.model.Slot;

public class BruteForceIE extends InferenceEngine {

	private WorkingMemory workingMemory;

	private KnowledgeBase knowledgeBase;

	public BruteForceIE(WorkingMemory workingMemory, KnowledgeBase knowledgeBase) {
		super();
		this.workingMemory = workingMemory;
		this.knowledgeBase = knowledgeBase;
	}

	@Override
	protected RuleOperator getRuleOperator(Rule rule) {
		return rule.getOperator();
	}

	@Override
	protected List<Clause> getRuleConditions(Rule rule) {
		return rule.getConditions();
	}

	@Override
	protected List<Clause> getRuleConsequences(Rule rule) {
		return rule.getConsequences();
	}

	@Override
	protected String getConditionExpression(Clause condition) {
		return condition.getOperator().name();
	}

	@Override
	protected String getConsequenceExpression(Clause consequence) {
		return consequence.getOperator().name();
	}

	@Override
	protected Fact getConsequenceParameter(Clause consequence) {
		return consequence.getParameter();
	}

	@Override
	protected Fact getConditionParameter(Clause condition) {
		return condition.getParameter();
	}

	@Override
	protected List<Slot> getFactProperties(Fact fact) {
		return fact.getSlots();
	}

	@Override
	protected String getFactName(Fact fact) {
		return fact.getName();
	}

	@Override
	protected boolean propertyRequiresInputFromUser(Slot property) {
		return property.isRequiresUserInput();
	}

	@Override
	protected String getFactPropertyType(Slot property) {
		return property.getType().getName();
	}

	@Override
	protected void setFactPropertyValue(Slot property, Object value) {
		property.setValue(value);
	}

	@Override
	protected String getFactPropertyName(Slot property) {
		return property.getName();
	}

	@Override
	protected void setConditionParameter(Clause condition, Fact parameter) {
		condition.setParameter(parameter);
	}

	@Override
	protected int getNrOfFactProperties(Fact fact) {
		return fact.getSlots().size();
	}

	@Override
	protected Slot getFactPropertyOnPosition(Fact fact, int position) {
		return fact.getSlots().get(position);
	}

	@Override
	protected boolean isFactPropertyMandatory(Slot property) {
		return property.isMandatory();
	}

	@Override
	protected Object getFactPropertyValue(Slot property) {
		return property.getValue();
	}

	@Override
	protected List<Rule> getAllRules() {
		return knowledgeBase.getAllRules();
	}

	@Override
	protected void clearRuntimeFactStorage() {
		workingMemory.clear();
	}

	@Override
	protected List<Fact> getAllFactsInRuntimeStorage() {
		return workingMemory.getRuntimeFacts();
	}

	@Override
	protected void assertFact(Fact fact) {
		workingMemory.assertFact(fact);
	}

	@Override
	protected List<Fact> getAllFacts() {
		return knowledgeBase.getAllFacts();
	}

}
