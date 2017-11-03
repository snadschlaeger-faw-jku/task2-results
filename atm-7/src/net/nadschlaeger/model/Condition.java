package net.nadschlaeger.model;

import net.nadschlaeger.ClauseOperators;

public class Condition extends Clause {

	public Condition(ClauseOperators expression, Fact parameter) {
		super();
		this.expression = expression;
		this.parameter = parameter;
	}
}
