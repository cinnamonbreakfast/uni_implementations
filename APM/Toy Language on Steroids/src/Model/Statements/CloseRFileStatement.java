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
import java.io.IOException;

public class CloseRFileStatement implements IStatement {
    private Expression exp;

    public CloseRFileStatement(Expression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionary<StringValue, BufferedReader> filetable = (MyDictionary<StringValue, BufferedReader>) state.getFileTable();
        MyDictionary<String, Value> table = (MyDictionary<String, Value>) state.getSymTable();
        MyHeap<Value> heap = (MyHeap<Value>) state.getHeap();

        Value val = exp.evaluate(table, heap);

        if(val.getType().equals(new StringType()))
        {
            if(filetable.contains((StringValue)val))
            {
                BufferedReader br = filetable.get((StringValue)val);
                if(br != null)
                {
                    try
                    {
                        br.close();
                        filetable.remove((StringValue)val);
                    }
                    catch (IOException e)
                    {
                        throw new MyException(e.getMessage());
                    }
                } else {
                    throw new MyException("File not opened");
                }
            } else {
                throw new MyException("Cannot find any open file descriptor with the given symbol name.");
            }
        } else {
            throw new MyException("Expected a file name of a file descriptor. Received " + val.getType() + " instead.");
        }

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type type = exp.typeCheck(typeEnv);

        if(!(type.equals(new StringType())))
            throw new MyException("Name of file to close is not a string.");

        return typeEnv;
    }

    @Override
    public String toString()
    {
        return "closeRFile";
    }
}
