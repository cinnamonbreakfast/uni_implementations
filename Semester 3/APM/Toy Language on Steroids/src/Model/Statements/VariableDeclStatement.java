package Model.Statements;

import Model.Containers.IDictionary;
import Model.Containers.MyDictionary;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

import java.util.Map;

public class VariableDeclStatement implements IStatement {
    private String name;
    private Type type;

    public VariableDeclStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, Value> table = state.getSymTable();

        if(!table.contains(name))
        {
            Value defValue = this.type.getDefaultValue();
            table.put(name, defValue);

            return null;
        } else
            throw new MyException("Variable name already in use.");
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        MyDictionary<String, Type> newEnv = new MyDictionary<>();

        for(Map.Entry<String, Type> entry : typeEnv.getMap().entrySet())
            newEnv.put(entry.getKey(), entry.getValue().deepCopy());
        newEnv.put(name, type);

        return newEnv;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }

    public IStatement deepCopy()
    {
        return new VariableDeclStatement(name, type);
    }
}
