package Model.Types;

import Model.Values.Value;

public interface Type {
    Value getDefaultValue();
    boolean equals(Object another);
    Type deepCopy();
}
