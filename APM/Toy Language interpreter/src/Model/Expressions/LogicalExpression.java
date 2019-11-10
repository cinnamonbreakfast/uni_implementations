package Model.Expressions;

import Model.Containers.IDictionary;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;

public class LogicalExpression implements Expression {
    private Expression operand1;
    private Expression operand2;
    private String relation;

    public LogicalExpression(Expression operand1, Expression operand2, String relation) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.relation = relation;
    }

    @Override
    public Value evaluate(IDictionary<String, Value> table) throws MyException {
        Value val1;
        Value val2;

        val1 = operand1.evaluate(table);

        if(val1.getType().equals(new BoolType()))
        {
            val2 = operand2.evaluate(table);

            if(val2.getType().equals(new BoolType()))
            {
                BoolValue bv1 = (BoolValue)val1;
                BoolValue bv2 = (BoolValue)val2;

                boolean b1, b2;

                b1 = bv1.getVal();
                b2 = bv2.getVal();

                switch(relation)
                {
                    case "and":
                        return new BoolValue(b1 && b2);
                    case "or":
                        return new BoolValue(b1 || b2);
                    default:
                        throw new MyException("Invalid operand.");
                }
            } else
                throw new MyException("Right-hand operand is not of type boolean.");
        } else
            throw new MyException("Left-hand operand is not of type boolean.");
    }

    @Override
    public String toString() {
        return "LogicalExpression{" +
                "operand1=" + operand1 +
                ", operand2=" + operand2 +
                ", relation='" + relation + '\'' +
                '}';
    }
}
