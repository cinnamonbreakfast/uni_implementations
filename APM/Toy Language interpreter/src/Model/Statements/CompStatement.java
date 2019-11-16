package Model.Statements;

import Model.Containers.IStack;
import Model.Exceptions.MyException;
import Model.ProgramState;

public class CompStatement implements IStatement {
    IStatement first;
    IStatement second;

    public CompStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStack<IStatement> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return state;
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
