package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStatement implements IStatement {
    private Expression exp;

    public OpenRFileStatement(Expression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionary<StringValue, BufferedReader> fileTable = (MyDictionary<StringValue, BufferedReader>) state.getFileTable();
        MyDictionary<String, Value> table = (MyDictionary<String, Value>) state.getSymTable();
        MyHeap<Value> heap = (MyHeap<Value>) state.getHeap();

        Value filename = this.exp.evaluate(table, heap);

        if(filename.getType().equals(new StringType()))
        {
            if(!fileTable.contains((StringValue) filename))
            {
                String filepath = ((StringValue)filename).getVal();

                BufferedReader br = null;
                try {
                    br = new BufferedReader(
                            new FileReader(filepath)
                    );

                    fileTable.put((StringValue)filename, br);
                } catch (IOException e) {
                    throw new MyException(e.getMessage());
                }
            } else {
                throw new MyException("Reuse of a symbol that is already declared, "+((StringValue) filename).getVal()+".");
            }
        } else {
            throw new MyException("Expected a string for file location. Received " + filename.getType() + " instead.");
        }

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type type = exp.typeCheck(typeEnv);

        if(!(type.equals(new StringType())))
            throw new MyException("Name of file to open is not a string.");

        return typeEnv;
    }

    @Override
    public String toString()
    {
        return "openRFile";
    }
}
