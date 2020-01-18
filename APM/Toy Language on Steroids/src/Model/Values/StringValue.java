package Model.Values;
import Model.Types.*;

public class StringValue implements Value{
    private String val;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof StringValue)
        {
            return ((StringValue) obj).getVal() == this.getVal();
        }
        return false;
    }

    public StringValue(String v){ val = v; }

    public String getVal() { return val; }

    public String toString() {
        return val;
    }

    public Type getType() {
        return new StringType();
    }

    public Value deepCopy()
    {
        return new StringValue(val);
    }
}