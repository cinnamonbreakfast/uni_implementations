package Model.Statements;

import Model.Containers.*;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStatement implements IStatement
{
    private String var;
    private static Lock lock = new ReentrantLock();

    public AcquireStatement(String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();

        try {
            MySemaphore semaphores = (MySemaphore) state.getSemaphoreTable();
            MyStack<IStatement> exeStack = (MyStack<IStatement>) state.getExeStack();

            if(!state.getSymTable().contains(var))
                throw new MyException("Acquire: No entry in symbol table for symbol "+var +".");

            int foundIndex = ((IntValue) state.getSymTable().get(var)).getVal();

            if(!semaphores.exists(foundIndex))
                throw new MyException("Acquire: No entry in semaphores table for index "+foundIndex+".");

            IDictionary<Integer, Triplet<Integer, List<Integer>, Integer>> semaphore = semaphores.getSemaphore();

            Triplet<Integer, List<Integer>, Integer> entryTriplet = semaphore.get(foundIndex);

            if((entryTriplet.getFirst() - entryTriplet.getThird()) > entryTriplet.getSecond().size())
            {
                if(entryTriplet.getSecond().contains(state.getID())) {
                    // CIOCAN
                } else {
                    entryTriplet.getSecond().add(state.getID());
                }
            } else {
                state.getExeStack().push(this);
            }


            //lock.unlock();
        } catch (MyException e)
        {

        }
        finally {
            lock.unlock();
        }

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.get(var);

        if(!typeVar.equals(new IntType()))
            throw new MyException("Semaphore var " + var + " not an integer.");

        return typeEnv;
    }
}