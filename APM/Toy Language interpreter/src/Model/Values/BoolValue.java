package Model.Values;
import Model.Types.*;

public class BoolValue implements Value{
    boolean val;

    public BoolValue(boolean v){ val = v; }

    public boolean getVal() {return val;}

    public String toString() {
        return String.valueOf(getVal());
    }

    public Type getType() {
        return new BoolType();
    }
}