package Model.Expressions;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationalExpression implements Expression {
    private Expression op1;
    private Expression op2;
    private String rel;

    public RelationalExpression(Expression op1, Expression op2, String rel) {
        this.op1 = op1;
        this.op2 = op2;
        this.rel = rel;
    }


    @Override
    public Value evaluate(MyDictionary<String, Value> tbl, MyHeap<Value> heap) throws MyException {
        Value val1, val2;

        val1 = op1.evaluate(tbl, heap);

        if(val1.getType().equals(new IntType()))
        {
            val2 = op2.evaluate(tbl, heap);
            if(val2.getType().equals(new IntType()))
            {
                int v1 = ((IntValue)val1).getVal();
                int v2 = ((IntValue)val2).getVal();

                switch(this.rel)
                {
                    case "<":
                        return new BoolValue(v1 < v2);
                        //break;
                    case "<=":
                        return new BoolValue(v1 <= v2);
                        //break;
                    case "==":
                        return new BoolValue(v1 == v2);
                        //break;
                    case "!=":
                        return new BoolValue(v1 != v2);
                        //break;
                    case ">=":
                        return new BoolValue(v1 >= v2);
                        //break;
                    case ">":
                        return new BoolValue(v1 > v2);
                        //break;
                    default:
                        throw new MyException("Unknown relation '" + rel +"' between the two operands.");
                }
            } else
                throw new MyException("Right-hand operand has to be an integer value.");
        } else
            throw new MyException("Left-hand operand has to be an integer value.");
    }

    @Override
    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type type1 = op1.typeCheck(typeEnv);
        Type type2 = op2.typeCheck(typeEnv);

        if (!type1.equals(new IntType()))
            throw new MyException("Right-hand operand has to be an integer.");
        if (!type2.equals(new IntType()))
            throw new MyException("Left-hand operand has to be an integer.");

        return new BoolType();
    }

    @Override
    public String toString()
    {
        return op1+" "+rel+" "+op2;
    }
}
