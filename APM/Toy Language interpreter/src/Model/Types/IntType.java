package Model.Types;

import Model.Values.IntValue;
import Model.Values.Value;

public class IntType implements Type {
    public boolean equals(Object another){
        if (another instanceof IntType)
            return true;
        else
            return false;
    }
    public String toString() {
        return "int";
    }

    @Override
    public Value getDefaultValue() {
        return new IntValue(0);
    }

    @Override
    public Type deepCopy() {
        return new IntType();
    }
}

