package Model.Expressions;

import Model.Containers.IDictionary;
import Model.Exceptions.MyException;
import Model.Values.Value;

public class ValueExpression implements Expression {
    private Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    public Value evaluate(IDictionary<String, Value> table) throws MyException
    {
        return value;
    }
}
