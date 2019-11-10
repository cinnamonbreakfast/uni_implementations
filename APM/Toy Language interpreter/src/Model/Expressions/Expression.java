package Model.Expressions;

import Model.Containers.IDictionary;
import Model.Values.Value;
import Model.Exceptions.MyException;

public interface Expression {
    Value evaluate(IDictionary<String,Value> tbl) throws MyException;
}

