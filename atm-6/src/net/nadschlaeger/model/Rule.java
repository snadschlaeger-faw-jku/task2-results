package net.nadschlaeger.model;

import java.util.ArrayList;
import java.util.List;

import net.nadschlaeger.RuleOperator;

public class Rule {

	private String name;

	private RuleOperator ruleOperator;

	private List<Clause> conditions = new ArrayList<>();

	private List<Clause> consequences = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RuleOperator getRuleOperator() {
		return ruleOperator;
	}

	public void setRuleOperator(RuleOperator ruleOperator) {
		this.ruleOperator = ruleOperator;
	}

	public List<Clause> getConditions() {
		return conditions;
	}

	public void setConditions(List<Clause> conditions) {
		this.conditions = conditions;
	}

	public List<Clause> getConsequences() {
		return consequences;
	}

	public void setConsequences(List<Clause> consequences) {
		this.consequences = consequences;
	}

}
