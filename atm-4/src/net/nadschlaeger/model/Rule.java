package net.nadschlaeger.model;
import java.util.List;

import net.nadschlaeger.RuleOperator;

public class Rule {
	private String name;
	private RuleOperator logicalOperator;
	private List<Clause> conditions;
	private List<Clause> consequences;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RuleOperator getLogicalOperator() {
		return logicalOperator;
	}
	public void setLogicalOperator(RuleOperator logicalOperator) {
		this.logicalOperator = logicalOperator;
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