package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Containers.MyStack;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Expressions.RelationalExpression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public class SwitchStatement implements IStatement {
    private Expression exp;
    private Expression exp1;
    private Expression exp2;
    private IStatement stmt1;
    private IStatement stmt2;
    private IStatement stmt3;

    public SwitchStatement(Expression exp, Expression exp1, Expression exp2, IStatement stmt1, IStatement stmt2, IStatement stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyStack<IStatement> stack = (MyStack<IStatement>) state.getExeStack();
        MyDictionary<String, Value> table = (MyDictionary<String, Value>) state.getSymTable();
        MyHeap<Value> heap = (MyHeap<Value>) state.getHeap();

        Value v = exp.evaluate(table, heap);
        Value v1 = exp1.evaluate(table, heap);
        Value v2 = exp2.evaluate(table, heap);

        IStatement stmt = new IfStatement(
                new RelationalExpression(
                        exp,
                        exp1,
                        "=="
                ),
                stmt1,
                new IfStatement(
                        new RelationalExpression(
                                exp,
                                exp2,
                                "=="
                        ),
                        stmt2,
                        stmt3
                )
        );

        stack.push(stmt);
        state.setExeStack(stack);

        return null;
    }

    @Override
    public String toString() {
        return "switch{" + exp + "}"+
                "((case "+ exp1 + "): " + stmt1 + ")"+
                "((caes "+ exp2 + "): " + stmt2 + ")"+
                "(default: "+stmt3+")";
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
