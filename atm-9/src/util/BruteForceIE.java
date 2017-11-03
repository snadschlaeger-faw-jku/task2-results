package util;

import domain.*;
import repo.MySqlATMRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snadschlaeger on 28.12.2016.
 */
public class BruteForceIE extends AbstractIE {

    private List<Fact> factList;
    private List<Rule> ruleList;
    private List<String> entityList;
    private MySqlATMRepository repo;

	@Override
	protected void assertFact(Fact fact) {
        factList.add(fact);
	}

	@Override
	protected List<Property> getFactProperties(Object fact) {
		return ((Fact) fact).getPropertyList();
	}

	public BruteForceIE(MySqlATMRepository repo, List<String> entityList) throws SQLException, ClassNotFoundException {
        this.factList = new ArrayList<>();
        this.ruleList = new ArrayList<>();
        //ruleList.addAll(getAllRules());

        this.repo = repo;
        this.entityList = entityList;

        getAllFactsInRuntimeStorage();
        checkRules();


	}

	@Override
	protected List<Fact> getAllFactsInRuntimeStorage() throws SQLException, ClassNotFoundException {
		factList.clear();
		this.factList.addAll(repo.getFacts(entityList));
		return factList;
	}

    @Override
	protected void clearRuntimeFactStorage() {
		factList.clear();
	}

	@Override
	protected List<Rule> getAllRules() {
		return repo.getRules();
	}

	@Override
	protected String getConditionExpression(Object condition) {
        return ((Condition) condition).getExpression();
	}

	@Override
	protected Object getConditionParameter(Object condition) {
        return ((Condition) condition).getParameter();
	}

	@Override
	protected String getConsequenceExpression(Object consequence) {
        return ((Action) consequence).getExpression();
	}

	@Override
	protected Object getConsequenceParameter(Object consequence) {
		return ((Action) consequence).getParameter();
	}

	@Override
	protected String getFactName(Object fact) {
        return ((Fact) fact).getName();
	}



	@Override
	protected String getFactPropertyName(Object property) {
        return ((Property) property).getName();
	}

	@Override
	protected Object getFactPropertyOnPosition(Object fact, int position) {
        return ((Fact) fact).getPropertyAt(position);
	}

	@Override
	protected String getFactPropertyType(Object property) {
		return ((Property) property).getType();
	}

	@Override
	protected Object getFactPropertyValue(Object property) {
		return ((Property) property).getValue();
	}

	@Override
	protected int getNrOfFactProperties(Object fact) {
		return ((Fact) fact).getPropertyListSize();
	}

	@Override
	protected List<Condition> getRuleConditions(Object rule) {
        return ((Rule) rule).getConditions();
	}

	@Override
	protected List<Action> getRuleConsequences(Object rule) {
		return ((Rule) rule).getConsequences();
	}

	@Override
	protected RuleOperator getRuleOperator(Object rule) {
		return ((Rule) rule).getRuleOperator();
	}

	@Override
	protected boolean isFactPropertyMandatory(Object property) {
		return ((Property) property).getIsMandatory();
	}

	@Override
	protected boolean propertyRequiresInputFromUser(Object property) {
        return ((Property) property).getValue() == null;
	}

	@Override
	protected void setConditionParameter(Condition clause, Fact parameter) {
        clause.setParameter(parameter);
	}


	protected List<Fact> getAllFacts() throws SQLException {

		return repo.getFacts(entityList);

	}

	@Override
	protected void setFactPropertyValue(Object property, Object value) {
        ((Property) property).setValue((String)value);
	}

}
