package View;

import Controller.Controller;
import Model.Containers.*;
import Model.Exceptions.MyException;
import Model.Expressions.ArithmeticalExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;

import java.io.BufferedReader;

public class Interpreter {

    public static void main(String[] args)
    {
        IStack<IStatement> stack = new MyStack<IStatement>();
        IDictionary<String, Value> symTable = new MyDictionary<String, Value>();
        IDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
        IList<Value> out = new MyList<Value>();


        IStatement ex1 = pr1();

        ProgramState prg1 = new ProgramState(stack, symTable, fileTable, out, ex1);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctrl1 = new Controller(repo1);

        IStatement ex2 = pr2();

        ProgramState prg2 = new ProgramState(stack, symTable, fileTable, out, ex2);
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctrl2 = new Controller(repo2);

        IStatement ex3 = pr3();

        ProgramState prg3 = new ProgramState(stack, symTable, fileTable, out, ex3);
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller ctrl3 = new Controller(repo3);

        IStatement ex4 = pr4();

        ProgramState prg4 = new ProgramState(stack, symTable, fileTable, out, ex4);
        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller ctrl4 = new Controller(repo4);

        System.out.println("MUIE"+stack.size());

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctrl4));
        menu.show();


    }

    private static IStatement pr1()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "v",
                        new IntType()
                ),
                new CompStatement(
                        new AssignStatement(
                                "v",
                                new ValueExpression(
                                        new IntValue(2)
                                )
                        ),
                        new PrintStatement(
                                new VariableExpression(
                                        "v"
                                )
                        )
                )
        );
    }

    private static IStatement pr2()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "a",
                        new IntType()
                ),
                new CompStatement(
                        new VariableDeclStatement(
                                "b",
                                new IntType()
                        ),
                        new CompStatement(
                                new AssignStatement(
                                        "a",
                                        new ArithmeticalExpression(
                                                "+",
                                                new ValueExpression(
                                                        new IntValue(2)
                                                ),
                                                new ArithmeticalExpression(
                                                        "*",
                                                        new ValueExpression(
                                                                new IntValue(3)
                                                        ),
                                                        new ValueExpression(
                                                                new IntValue(5)
                                                        )
                                                )
                                        )
                                ),
                                new CompStatement(
                                        new AssignStatement(
                                                "b",
                                                new ArithmeticalExpression(
                                                        "+",
                                                        new VariableExpression("a"),
                                                        new ValueExpression(
                                                                new IntValue(1)
                                                        )
                                                )
                                        ),
                                        new PrintStatement(
                                                new VariableExpression("b")
                                        )
                                )
                        )
                )
        );
    }

    private static IStatement pr3()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "a",
                        new BoolType()
                ),
                new CompStatement(
                        new VariableDeclStatement(
                                "v",
                                new IntType()
                        ),
                        new CompStatement(
                                new AssignStatement(
                                        "a",
                                        new ValueExpression(
                                                new BoolValue(false)
                                        )
                                ),
                                new CompStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntValue(2)
                                                        )
                                                ),
                                                new AssignStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntValue(3)
                                                        )
                                                )
                                        ),
                                        new PrintStatement(
                                                new VariableExpression("v")
                                        )
                                )
                        )
                )
        );
    }

    private static IStatement pr4()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "varf",
                        new StringType()
                ),
                new CompStatement(
                        new AssignStatement(
                                "varf",
                                new ValueExpression(
                                        new StringValue("test.in")
                                )
                        ),
                        new CompStatement(
                                new OpenRFileStatement(
                                        new VariableExpression(
                                                "varf"
                                        )
                                ),
                                new CompStatement(
                                        new VariableDeclStatement(
                                                "varc",
                                                new IntType()
                                        ),
                                        new CompStatement(
                                                new ReadFileStatement(
                                                        new VariableExpression("varf"),
                                                        "varc"
                                                ),
                                                new CompStatement(
                                                        new PrintStatement(
                                                                new VariableExpression("varc")
                                                        ),
                                                        new CompStatement(
                                                                new ReadFileStatement(
                                                                        new VariableExpression("varf"),
                                                                        "varc"
                                                                ),
                                                                new CompStatement(
                                                                        new PrintStatement(
                                                                                new VariableExpression("varc")
                                                                        ),
                                                                        new CloseRFileStatement(
                                                                                new VariableExpression("varf")
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }
}
