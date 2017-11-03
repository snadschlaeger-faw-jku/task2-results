package net.nadschlaeger;

import java.util.List;

public class Rule {

	public String name;
	// logical operator: AND | OR
	public Clause conditions;
	public Clause consequences;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Clause getConditions() {
		return conditions;
	}
	public void setConditions(Clause conditions) {
		this.conditions = conditions;
	}
	public Clause getConsequences() {
		return consequences;
	}
	public void setConsequences(Clause consequences) {
		this.consequences = consequences;
	}
	
	
	

	
	

}
