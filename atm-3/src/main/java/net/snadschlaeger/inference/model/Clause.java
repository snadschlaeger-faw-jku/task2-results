package net.snadschlaeger.inference.model;

import net.snadschlaeger.inference.ClauseOperator;

public class Clause extends Model {

	private ClauseOperator operator;

	private Fact parameter;

	public static Clause create(ClauseOperator operator) {
		return new Clause(operator);
	}

	public Clause(ClauseOperator operator) {
		super();
		this.operator = operator;
	}

	public ClauseOperator getOperator() {
		return operator;
	}

	public Clause setOperator(ClauseOperator operator) {
		this.operator = operator;
		return this;
	}

	public Fact getParameter() {
		return parameter;
	}

	public Clause setParameter(Fact parameter) {
		this.parameter = parameter;
		return this;
	}

}
