package Model.Statements;

import Model.Containers.MyDictionary;
import Model.Containers.MyHeap;
import Model.Exceptions.MyException;
import Model.Expressions.Expression;
import Model.Expressions.ValueExpression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class WaitStatement implements IStatement {
    private Expression exp;

    public WaitStatement(Expression exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        int val = ((IntValue)exp.evaluate((MyDictionary<String, Value>) state.getSymTable(), (MyHeap<Value>) state.getHeap())).getVal();

        if(val > 0)
        {
            state.getExeStack().push(
                    new CompStatement(
                            new PrintStatement(
                                    new ValueExpression(
                                            new IntValue(val)
                                    )
                            ),
                            new WaitStatement(
                                    new ValueExpression(
                                            new IntValue(val-1)
                                    )
                            )
                    )
            );
        } else {
            // CIOCAN
        }

        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}
