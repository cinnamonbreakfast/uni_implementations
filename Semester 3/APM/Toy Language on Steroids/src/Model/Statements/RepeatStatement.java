package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Containers.MyStack;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Expressions.NotExpression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public class RepeatStatement implements IStatement {
    private Expression exp;
    private IStatement stmt;

    public RepeatStatement(Expression exp, IStatement stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStatement repeater =
                new CompStatement(
                        stmt,
                        new WhileStatement(
                            new NotExpression(exp),
                                stmt
                        )
                );

        state.getExeStack().push(repeater);

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}
