package main.java.model;

import main.java.ClauseOperators;

public class Clause {

	public Object parameter;
	public ClauseOperators expression;

	public Clause(ClauseOperators operator, Object xx) {
		this.expression = operator;
		this.parameter = xx;
	}

	@Override
	public String toString() {
		return "Clause [parameter=" + parameter + ", expression=" + expression + "]";
	}
	
	
}
