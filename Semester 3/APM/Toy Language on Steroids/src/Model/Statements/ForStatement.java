package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.Type;

public class ForStatement implements IStatement {
    private IStatement st1;
    private Expression ex;
    private IStatement st2;
    private IStatement st3;

    public ForStatement(IStatement st1, Expression ex, IStatement st2, IStatement st3) {
        this.st1 = st1;
        this.ex = ex;
        this.st2 = st2;
        this.st3 = st3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IStatement sc1 = new WhileStatement(
                ex,
                new CompStatement(
                        st3,
                        st2
                )
        );

        IStatement sc2 = new CompStatement(
                st1,
                sc1
        );

        state.getExeStack().push(sc2);

        return null;
    }

    @Override
    public String toString() {
        return "for(" +
                st1 +
                "; " + ex +
                "; " + st2 +
                ") {" + st3 +
                '}';
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}
