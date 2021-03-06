package Model.Expressions;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Types.Type;
import Model.Values.Value;

public class VariableExpression implements Expression {
    private String name;

    public VariableExpression(String name)
    {
        this.name = name;
    }

    @Override
    public Value evaluate(MyDictionary<String, Value> table, MyHeap<Value> heap) throws MyException {
        if(table.contains(name))
        {
            return table.get(name);
        }
        throw new MyException("Unknown variable symbol. Missing its declaration?");
    }

    @Override
    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        if(!typeEnv.contains(name))
            throw new MyException("Variable '" + name + "' is not defined.");

        return typeEnv.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
