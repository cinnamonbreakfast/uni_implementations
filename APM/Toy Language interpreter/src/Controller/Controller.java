package Controller;

import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private IRepository repo;
    private ExecutorService executor;

    public Controller(IRepository repository) {
        this.repo = repository;
    }

//    public ProgramState oneStep(ProgramState state) throws MyException {
//        IStack<IStatement> stack = state.getExeStack();
//        if (stack.isEmpty())
//            throw new MyException("Program state stack is empty.");
//        IStatement currentStatement = stack.pop();
//        return currentStatement.execute(state);
//    }

    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> addressesFromSymbolTable, Map<Integer,Value> heap)
    {
        return heap.entrySet()
                .stream()
                .filter(e->addressesFromSymbolTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Collection<Value> heap){

        return Stream.concat(
                heap.stream()
                        .filter(v-> v instanceof RefValue)
                        .map(v-> {
                            RefValue v1 = (RefValue)v;
                            return v1.getAddr();
                        }),
                symTableValues.stream()
                        .filter(v-> v instanceof RefValue)
                        .map(v-> {RefValue v1 = (RefValue)v;
                            return v1.getAddr();
                        })
        )
                .collect(Collectors.toList());
    }

    public void oneStepForAllProgram(List<ProgramState> progList) throws InterruptedException {
        // print the State into log file before execution
        progList.forEach(prg->repo.logPrgStateExec(prg));

        // CONCURENCY RUN
        // Prepare the list of "callables"
        List<Callable<ProgramState>> callList = progList.stream()
                .map((ProgramState p)->(Callable<ProgramState>)(
                        ()->{ return p.oneStep(); }
                        ))
                .collect(Collectors.toList());

        // Start the execution of "callables"

        List<ProgramState> newProgList = executor.invokeAll(callList).stream()
                .map(futureObj -> {
                    try { return futureObj.get(); }
                    catch (ExecutionException | InterruptedException ignored) {
                        // catch errors, even MyException
                        System.out.println(ignored.getMessage());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Add the new created threads to the list of existing threads
        progList.addAll(newProgList);

        // Print the ProgramState List into the log file
        progList.forEach(prg->repo.logPrgStateExec(prg));

        // Save the current programs into the repository
        repo.setProgramList(progList);
    }

    public void allStep() throws MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        // Remove the completed programs
        List<ProgramState> prgList = removeCompletedProgram(repo.getProgramStates());

        while(prgList.size() > 0)
        {
            oneStepForAllProgram(prgList);

            // Remove the completed programs

            prgList = removeCompletedProgram(repo.getProgramStates());
        }

        executor.shutdownNow();

        // HERE the repository still contains at least one Completed PRG
        // and its List<ProgramState> is not empty. Note that oneStepForAllPrg
        // calls the method setPrgList of the repository in order to change
        // the repository

        // Update the Repository State
        repo.setProgramList(prgList);

/*
        +-------------------------------------------------+
        |  Before FORK() - i want to keep it as a memory  |
        |     *That's so sad, Alexa play Despacito*       |
        +-------------------------------------------------+

          ProgramState state = repo.getCurrentProgram();
//        state.getExeStack().push(state.getOriginalProgram());
//                          in case if you want to use the same stack n shit
        repo.logPrgStateExec();                     // First state
        while (!state.getExeStack().isEmpty()) {
            this.oneStep(state);
            repo.logPrgStateExec();                 // Step-by-step
            state.getHeap().setContent(
                    unsafeGarbageCollector(
                        getAddrFromSymTable(
                            state.getSymTable().values(),
                            state.getHeap().getContent().values()
                        ),
                        state.getHeap().getContent()
                    )
                );
        }
        System.out.println(state);*/
    }

    private List<ProgramState> removeCompletedProgram(List<ProgramState> states)
    {
        return states.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }
}