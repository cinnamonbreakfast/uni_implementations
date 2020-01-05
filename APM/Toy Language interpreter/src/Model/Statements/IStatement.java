package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Types.Type;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;
    MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException;
}
