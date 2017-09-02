package net.snadschlaeger.inference.model;

import java.util.ArrayList;
import java.util.List;

import net.snadschlaeger.inference.RuleOperator;

public class Rule extends Model {

	private String name;

	private RuleOperator operator;

	private List<Clause> consequences;

	private List<Clause> conditions;

	public static Rule create(String name) {
		return new Rule(name);
	}

	public Rule(String name) {
		this.name = name;
		this.operator = RuleOperator.AND;
		this.consequences = new ArrayList<>();
		this.conditions = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public Rule setName(String name) {
		this.name = name;
		return this;
	}

	public RuleOperator getOperator() {
		return operator;
	}

	public Rule setOperator(RuleOperator operator) {
		this.operator = operator;
		return this;
	}

	public List<Clause> getConsequences() {
		return consequences;
	}

	public Rule setConsequences(List<Clause> consequences) {
		this.consequences = consequences;
		return this;
	}

	public Rule addConsequence(Clause consequence) {
		this.consequences.add(consequence);
		return this;
	}

	public List<Clause> getConditions() {
		return conditions;
	}

	public Rule setConditions(List<Clause> conditions) {
		this.conditions = conditions;
		return this;
	}

	public Rule addCondition(Clause condition) {
		this.conditions.add(condition);
		return this;
	}

}
