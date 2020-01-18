package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
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
        MyDictionary<String, Value> table = (MyDictionary<String, Value>) state.getSymTable();
        MyDictionary<StringValue, BufferedReader> filetable = (MyDictionary<StringValue, BufferedReader>) state.getFileTable();
        MyHeap<Value> heap = (MyHeap<Value>) state.getHeap();

        Value fname = exp.evaluate(table, heap);

        if(table.contains(varName))
        {
            if(table.get(varName).getType().equals(new IntType()))
            {
                Value val = exp.evaluate(table, heap);

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

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typeCheck(typeEnv);
        Type typeVar = typeEnv.get(varName);

        if(!(typeExp.equals(new StringType())))
            throw new MyException("File name is not a string.");

        return typeEnv;
    }

    public IStatement deepcopy()
    {
        return new ReadFileStatement(exp, varName);
    }
}
