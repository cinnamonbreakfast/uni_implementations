package Model.Statements;

import Model.Containers.IBarrier;
import Model.Containers.MyDictionary;
import Model.Containers.MyStack;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public AwaitStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();

        MyStack<IStatement> exeStack = (MyStack<IStatement>) state.getExeStack();
        IBarrier barriers = state.getBarrierTable();

        if(!state.getSymTable().contains(var))
            throw new MyException("No entry in symbol table for name "+var+".");

        int index = ((IntValue)state.getSymTable().get(var)).getVal();
        Pair<Integer, List<Integer>> barrierVal = barriers.getBarriers().get(index);
        List<Integer> threads = barrierVal.getValue();

        int n1 = barrierVal.getKey();
        int nl = threads.size();

        if(n1 > nl)
        {
            if(threads.contains(state.getID()))
            {
                exeStack.push(this);
                state.setExeStack(exeStack);
            } else {
                threads.add(state.getID());
                barriers.put(index, new Pair<>(n1, threads));
                state.setBarrierTable(barriers);

                exeStack.push(this);
                state.setExeStack(exeStack);
            }
        } else {
            // CIOCAN
        }

        lock.unlock();

        return null;
    }

    @Override
    public String toString() {
        return "await("+var+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}
