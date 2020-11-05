package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Types.Type;

public class NOpStatement implements IStatement {



    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    public IStatement deepCopy()
    {
        return new NOpStatement();
    }
}
