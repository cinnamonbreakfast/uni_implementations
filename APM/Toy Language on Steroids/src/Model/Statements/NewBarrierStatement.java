package Model.Statements;

import Model.Containers.IBarrier;
import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStatement implements IStatement {
    private String var;
    private Expression exp;

    private static Lock lock = new ReentrantLock();

    public NewBarrierStatement(String var, Expression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();

        MyDictionary<String, Value> symTable = (MyDictionary<String, Value>) state.getSymTable();
        IBarrier barriers = state.getBarrierTable();

        Value raw = exp.evaluate(symTable, (MyHeap<Value>) state.getHeap());
        int location = barriers.getBarrierAddress();
        barriers.put(location, new Pair<>(((IntValue)raw).getVal(), new ArrayList<>()));

        symTable.put(var, new IntValue(location));

        state.setBarrierTable(barriers);
        state.setSymTable(symTable);

        lock.unlock();

        return null;
    }

    @Override
    public String toString() {
        return "newBarrier("+var+", "+exp+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
