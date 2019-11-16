package Model.Statements;

import Model.Containers.IList;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Values.Value;

public class PrintStatement implements IStatement {
    private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "print(\""+expression+"\"); ";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IList<Value> out = state.getOut();
        out.add(expression.evaluate(state.getSymTable()));

        return state;
    }

    public PrintStatement deepCopy()
    {
        return new PrintStatement(expression);
    }
}
