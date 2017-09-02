import java.util.ArrayList;
import java.util.List;

public class Rule {

    private final String name;
    private final RuleOperator operator;
    private final List<Clause> conditions = new ArrayList<>();
    private final List<Clause> consequences = new ArrayList<>();

    // Since the RuleOperator seems not to be in use...
    public Rule(String name)
    {
        this(name, null);
    }

    public Rule(String name, RuleOperator operator) {
        this.name = name;
        this.operator = operator;
    }

    public RuleOperator getOperator() {
        return operator;
    }

    public Rule addCondition(ClauseOperators operator, Fact fact) {
        Clause clause = new Clause(operator, fact);
        conditions.add(clause);
        return this;
    }

    public List<Clause> getConditions() {
        return new ArrayList<>(conditions);
    }

    public Rule addConsequence(ClauseOperators operator, Fact fact) {
        Clause clause = new Clause(operator, fact);
        consequences.add(clause);
        return this;
    }

    public Rule addConsequence(ClauseOperators operator, String value) {
        Clause clause = new Clause(operator, value);
        consequences.add(clause);
        return this;
    }

    public List<Clause> getConsequences() {
        return new ArrayList<>(consequences);
    }

    @Override
    public String toString() {
        return name + ": " + operator + " IF " + conditions + " THEN " + consequences;
    }
}
