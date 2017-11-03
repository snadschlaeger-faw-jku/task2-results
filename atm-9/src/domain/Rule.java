package domain;

import util.RuleOperator;

import java.util.List;

public class Rule {
    private RuleOperator ruleOperator;
    private List<Condition> conditions;
    private List<Action> actions;

    public Rule(RuleOperator ruleOperator, List<Condition> conditions, List<Action> actions) {
        this.ruleOperator = ruleOperator;
        this.conditions = conditions;
        this.actions = actions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public List<Action> getConsequences() {
        return actions;
    }

    public RuleOperator getRuleOperator() {
        return ruleOperator;
    }
}
