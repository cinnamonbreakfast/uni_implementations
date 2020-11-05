package Model.Expressions;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Types.RefType;
import Model.Values.RefValue;
import Model.Values.Value;
import kotlin.reflect.jvm.internal.ReflectProperties;

public class WHeapExpression implements Expression {
    private Expression exp;
    private String var;

    public WHeapExpression(String var, Expression exp) {
        this.exp = exp;
        this.var = var;
    }

    public WHeapExpression(Expression exp, String var) {
        this.exp = exp;
        this.var = var;
    }

    @Override
    public Value evaluate(MyDictionary<String, Value> tbl, MyHeap<Value> heap) throws MyException {
        Value ref = exp.evaluate(tbl, heap);

        if(tbl.contains(var))
        {
            Value sym = tbl.get(var);
            if(sym.getType() instanceof RefType)
            {
                int addr = ((RefValue)sym).getAddr();

                if(heap.contains(addr))
                {
                    if(ref.getType().equals(heap.get(addr).getType()))
                    {
                        heap.put(addr, ref);
                        return ref;
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
