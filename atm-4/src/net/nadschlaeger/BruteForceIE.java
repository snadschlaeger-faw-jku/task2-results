package net.nadschlaeger;

import java.util.List;

import net.nadschlaeger.model.Clause;
import net.nadschlaeger.model.Fact;
import net.nadschlaeger.model.KnowledgeBase;
import net.nadschlaeger.model.Rule;
import net.nadschlaeger.model.Slot;
import net.nadschlaeger.model.WorkingMemory;

/**
 * Created by snadschlaeger on 28.12.2016.
 */
public class BruteForceIE extends AbstractIE {

	KnowledgeBase kb;
	WorkingMemory wb;
	/*
	 * List<Object> rules; List<Object> dbFacts; List<Object> rtFacts;
	 */

	public BruteForceIE(WorkingMemory wb, KnowledgeBase kb) {
		this.kb = kb;
		this.wb = wb;

	}

	@Override
	protected void assertFact(Fact fact) {
		wb.assertFact(fact);
	}

	@Override
	protected void clearRuntimeFactStorage() {
		//rtFacts = new ArrayList<>();
		wb.clearInferredFacts();
	}

	@Override
	protected List<Fact> getAllFacts() {
		return kb.getAllFacts();
	}

	@Override
	protected List<Fact> getAllFactsInRuntimeStorage() {
		return wb.getInferredFacts();
	}

	@Override
	protected List<Rule> getAllRules() {
		return kb.getAllRules();
	}

	@Override
	protected String getConditionExpression(Clause condition) {
		return condition.op.name();
	}

	@Override
	protected Fact getConditionParameter(Clause condition) {
		return condition.fact;
	}

	@Override
	protected String getConsequenceExpression(Clause consequence) {
		return consequence.getExpression().name();
	}

	@Override
	protected Fact getConsequenceParameter(Clause consequence) {
		return consequence.getParameter();
	}

	@Override
	protected String getFactName(Fact fact) {
		return fact.getName();
	}

	@Override
	protected List<Slot> getFactProperties(Fact fact) {
		return fact.getSlots();
	}

	@Override
	protected String getFactPropertyName(Slot property) {
		return property.getName();
	}

	@Override
	protected Slot getFactPropertyOnPosition(Fact fact, int position) {
		return fact.getSlots().get(position);
	}

	@Override
	protected String getFactPropertyType(Slot property) {
		return property.getType();
	}

	@Override
	protected Object getFactPropertyValue(Slot property) {
		return property.getValue();
	}

	@Override
	protected int getNrOfFactProperties(Fact fact) {
		return fact.getSlots().size();
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
	protected RuleOperator getRuleOperator(Rule rule) {
		return rule.getLogicalOperator();
	}

	@Override
	protected boolean isFactPropertyMandatory(Slot property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean propertyRequiresInputFromUser(Slot property) {
		return (getFactPropertyValue(property) == null);
	}

	@Override
	protected void setConditionParameter(Clause clause, Fact parameter) {
		clause.setParameter(parameter);
	}

	@Override
	protected void setFactPropertyValue(Slot property, Object value) {
		property.setValue(value);
	}

}
