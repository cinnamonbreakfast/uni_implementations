package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

public class RefValue implements Value {
    private int addr;
    private Type locType;

    public RefValue(Type locType, int addr) {
        this.addr = addr;
        this.locType = locType;
    }

    public Type getLocType()
    {
        return locType;
    }

    public int getAddr() {
        return addr;
    }

    @Override
    public Type getType() {
        return new RefType(locType);
    }

    @Override
    public String toString() {
        return addr+"@"+locType;
    }

    public Value deepCopy()
    {
        return new RefValue(locType, addr);
    }
}
