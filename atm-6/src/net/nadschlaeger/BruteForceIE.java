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

	private WorkingMemory wm;

	private KnowledgeBase kb;

	public BruteForceIE(WorkingMemory wm, KnowledgeBase kb) {
		this.wm = wm;
		this.kb = kb;
	}

	public void trigger(Fact startFact) {
		assertFact(startFact);
		checkRules();
	}

	@Override
	protected List<Rule> getAllRules() {
		return kb.getAllRules();
	}

	@Override
	protected RuleOperator getRuleOperator(Rule rule) {
		return rule.getRuleOperator();
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
		return condition.getExpression();
	}

	@Override
	protected String getConsequenceExpression(Clause consequence) {
		return consequence.getExpression();
	}

	@Override
	protected Object getConsequenceParameter(Clause consequence) {
		return consequence.getParameter();
	}

	@Override
	protected Object getConditionParameter(Clause condition) {
		return condition.getParameter();
	}

	@Override
	protected void clearRuntimeFactStorage() {
		wm.getInferredFacts().clear();
	}

	@Override
	protected List<Fact> getAllFactsInRuntimeStorage() {
		return wm.getInferredFacts();
	}

	@Override
	protected void assertFact(Fact fact) {
		wm.assertFact(fact);
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
		return property.isInputRequired();
	}

	@Override
	protected String getFactPropertyType(Slot property) {
		return property.getType();
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
	protected List<Fact> getAllFacts() {
		return kb.getAllFacts();
	}

	@Override
	protected int getNrOfFactProperties(Fact fact) {
		return fact.getSlots().size();
	}

	@Override
	protected Slot getFactPropertyOnPosition(Fact fact, int position) {
		if (fact.getSlots().size()<= position){
			return null;
		}
		return fact.getSlots().get(position);
	}

	@Override
	protected boolean isFactPropertyMandatory(Slot slot) {
		return slot.isMandatory();
	}

	@Override
	protected Object getFactPropertyValue(Slot property) {
		return property.getValue();
	}

}
