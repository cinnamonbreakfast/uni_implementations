package Model.Expressions;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithmeticalExpression implements Expression {
    private Expression operand1;
    private Expression operand2;
    private int operation;
                //  1 +
                //  2 -
                //  3 *
                //  4 /
    private String op;

    public ArithmeticalExpression(String operation, Expression operand1, Expression operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.op = operation;

        switch(operation)
        {
            case "+":
                this.operation = 1;
                break;
            case "-":
                this.operation = 2;
                break;
            case "*":
                this.operation = 3;
                break;
            case "/":
                this.operation = 4;
                break;
        }
    }

    public ArithmeticalExpression(Expression operand1, Expression operand2, int operation) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
    }

    public Value evaluate(MyDictionary<String, Value> tbl, MyHeap<Value> heap) throws MyException
    {
        Value value1, value2;
        value1 = operand1.evaluate(tbl, heap);

        if(value1.getType().equals(new IntType()))
        {
            value2 = operand2.evaluate(tbl, heap);

            if(value2.getType().equals(new IntType()))
            {
                IntValue integer1 = (IntValue)value1;
                IntValue integer2 = (IntValue)value2;

                int nint1, nint2;
                nint1 = integer1.getVal();
                nint2 = integer2.getVal();

                if(operation == 1)
                {
                    return new IntValue(nint1 + nint2);
                }
                else if (operation == 2)
                {
                    return new IntValue(nint1 - nint2);
                } else if (operation == 3) {
                    return new IntValue(nint1 * nint2);
                } else if (operation == 4)
                {
                    if(nint2 == 0)
                    {
                        throw new MyException("Division by zero!");
                    }
                    return new IntValue(nint1 / nint2);
                }
            } else
            {
                throw new MyException("Right-hand operand is not a number.");
            }
        }
        throw new MyException("Left-hand operand is not a number.");

     }

    @Override
    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type type1 = operand1.typeCheck(typeEnv);
        Type type2 = operand2.typeCheck(typeEnv);

        if(!type1.equals(new IntType()))
            throw new MyException("Left-hand operand is not a number.");

        if(!type2.equals(new IntType()))
            throw new MyException("Right-hand operand is not a number.");

        return new IntType();
    }

    @Override
    public String toString() {
        return operand1 + " " + op + " " + operand2;
    }
}
