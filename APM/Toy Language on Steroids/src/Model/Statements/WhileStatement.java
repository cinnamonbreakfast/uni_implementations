package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;
import kotlin.reflect.jvm.internal.ReflectProperties;

public class WhileStatement implements IStatement {
    private Expression exp;
    private IStatement stat;

    public WhileStatement(Expression exp, IStatement stat) {
        this.exp = exp;
        this.stat = stat;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionary<String, Value> table = (MyDictionary<String, Value>) state.getSymTable();
        MyHeap<Value> heap = (MyHeap<Value>) state.getHeap();

        Value val = exp.evaluate(table, heap);

        if(val.getType().equals(new BoolType()))
        {
            BoolValue bv = (BoolValue)val;
            if(bv.getVal())
            {
                state.getExeStack().push(new WhileStatement(exp, stat));
                state.getExeStack().push(stat);
            }
        } else
            throw new MyException("Invalid state type. Expected boolean.");

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typeCheck(typeEnv);

        if(!(typeExp.equals(new BoolType())))
            throw new MyException("Loop condition is not a boolean type.");

        stat.typeCheck(typeEnv);

        return typeEnv;
    }

    @Override
    public String toString() {
        return "while("+exp+") { \n\t" + stat + " \n\t}";
    }
}
