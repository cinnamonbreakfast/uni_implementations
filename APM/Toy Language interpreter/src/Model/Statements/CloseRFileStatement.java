package Model.Statements;

import Model.Containers.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;
import com.sun.jdi.InconsistentDebugInfoException;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements IStatement {
    private Expression exp;

    public CloseRFileStatement(Expression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<StringValue, BufferedReader> filetable = state.getFileTable();
        IDictionary<String, Value> table = state.getSymTable();

        Value val = exp.evaluate(table);

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

        return state;
    }

    @Override
    public String toString()
    {
        return "closeRFile";
    }
}