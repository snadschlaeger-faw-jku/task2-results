package net.nadschlaeger.model;

import net.nadschlaeger.ClauseOperators;

public abstract class Clause {

	protected Fact parameter;

	protected ClauseOperators expression;


	public ClauseOperators getExpression() {
		return expression;
	}

	public Fact getParameter() {
		return parameter;
	}

	public void setExpression(ClauseOperators expression) {
		this.expression = expression;
	}

	public void setParameter(Fact parameter) {
		this.parameter = parameter;
	}

	@Override
	public String toString() {
		return "Clause [expression=" + expression + ", parameter=" + parameter + "]";
	}

}
