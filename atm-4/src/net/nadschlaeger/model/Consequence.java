package net.nadschlaeger.model;
import net.nadschlaeger.ClauseOperators;

public class Consequence {
	public ClauseOperators op;
	public Object parameter;

	public Consequence(ClauseOperators op,Object parameter) {
		this.op = op;
		this.parameter = parameter;
	}
}