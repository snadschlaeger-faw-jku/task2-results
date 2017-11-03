
public class Clause {
    private final ClauseOperators operator;
    private Fact fact;

    public Clause(ClauseOperators operator, String value) {
        this.operator = operator;
        this.fact = new Fact(value);
    }

    public Clause(ClauseOperators operator, Fact fact) {
        this.operator = operator;
        this.fact = fact;
    }

    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    public void setFact(String value) {
        this.fact = new Fact(value);
    }

    public ClauseOperators getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return operator + " " + fact;
    }
}
