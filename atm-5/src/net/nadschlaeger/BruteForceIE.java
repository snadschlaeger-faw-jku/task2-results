package net.nadschlaeger;

import java.util.List;

import net.nadschlaeger.model.Clause;
import net.nadschlaeger.model.Condition;
import net.nadschlaeger.model.Consequence;
import net.nadschlaeger.model.Fact;
import net.nadschlaeger.model.Rule;
import net.nadschlaeger.model.Slot;
import net.nadschlaeger.service.FactStorage;
import net.nadschlaeger.service.KnowledgeBase;

/**
 * Created by snadschlaeger on 28.12.2016.
 */
public class BruteForceIE extends AbstractIE {

	FactStorage runtimeStorage;
	private final KnowledgeBase kb;

	public BruteForceIE(KnowledgeBase kb, FactStorage runtimeStorage) {
		this.runtimeStorage = runtimeStorage;
		this.kb=kb;

	}

	@Override
	protected void assertFact(Fact fact) {
		runtimeStorage.assertFact(fact);

	}

	@Override
	protected void clearRuntimeFactStorage() {
		runtimeStorage.clear();

	}

	@Override
	protected List<Fact> getAllFacts() {
		return kb.getFacts();
	}

	@Override
	protected List<Fact> getAllFactsInRuntimeStorage() {
		return runtimeStorage.getAllFacts();
	}

	@Override
	protected List<Rule> getAllRules() {
		return kb.getRules();
	}

	@Override
	protected ClauseOperators getConditionExpression(Clause condition) {
		return condition.getExpression();
	}

	@Override
	protected Fact getConditionParameter(Clause condition) {
		return condition.getParameter();
	}

	@Override
	protected ClauseOperators getConsequenceExpression(Clause consequence) {
		return consequence.getExpression();
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
		// TODO rangecheck
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
		return fact.getSlotCount();
	}

	@Override
	protected List<Condition> getRuleConditions(Rule rule) {
		return rule.getConditions();
	}

	@Override
	protected List<Consequence> getRuleConsequences(Rule rule) {
		return rule.getConsequences();
	}

	@Override
	protected RuleOperator getRuleOperator(Rule rule) {
		return rule.getOperator();
	}

	@Override
	protected boolean isFactPropertyMandatory(Slot property) {
		return property.isMandatory();
	}

	@Override
	protected boolean propertyRequiresInputFromUser(Slot property) {
		return property.isRequiresInputFromUser();
	}

	@Override
	protected void setConditionParameter(Clause condition, Fact parameter) {
		if (condition != null && parameter != null) {
			condition.setParameter(parameter);
		}

	}

	@Override
	protected void setFactPropertyValue(Slot property, Object value) {
		if (property != null) {
			property.setValue(value);
		}

	}



}
