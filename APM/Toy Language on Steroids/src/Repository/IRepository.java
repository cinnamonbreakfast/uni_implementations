package Repository;

import Model.Exceptions.MyException;
import Model.ProgramState;

import java.util.List;

public interface IRepository {
//    ProgramState getCurrentProgram();
    void logPrgStateExec(ProgramState state) throws MyException;
    public void setProgramList(List<ProgramState> states);
    public List<ProgramState> getProgramStates();
}