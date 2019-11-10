package Model.Values;
import Model.Types.*;

public class IntValue implements Value{
    int val;

    public IntValue(int v){ val = v; }

    public int getVal() { return val; }

    public String toString() {
        return String.valueOf(getVal());
    }

    public Type getType() {
        return new IntType();
    }
}
