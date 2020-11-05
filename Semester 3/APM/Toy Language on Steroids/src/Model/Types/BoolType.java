package Model.Types;

import Model.Values.BoolValue;
import Model.Values.Value;

public class BoolType implements Type {
    @Override
    public boolean equals(Object another){
        return another instanceof BoolType;
    }
    public String toString() {
        return "boolean";
    }

    @Override
    public Value getDefaultValue() {
        return new BoolValue(false);
    }

    @Override
    public Type deepCopy() {
        return new BoolType();
    }
}
