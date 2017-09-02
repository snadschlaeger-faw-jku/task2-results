package main.java.model;

import java.util.ArrayList;
import java.util.List;

import main.java.RuleOperator;

public class Rule {

	public String name;
	public RuleOperator logicalOperator;
	public List<Clause> conditions = new ArrayList<>();
	public List<Clause> consequences = new ArrayList<>();

	@Override
	public String toString() {
		return "Rule [name=" + name + "]";
	}

}
