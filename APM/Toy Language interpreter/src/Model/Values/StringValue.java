package Model.Values;
import Model.Types.*;

public class StringValue implements Value{
    String val;

    public StringValue(String v){ val = v; }

    public String getVal() { return val; }

    public String toString() {
        return val;
    }

    public Type getType() {
        return new StringType();
    }
}