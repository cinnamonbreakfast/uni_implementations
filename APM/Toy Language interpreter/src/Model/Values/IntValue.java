package Model.Values;
import Model.Types.*;

public class IntValue implements Value{
    int val;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof IntValue)
        {
            return ((IntValue) obj).getVal() == this.getVal();
        }
        return false;
    }

    public IntValue(int v){ val = v; }

    public int getVal() { return val; }

    public String toString() {
        return String.valueOf(getVal());
    }

    public Type getType() {
        return new IntType();
    }

    public Value deepCopy()
    {
        return new IntValue(val);
    }
}
