package Repository;

import Model.Exceptions.MyException;
import Model.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    // list of ProgramState's, threading later
    private List<ProgramState> programStates;
    private String logFilePath;

    public Repository (ProgramState currentProgramState, String logFilePath) {
        this.programStates = new ArrayList<ProgramState>();
        this.programStates.add(currentProgramState);
        this.logFilePath = logFilePath;
    }

    public Repository (ProgramState currentProgramState) {
        this.programStates = new ArrayList<ProgramState>();
        this.programStates.add(currentProgramState);
    }

    @Override
    public void setProgramList(List<ProgramState> states) {
        this.programStates = states;
    }

    @Override
    public void logPrgStateExec(ProgramState state) throws MyException {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true))))
        {
            logFile.println(this.programStates.toString() + '\n');
        }
        catch (IOException ioe) {
            throw new MyException(ioe.getMessage());
        }
    }

    public List<ProgramState> getProgramStates() {
        return programStates;
    }

    public void setProgramStates(List<ProgramState> programStates) {
        this.programStates = programStates;
    }
}