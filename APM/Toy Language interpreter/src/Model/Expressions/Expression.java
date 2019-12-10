package Model.Expressions;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Values.Value;

public interface Expression {
    Value evaluate(MyDictionary<String,Value> tbl, MyHeap<Value> heap) throws MyException;
}

