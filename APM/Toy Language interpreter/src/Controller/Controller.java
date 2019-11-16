package Controller;

import Model.Containers.IStack;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Statements.IStatement;
import Repository.IRepository;

public class Controller {
    private IRepository repo;

    public Controller(IRepository repository) {
        this.repo = repository;
    }

    public ProgramState oneStep(ProgramState state) throws MyException {
        IStack<IStatement> stack = state.getExeStack();
        if (stack.isEmpty())
            throw new MyException("Program state stack is empty.");
        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void allStep() throws MyException {
        ProgramState state = repo.getCurrentProgram();
        state.getExeStack().push(state.getOriginalProgram());
        repo.logPrgStateExec();                     // First state
        while (!state.getExeStack().isEmpty()) {
            this.oneStep(state);
            repo.logPrgStateExec();                 // Step-by-step
        }
//        System.out.println(state);
    }
}