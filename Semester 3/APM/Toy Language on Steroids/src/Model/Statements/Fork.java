package Model.Statements;

import Model.Containers.IDictionary;
import Model.Containers.MyDictionary;
import Model.Containers.MyStack;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

import java.util.Map;

public class Fork implements IStatement {
    private IStatement statement;

    public Fork(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, Value> newSymTable = new MyDictionary<>();

        for(Map.Entry<String, Value> entry : state.getSymTable().getMap().entrySet())
        {
            newSymTable.put(entry.getKey(), entry.getValue().deepCopy());
        }

        ProgramState newPS = new ProgramState(
                new MyStack<>(),
                state.getHeap(),
                newSymTable,
                state.getFileTable(),
                state.getOut(),
                state.getSemaphoreTable(), state.getBarrierTable(), this.statement
        );

        newPS.setNewID();

        return newPS;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        statement.typeCheck(typeEnv);

        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork(" + statement + ")";
    }
}
