package Model.Expressions;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Types.RefType;
import Model.Values.RefValue;
import Model.Values.Value;

public class RHeapExpression implements Expression {
    private Expression exp;

    public RHeapExpression(Expression exp) {
        this.exp = exp;
    }

    @Override
    public Value evaluate(MyDictionary<String, Value> tbl, MyHeap<Value> heap) throws MyException {
        Value ref = exp.evaluate(tbl, heap);

        if(ref instanceof RefValue)
        {
            int addr = ((RefValue)ref).getAddr();

            //Value heapVal = heap.contains(addr);

            if(heap.contains(addr))
            {
//                System.out.println("Got");
                return heap.get(addr);
            } else
                throw new MyException("Invalid address.");
        } else
            throw new MyException("Expression does not represent a reference in heap.");
    }

    @Override
    public String toString() {
        return "*"+exp;
    }
}
