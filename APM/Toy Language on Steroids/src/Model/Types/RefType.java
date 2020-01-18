package Model.Types;

import Model.Values.IntValue;
import Model.Values.RefValue;
import Model.Values.Value;

import java.sql.Ref;

public class RefType implements Type {
    private Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof RefType)
        {
            return inner.equals(((RefType) obj).getInner());
        } else return false;
    }

    @Override
    public Value getDefaultValue() {
        return new RefValue(inner, 0);
    }

    public Type getInner() {
        return inner;
    }



    @Override
    public Type deepCopy() {
        return new RefType(inner);
    }

    @Override
    public String toString() {
        return "*"+inner;
    }
}
