package net.nadschlaeger.model;
import net.nadschlaeger.ClauseOperators;

public class Clause {
	public ClauseOperators op;
	public Fact fact;
	
	public void setExpression(ClauseOperators op ) {
		this.op = op;
	}
	
	public void setParameter(Fact fact) {
		this.fact = fact;
	}
	
	public Fact getParameter() {
		return fact;
	}
	
	public ClauseOperators getExpression(  ) {
		return op;
	}
	
	/*@Override
	public String toString() {
		
		return "Clause:[op: " + op.toString() + ", fact:" + ((fact != null)? fact.toString() :"") + "]";
	}*/
}