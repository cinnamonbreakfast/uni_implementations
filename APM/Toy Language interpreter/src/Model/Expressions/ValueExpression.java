package Model.Expressions;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Values.Value;

public class ValueExpression implements Expression {
    private Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    public Value evaluate(MyDictionary<String, Value> table, MyHeap<Value> heap) throws MyException
    {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
