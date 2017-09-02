package net.nadschlaeger.model;

import net.nadschlaeger.ClauseOperators;

public class Consequence extends Clause {


	public Consequence(ClauseOperators expression, Fact parameter) {
		super();
		this.expression = expression;
		this.parameter = parameter;
	}

}
