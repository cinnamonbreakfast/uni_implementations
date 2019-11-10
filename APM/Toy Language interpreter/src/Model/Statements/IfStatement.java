package Model.Statements;

import Model.Containers.IDictionary;
import Model.Containers.IStack;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;

public class IfStatement implements IStatement {
    private Expression exp;
    private IStatement doif;
    private IStatement doEsle;

    public IfStatement(Expression exp, IStatement doif, IStatement doEsle) {
        this.exp = exp;
        this.doif = doif;
        this.doEsle = doEsle;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStack<IStatement> stack = state.getExeStack();
        IDictionary<String, Value> table = state.getSymTable();

        Value condition = this.exp.evaluate(table);

        if(condition.getType().equals(new BoolType()))
        {
            if(((BoolValue)condition).getVal())
            {
                stack.push(doif);
            } else {
                stack.push(doEsle);
            }
        } else {
            throw new MyException("Cannot treat statement as a boolean, invalid condition.");
        }

        return state;
    }

    @Override
    public String toString() {
        return "IfStatement{" +
                "if(" + exp +
                ") then (" + doif +
                ") else (" + doEsle +
                ')';
    }

    public IStatement deepCopy()
    {
        return new IfStatement(exp, doif, doEsle);
    }
}
