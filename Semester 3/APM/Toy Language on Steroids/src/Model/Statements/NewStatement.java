package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.sql.Ref;

public class NewStatement implements IStatement {
    private Expression exp;
    private String var_name;

    public NewStatement(String var_name, Expression exp) {
        this.exp = exp;
        this.var_name = var_name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionary<String, Value> table = (MyDictionary<String, Value>)state.getSymTable();
        MyHeap<Value> heap = (MyHeap<Value>) state.getHeap();

        if(table.contains(var_name))
        {
            if(table.get(var_name).getType() instanceof RefType)
            {
                Value val = exp.evaluate(table, heap);
                Value tabVal = table.get(var_name);

//                System.out.println("\n\n\n----");
//                System.out.println(tabVal.getType());
//                System.out.println(((RefValue)tabVal).getLocType());
//                System.out.println(val.getType().equals(((RefValue)tabVal).getLocType()));
//                System.out.println("\n\n\n----");

//                if(table.get(var_name).getType().equals(val.getType()))
                if(val.getType().equals(((RefValue)tabVal).getLocType()))
                {
                    int addr = state.getHeap().allocate(val);
                    table.put(var_name, new RefValue(val.getType(), addr));
                } else
                    throw new MyException("Given expression does not match the symbol type.");

            } else throw new MyException("Symbol is not a reference of an instance.");
        } else
            throw new MyException("Cannot allocate on a undeclared symbol.");

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.get(var_name);
        Type typeExp = exp.typeCheck(typeEnv);

        if(!(typeVar.equals(new RefType(typeExp))))
            throw new MyException("Right and left hand side have different types [assign].");

        return typeEnv;
    }

    @Override
    public String toString() {
        return var_name +" -> "+exp;
    }
}
