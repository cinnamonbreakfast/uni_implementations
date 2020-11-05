package Model.Statements;

import Model.Containers.*;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import kotlin.reflect.jvm.internal.ReflectProperties;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewSemaphoreStatement implements IStatement {
    private Expression exp1;
    private Expression exp2;
    private String var;
    private static Lock lock = new ReentrantLock();

    public NewSemaphoreStatement(String var, Expression expression1, Expression expression2)
    {
        this.exp1 = expression1;
        this.exp2 = expression2;
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();
        System.out.println("pulaaa");
        try {
            IDictionary<String, Value> symTable = state.getSymTable();
            ISemaphore semaphoreTable = state.getSemaphoreTable();

            Value value1 = exp1.evaluate((MyDictionary<String, Value>) symTable, (MyHeap<Value>) state.getHeap());
            Value value2 = exp2.evaluate((MyDictionary<String, Value>) symTable, (MyHeap<Value>) state.getHeap());

            // type checking done before runtime

            int location = state.getSemaphoreTable().getSemaphoreAddress();

            if (symTable.contains(var)) {
                state.getSemaphoreTable().put(location, new Triplet<>(((IntValue) value1).getVal(), new ArrayList<>(), ((IntValue) value2).getVal()));
            } else
                throw new MyException("NewSemaphore: Semaphore " +var+ " not found.");

            //lock.unlock();
        }catch (MyException e) {
            throw e;
        } finally {
            System.out.println("pula 1");
            lock.unlock();
            System.out.println("pula 2");
        }


        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.get(var);

        exp1.typeCheck(typeEnv);
        exp1.typeCheck(typeEnv);

        if(!typeVar.equals(new IntType()))
            throw new MyException("Semaphore var " + var + " not an integer.");

        return typeEnv;
    }
}
