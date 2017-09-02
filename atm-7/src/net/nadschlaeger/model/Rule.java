package net.nadschlaeger.model;

import java.util.ArrayList;
import java.util.List;

import net.nadschlaeger.RuleOperator;

public class Rule {
	private final String name;

	private final RuleOperator operator;

	private List<Condition> conditions = new ArrayList<>();
	private List<Consequence> consequences = new ArrayList<>();

	public Rule(RuleOperator operator, List<Condition> conditions, List<Consequence> consequences, String name) {
		super();
		this.operator = operator;
		this.conditions = conditions;
		this.consequences = consequences;
		this.name = name;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public List<Consequence> getConsequences() {
		return consequences;
	}

	public String getName() {
		return name;
	}

	public RuleOperator getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		return "Rule [operator=" + operator + ", conditions=" + conditions + ", consequences=" + consequences + "]";
	}

}
