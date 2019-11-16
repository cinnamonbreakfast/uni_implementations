package Model.Statements;

import Model.Containers.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStatement implements IStatement {
    private String id;
    private Expression expression;

    public AssignStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, Value> table = state.getSymTable();
        Value val = expression.evaluate(table);

        if(table.contains(id))
        {
            Type typeId = (table.get(id)).getType();

            if(val.getType().equals(typeId))
            {
                table.put(id, val);
            } else {
                throw new MyException("Declared type of variable " + id + " and type of expression does not match.");
            }
        } else {
            throw new MyException("Usage of undeclared variable name " + id + ".");
        }
        return state;
    }

    @Override
    public String toString() {
        return id +
                " = " + expression +
                ";";
    }

    public IStatement deepCopy()
    {
        return new AssignStatement(id, expression);
    }
}
