package Model.Types;

import Model.Values.BoolValue;
import Model.Values.Value;

public class BoolType implements Type {
    public boolean equals(Object another){
        if (another instanceof BoolType)
            return true;
        else
            return false;
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
