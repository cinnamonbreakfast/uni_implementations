package Model.Types;

import Model.Values.IntValue;
import Model.Values.Value;

public class IntType implements Type {
    @Override
    public boolean equals(Object another){
        return another instanceof IntType;
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

