package Model.Statements;

import Model.Containers.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.*;

public class OpenRFileStatement implements IStatement {
    private Expression exp;

    public OpenRFileStatement(Expression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        IDictionary<String, Value> table = state.getSymTable();

        Value filename = this.exp.evaluate(table);

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

        return state;
    }

    @Override
    public String toString()
    {
        return "openRFile";
    }
}
