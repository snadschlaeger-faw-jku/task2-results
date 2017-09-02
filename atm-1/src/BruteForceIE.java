import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by snadschlaeger on 28.12.2016.
 */
public class BruteForceIE extends AbstractIE {

	private final List<Fact> facts = new ArrayList<>();
	private final List<Fact> runtimeFacts = new ArrayList<>();
	private final Set<Rule> rules = new HashSet<>();

	private boolean newFacts = true;


	public void addFact(Fact fact)
	{
		facts.add(fact);
	}

	public void addRule(Rule rule)
	{
		rules.add(rule);
	}

	public void trigger()
	{
		while(newFacts)
		{
			newFacts = false;
			checkRules();
		}
	}

	@Override
	protected void assertFact(Fact fact) {
		newFacts = true;
		runtimeFacts.remove(fact);
		runtimeFacts.add(fact);
	}

	@Override
	protected void clearRuntimeFactStorage() {
		runtimeFacts.clear();
	}

	@Override
	protected List<Fact> getAllFacts() {
		List<Fact> all = new ArrayList<>();
		all.addAll(facts);
		all.addAll(runtimeFacts);
		return all;
	}

	@Override
	protected List<Fact> getAllFactsInRuntimeStorage() {
		return runtimeFacts;
	}

	@Override
	protected List<Rule> getAllRules() {
		return new ArrayList<>(rules);
	}

	@Override
	protected String getConditionExpression(Clause condition) {
		return condition.getOperator().name();
	}

	@Override
	protected Fact getConditionParameter(Clause condition) {
		return condition.getFact();
	}

	@Override
	protected String getConsequenceExpression(Clause consequence) {
		return consequence.getOperator().name();
	}

	@Override
	protected Object getConsequenceParameter(Clause consequence) {
		return consequence.getFact();
	}

	@Override
	protected String getFactName(Fact fact) {
		return fact.getName();
	}

	@Override
	protected List<Slot> getFactProperties(Fact fact) {
		return new ArrayList<>(fact.getSlots());
	}

	@Override
	protected String getFactPropertyName(Slot property) {
		return property.getName();
	}

	@Override
	protected Slot getFactPropertyOnPosition(Fact fact, int position) {
		return fact.getSlot(position);
	}

	@Override
	protected String getFactPropertyType(Slot property) {
		return property.getValueClassName();
	}

	@Override
	protected Object getFactPropertyValue(Slot property) {
		return property.getValue();
	}

	@Override
	protected int getNrOfFactProperties(Fact fact) {
		return fact.getNumProperties();
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
		return rule.getOperator();
	}

	@Override
	protected boolean isFactPropertyMandatory(Slot property) {
		return property.isMandatory();
	}

	@Override
	protected boolean propertyRequiresInputFromUser(Slot property) {
		return property.askUser();
	}

	@Override
	protected void setConditionParameter(Clause clause, Fact parameter) {
		clause.setFact(parameter);
	}

	@Override
	protected void setFactPropertyValue(Slot property, Object value) {
		property.setValue(value);
	}

	public Rule addRule(String name)
	{
		Rule rule = new Rule(name);
		addRule(rule);
		return rule;
	}

}
