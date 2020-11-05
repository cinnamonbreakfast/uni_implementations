package Model.Statements;

import Model.Containers.*;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public class PrintStatement implements IStatement {
    private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "print(\""+expression+"\"); ";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyDictionary<String, Value> table = (MyDictionary<String, Value>) state.getSymTable();
        MyList<Value> out = (MyList<Value>) state.getOut();
        MyHeap<Value> heap = (MyHeap<Value>) state.getHeap();

        out.add(expression.evaluate(table, heap));

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    public PrintStatement deepCopy()
    {
        return new PrintStatement(expression);
    }
}
