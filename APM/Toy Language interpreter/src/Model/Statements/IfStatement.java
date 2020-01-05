package Model.Statements;

import Model.Containers.*;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
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
        MyStack<IStatement> stack = (MyStack<IStatement>) state.getExeStack();
        MyDictionary<String, Value> table = (MyDictionary<String, Value>) state.getSymTable();
        MyHeap<Value> heap = (MyHeap<Value>) state.getHeap();

        Value condition = this.exp.evaluate(table, heap);

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

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typeCheck(typeEnv);

        if(!(typeExp.equals(new BoolType())))
            throw new MyException("Expression inside the condition is not boolean.");

        doif.typeCheck(typeEnv);
        doEsle.typeCheck(typeEnv);

        return typeEnv;
    }

    @Override
    public String toString() {
        return "if(" + exp +
                ") then {" + doif +
                "} \n\t\telse { " + doEsle +
                " };";
    }

    public IStatement deepCopy()
    {
        return new IfStatement(exp, doif, doEsle);
    }
}
