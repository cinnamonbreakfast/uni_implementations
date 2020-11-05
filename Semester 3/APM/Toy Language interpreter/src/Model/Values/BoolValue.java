package Model.Values;
import Model.Types.*;

public class BoolValue implements Value{
    boolean val;

    @Override
    public boolean equals(Object another)
    {
        if(another instanceof BoolValue)
        {
            return ((BoolValue) another).getVal() == this.getVal();
        }
        return false;
    }

    public BoolValue(boolean v){ val = v; }

    public boolean getVal() {return val;}

    public String toString() {
        return String.valueOf(getVal());
    }

    public Type getType() {
        return new BoolType();
    }

    public Value deepCopy()
    {
        return new BoolValue(val);
    }
}