package Model.Expressions;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class MULExpression implements Expression {
    private Expression exp1;
    private Expression exp2;

    public MULExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public Value evaluate(MyDictionary<String, Value> tbl, MyHeap<Value> heap) throws MyException {
        return (IntValue) new ArithmeticalExpression(
                "-",
                new ArithmeticalExpression(
                        "*",
                        exp1,
                        exp2
                ),
                new ArithmeticalExpression(
                        "+",
                        exp1,
                        exp2
                )
        ).evaluate(tbl, heap);
    }

    @Override
    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}
