package Model.Expressions;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Statements.IStatement;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class NotExpression implements Expression {
    private Expression exp;

    public NotExpression(Expression exp) {
        this.exp = exp;
    }

    @Override
    public Value evaluate(MyDictionary<String, Value> tbl, MyHeap<Value> heap) throws MyException {
        boolean val = ((BoolValue)exp.evaluate(tbl, heap)).getVal();

        return new BoolValue(!val);
    }

    @Override
    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type type1 = exp.typeCheck(typeEnv);

        if(!type1.equals(new BoolType()))
            throw new MyException("Cannot 'not' on non-boolean value.");


        return new BoolType();
    }
}
