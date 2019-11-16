package Repository;

import Model.Exceptions.MyException;
import Model.ProgramState;

public interface IRepository {
    ProgramState getCurrentProgram();
    void logPrgStateExec() throws MyException;
}