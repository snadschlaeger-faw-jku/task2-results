package domain;

public class Condition {
    private String expression;
    private Object parameter;

    public Condition(String expression, Object parameter) {
        this.expression = expression;
        this.parameter = parameter;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}
