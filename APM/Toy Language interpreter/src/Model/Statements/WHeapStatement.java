package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.RefType;
import Model.Values.RefValue;
import Model.Values.Value;

public class WHeapStatement implements IStatement {
    private Expression exp;
    private String var;

    public WHeapStatement(String var, Expression exp) {
        this.exp = exp;
        this.var = var;
    }


    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionary<String , Value> table = (MyDictionary<String, Value>) state.getSymTable();
        MyHeap<Value> heap = (MyHeap<Value>) state.getHeap();

        Value ref = exp.evaluate(table, heap);

        if(table.contains(var))
        {
            Value sym = table.get(var);
            if(sym instanceof RefValue)
            {
                int addr = ((RefValue)sym).getAddr();

                if(heap.contains(addr))
                {
                    if(ref.getType().equals(heap.get(addr).getType()))
                    {
                        heap.put(addr, ref);
                        return null;
                    } else
                        throw new MyException("Cannot push another value type in allocated space.");
                } else
                    throw new MyException("Trying to handle unallocated space.");
            } else
                throw new MyException("Variable type must be reference.");
        } else
            throw new MyException("Symbol name does not exist in table. Missing its declaration.");
    }
}
