package Model.Statements;

import Model.Containers.IDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {
    private Expression exp;
    private String varName;

    public ReadFileStatement(Expression exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public String toString()
    {
        return "Reading " + this.exp.toString() + " - " + varName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, Value> table = state.getSymTable();
        IDictionary<StringValue, BufferedReader> filetable = state.getFileTable();

        Value fname = exp.evaluate(table);

        if(table.contains(varName))
        {
            if(table.get(varName).getType().equals(new IntType()))
            {
                Value val = exp.evaluate(table);

                if(val.getType().equals(new StringType()))
                {
                    BufferedReader br = filetable.get((StringValue) val);

                    if(br != null)
                    {
                        try {
                            String line = br.readLine();
                            IntValue intVal;

                            if(line == null)
                            {
                                intVal = new IntValue(0);
                            } else {
                                intVal = new IntValue(Integer.parseInt(line));
                            }

                            table.put(varName, intVal);
                        }
                        catch (IOException ioe) {
                            throw new MyException(ioe.getMessage());
                        }

                    } else {
                        throw new MyException("No file descriptor associated to given symbol. Missing its reference?");
                    }
                } else {
                    throw new MyException("Given path is not a String Type. Received "+val.getType()+" instead.");
                }
            } else {
                throw new MyException("Given value type is not an integer.");
            }
        } else {
            throw new MyException("Cannot find any variable for given symbol '"+varName+"'. Missing its declaration?");
        }

        return state;
    }

    public IStatement deepcopy()
    {
        return new ReadFileStatement(exp, varName);
    }
}
