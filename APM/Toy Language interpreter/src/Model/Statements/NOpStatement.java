package Model.Statements;

import Model.Exceptions.MyException;
import Model.ProgramState;

public class NOpStatement implements IStatement {



    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    public IStatement deepCopy()
    {
        return new NOpStatement();
    }
}
