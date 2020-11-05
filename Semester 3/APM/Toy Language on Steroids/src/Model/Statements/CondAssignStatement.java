package Model.Statements;

import Model.Containers.IStack;
import Model.Containers.MyDictionary;
import Model.Containers.MyStack;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.Value;

public class CondAssignStatement implements IStatement {
    private String var;
    private Expression exp1;
    private Expression exp2;
    private Expression exp3;

    public CondAssignStatement(String var, Expression exp1, Expression exp2, Expression exp3) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyStack<IStatement> exeStack = (MyStack<IStatement>) state.getExeStack();

        IStatement newStmt = new IfStatement(exp1, new AssignStatement(var, exp2), new AssignStatement(var, exp3));
        exeStack.push(newStmt);
        state.setExeStack(exeStack);

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.get(var);

        exp1.typeCheck(typeEnv);
        exp2.typeCheck(typeEnv);
        exp3.typeCheck(typeEnv);

        if(!typeVar.equals(new StringType()))
            throw new MyException("Symbol "+var+" not found.");

        return typeEnv;
    }
}
