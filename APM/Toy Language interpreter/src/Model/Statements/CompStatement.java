package Model.Statements;

import Model.Containers.MyStack;
import Model.Exceptions.MyException;
import Model.ProgramState;

public class CompStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyStack<IStatement> stack = (MyStack<IStatement>) state.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public String toString() {
        return first + " \n\t" + second;
    }

    public IStatement deepCopy()
    {
        return new CompStatement(first, second);
    }
}
