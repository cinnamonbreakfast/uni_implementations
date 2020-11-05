package View;

import Model.Statements.*;
import Model.Expressions.*;
import Model.Types.*;
import Model.Values.*;
import Model.Types.IntType;
import Model.Values.IntValue;

public class Programs {

    public IStatement getProgram(int pr)
    {
        if(pr == 1) return pr1();
        else if(pr == 2) return pr2();
        else if(pr == 3) return pr3();
        else if(pr == 4) return pr4();
        else if(pr == 5) return pr5();
        else if(pr == 6) return pr6();
        else if(pr == 7) return pr7();
        else if(pr == 8) return pr8();
        else if(pr == 9) return pr9();
        else if(pr == 10) return pr10();
        else if(pr == 11) return pr11();
        else if(pr == 12) return pr12();
        else if(pr == 13) return pr13();
        else if(pr == 14) return pr14();
        else if(pr == 15) return pr15();
        else if(pr == 16) return pr16();
        else if(pr == 17) return pr17();
        else if(pr == 18) return pr18();
        else if(pr == 19) return pr19();
        return null;
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

    private static IStatement pr5()
    {
        return new PrintStatement(
                new RelationalExpression(
                        new ValueExpression(new IntValue(5)),
                        new ValueExpression(new IntValue(7)),
                        ">"
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

    private static IStatement pr6()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "v",
                        new RefType(
                                new IntType()
                        )
                ),
                new CompStatement(
                        new NewStatement(
                                "v",
                                new ValueExpression(
                                        new IntValue(20)
                                )
                        ),
                        new CompStatement(
                                new PrintStatement(
                                        new RHeapExpression(
                                                new VariableExpression("v")
                                        )
                                ),
                                new CompStatement(
                                        new VariableDeclStatement(
                                                "a",
                                                new RefType(
                                                        new RefType(
                                                                new IntType()
                                                        )
                                                )
                                        ),
                                        new CompStatement(
                                                new NewStatement(
                                                        "a",
                                                        new VariableExpression("v")
                                                ),
                                                new CompStatement(
                                                        new WHeapStatement(
                                                                "v",
                                                                new ValueExpression(
                                                                        new IntValue(30)
                                                                )
                                                        ),
                                                        new PrintStatement(
                                                                new ArithmeticalExpression(
                                                                        "+",
                                                                        new RHeapExpression(
                                                                                new RHeapExpression(
                                                                                        new VariableExpression(
                                                                                                "a"
                                                                                        )
                                                                                )
                                                                        ),
                                                                        new ValueExpression(
                                                                                new IntValue(5)
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

    private static IStatement pr7()
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
                                        new IntValue(4)
                                )
                        ),
                        new CompStatement(
                                new WhileStatement(
                                        new RelationalExpression(
                                                new VariableExpression("v"),
                                                new ValueExpression(
                                                        new IntValue(0)
                                                ),
                                                ">"
                                        ),
                                        new CompStatement(
                                                new PrintStatement(
                                                        new VariableExpression("v")
                                                ),
                                                new AssignStatement(
                                                        "v",
                                                        new ArithmeticalExpression(
                                                                "-",
                                                                new VariableExpression("v"),
                                                                new ValueExpression(
                                                                        new IntValue(1)
                                                                )
                                                        )
                                                )
                                        )
                                ),
                                new PrintStatement(
                                        new VariableExpression("v")
                                )
                        )
                )
        );
    }

    public static IStatement pr8()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "v",
                        new RefType(
                                new IntType()
                        )
                ),
                new CompStatement(
                        new NewStatement(
                                "v",
                                new ValueExpression(
                                        new IntValue(20)
                                )
                        ),
                        new CompStatement(
                                new VariableDeclStatement(
                                        "a",
                                        new RefType(
                                                new RefType(
                                                        new IntType()
                                                )
                                        )
                                ),
                                new CompStatement(
                                        new NewStatement(
                                                "a",
                                                new VariableExpression("v")
                                        ),
                                        new CompStatement(
                                                new NewStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntValue(30)
                                                        )
                                                ),
                                                new PrintStatement(
                                                        new RHeapExpression(
                                                                new RHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static IStatement pr9()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "v",
                        new RefType(
                                new IntType()
                        )
                ),
                new CompStatement(
                        new NewStatement(
                                "v",
                                new ValueExpression(
                                        new IntValue(20)
                                )
                        ),
                        new CompStatement(
                                new PrintStatement(
                                        new RHeapExpression(
                                                new VariableExpression("v")
                                        )
                                ),
                                new CompStatement(
                                        new NewStatement(
                                                "v",
                                                new ValueExpression(
                                                        new IntValue(26)
                                                )
                                        ),
                                        new PrintStatement(
                                                new RHeapExpression(
                                                        new VariableExpression("v")
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static IStatement pr10()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "v",
                        new RefType(
                                new IntType()
                        )
                ),
                new CompStatement(
                        new NewStatement(
                                "v",
                                new ValueExpression(
                                        new IntValue(20)
                                )
                        ),
                        new CompStatement(
                                new VariableDeclStatement(
                                        "a",
                                        new RefType(
                                                new RefType(
                                                        new IntType()
                                                )
                                        )
                                ),
                                new CompStatement(
                                        new NewStatement(
                                                "a",
                                                new VariableExpression("v")
                                        ),
                                        new CompStatement(
                                                new PrintStatement(
                                                        new RHeapExpression(
                                                                new VariableExpression("v")
                                                        )
                                                ),
                                                new PrintStatement(
                                                        new RHeapExpression(
                                                                new RHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static IStatement pr11()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "v",
                        new IntType()
                ),
                new CompStatement(
                        new VariableDeclStatement(
                                "a",
                                new RefType(
                                        new IntType()
                                )
                        ),
                        new CompStatement(
                                new AssignStatement(
                                        "v",
                                        new ValueExpression(
                                                new IntValue(10)
                                        )
                                ),
                                new CompStatement(
                                        new NewStatement(
                                                "a",
                                                new ValueExpression(
                                                        new IntValue(22)
                                                )
                                        ),
                                        new CompStatement(
                                                new Fork(
                                                        new CompStatement(
                                                                new WHeapStatement(
                                                                        "a",
                                                                        new ValueExpression(
                                                                                new IntValue(30)
                                                                        )
                                                                ),
                                                                new CompStatement(
                                                                        new AssignStatement(
                                                                                "v",
                                                                                new ValueExpression(
                                                                                        new IntValue(32)
                                                                                )
                                                                        ),
                                                                        new CompStatement(
                                                                                new PrintStatement(
                                                                                        new VariableExpression("v")
                                                                                ),
                                                                                new PrintStatement(
                                                                                        new RHeapExpression(
                                                                                                new VariableExpression("a")
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompStatement(
                                                        new PrintStatement(
                                                                new VariableExpression("v")
                                                        ),
                                                        new PrintStatement(
                                                                new RHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static IStatement pr12()
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
                                        new IntValue(1)
                                )
                        ),
                        new CompStatement(
                                new Fork(
                                        new CompStatement(
                                                new AssignStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntValue(2)
                                                        )
                                                ),
                                                new PrintStatement(
                                                        new VariableExpression("v")
                                                )
                                        )
                                ),
                                new Fork(
                                        new CompStatement(
                                                new AssignStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntValue(3)
                                                        )
                                                ),
                                                new PrintStatement(
                                                        new VariableExpression("v")
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static IStatement pr13()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "v1",
                        new RefType(
                                new IntType()
                        )
                ),
                new CompStatement(
                        new VariableDeclStatement(
                                "cnt",
                                new IntType()
                        ),
                        new CompStatement(
                                new NewStatement(
                                        "v1",
                                        new ValueExpression(
                                                new IntValue(2)
                                        )
                                ),
                                new CompStatement(
                                        new NewSemaphoreStatement(
                                                "cnt",
                                                new RHeapExpression(
                                                        new VariableExpression("v1")
                                                ),
                                                new ValueExpression(new IntValue(1))
                                        ),
                                        new CompStatement(
                                                new Fork(
                                                        new CompStatement(
                                                                new AcquireStatement("cnt"),
                                                                new CompStatement(
                                                                        new WHeapStatement(
                                                                                "v1",
                                                                                new ArithmeticalExpression(
                                                                                        "*",
                                                                                        new RHeapExpression(
                                                                                                new VariableExpression("v1")
                                                                                        ),
                                                                                        new ValueExpression(new IntValue(10))
                                                                                )
                                                                        ),
                                                                        new CompStatement(
                                                                                new PrintStatement(
                                                                                        new RHeapExpression(
                                                                                                new VariableExpression("v1")
                                                                                        )
                                                                                ),
                                                                                new ReleaseStatement("cnt")
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompStatement(
                                                        new Fork(
                                                                new CompStatement(
                                                                        new AcquireStatement("cnt"),
                                                                        new CompStatement(
                                                                                new WHeapStatement(
                                                                                        "v1",
                                                                                        new ArithmeticalExpression(
                                                                                                "*",
                                                                                                new RHeapExpression(
                                                                                                        new VariableExpression("v1")
                                                                                                ),
                                                                                                new ValueExpression(new IntValue(10))
                                                                                        )
                                                                                ),
                                                                                new CompStatement(
                                                                                        new WHeapStatement(
                                                                                                "v1",
                                                                                                new ArithmeticalExpression(
                                                                                                        "*",
                                                                                                        new RHeapExpression(
                                                                                                                new VariableExpression("v1")
                                                                                                        ),
                                                                                                        new ValueExpression(new IntValue(2))
                                                                                                )
                                                                                        ),
                                                                                        new CompStatement(
                                                                                                new PrintStatement(
                                                                                                        new RHeapExpression(
                                                                                                                new VariableExpression("v1")
                                                                                                        )
                                                                                                ),
                                                                                                new ReleaseStatement("cnt")
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        ),
                                                        new CompStatement(
                                                                new AcquireStatement("cnt"),
                                                                new CompStatement(
                                                                        new PrintStatement(
                                                                                new ArithmeticalExpression(
                                                                                        "-",
                                                                                        new RHeapExpression(
                                                                                                new VariableExpression("v1")
                                                                                        ),
                                                                                        new ValueExpression(new IntValue(1))
                                                                                )
                                                                        ),
                                                                        new ReleaseStatement("cnt")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static IStatement pr14()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "b",
                        new BoolType()
                ),
                new CompStatement(
                        new VariableDeclStatement(
                                "c",
                                new IntType()
                        ),
                        new CompStatement(
                                new AssignStatement(
                                        "b",
                                        new ValueExpression(new BoolValue(true))
                                ),
                                new CompStatement(
                                        new CondAssignStatement(
                                                "c",
                                                new VariableExpression("b"),
                                                new ValueExpression(new IntValue(100)),
                                                new ValueExpression(new IntValue(200))
                                        ),
                                        new CompStatement(
                                                new PrintStatement(
                                                        new VariableExpression("c")
                                                ),
                                                new CompStatement(
                                                        new CondAssignStatement(
                                                                "c",
                                                                new ValueExpression(new BoolValue(false)),
                                                                new ValueExpression(new IntValue(100)),
                                                                new ValueExpression(new IntValue(200))
                                                        ),
                                                        new PrintStatement(
                                                                new VariableExpression("c")
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static IStatement pr15()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "v1",
                        new RefType(
                                new IntType()
                        )
                ),
                new CompStatement(
                        new VariableDeclStatement(
                                "v2",
                                new RefType(
                                        new IntType()
                                )
                        ),
                        new CompStatement(
                                new VariableDeclStatement(
                                        "v3",
                                        new RefType(
                                                new IntType()
                                        )
                                ),
                                new CompStatement(
                                        new VariableDeclStatement(
                                                "cnt",
                                                new IntType()
                                        ),
                                        new CompStatement(
                                                new NewStatement(
                                                        "v1",
                                                        new ValueExpression(
                                                                new IntValue(2)
                                                        )
                                                ),
                                                new CompStatement(
                                                        new NewStatement(
                                                                "v2",
                                                                new ValueExpression(
                                                                        new IntValue(3)
                                                                )
                                                        ),
                                                        new CompStatement(
                                                                new NewStatement(
                                                                        "v3",
                                                                        new ValueExpression(
                                                                                new IntValue(4)
                                                                        )
                                                                ),
                                                                new CompStatement(
                                                                        new NewBarrierStatement(
                                                                                "cnt",
                                                                                new RHeapExpression(
                                                                                        new VariableExpression("v2")
                                                                                )
                                                                        ),
                                                                        new CompStatement(
                                                                                new Fork(
                                                                                        new CompStatement(
                                                                                                new AwaitStatement(
                                                                                                        "cnt"
                                                                                                ),
                                                                                                new CompStatement(
                                                                                                        new WHeapStatement(
                                                                                                                "v1",
                                                                                                                new ArithmeticalExpression(
                                                                                                                        "*",
                                                                                                                        new RHeapExpression(
                                                                                                                                new VariableExpression("v1")
                                                                                                                        ),
                                                                                                                        new ValueExpression(
                                                                                                                                new IntValue(10)
                                                                                                                        )
                                                                                                                )
                                                                                                        ),
                                                                                                        new PrintStatement(
                                                                                                                new RHeapExpression(
                                                                                                                        new VariableExpression("v1")
                                                                                                                )
                                                                                                        )
                                                                                                )
                                                                                        )
                                                                                ),
                                                                                new CompStatement(
                                                                                        new Fork(
                                                                                                new CompStatement(
                                                                                                        new AwaitStatement(
                                                                                                                "cnt"
                                                                                                        ),
                                                                                                        new CompStatement(
                                                                                                                new WHeapStatement(
                                                                                                                        "v2",
                                                                                                                        new ArithmeticalExpression(
                                                                                                                                "*",
                                                                                                                                new RHeapExpression(
                                                                                                                                        new VariableExpression("v2")
                                                                                                                                ),
                                                                                                                                new ValueExpression(
                                                                                                                                        new IntValue(10)
                                                                                                                                )
                                                                                                                        )
                                                                                                                ),
                                                                                                                new CompStatement(
                                                                                                                        new WHeapStatement(
                                                                                                                                "v2",
                                                                                                                                new ArithmeticalExpression(
                                                                                                                                        "*",
                                                                                                                                        new RHeapExpression(
                                                                                                                                                new VariableExpression("v2")
                                                                                                                                        ),
                                                                                                                                        new ValueExpression(
                                                                                                                                                new IntValue(10)
                                                                                                                                        )
                                                                                                                                )
                                                                                                                        ),
                                                                                                                        new PrintStatement(
                                                                                                                                new RHeapExpression(
                                                                                                                                        new VariableExpression(
                                                                                                                                                "v2"
                                                                                                                                        )
                                                                                                                                )
                                                                                                                        )
                                                                                                                )
                                                                                                        )
                                                                                                )
                                                                                        ),
                                                                                        new CompStatement(
                                                                                                new AwaitStatement(
                                                                                                        "cnt"
                                                                                                ),
                                                                                                new PrintStatement(
                                                                                                        new RHeapExpression(
                                                                                                                new VariableExpression(
                                                                                                                        "v3"
                                                                                                                )
                                                                                                        )
                                                                                                )
                                                                                        )
                                                                                )
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

    public static IStatement pr16()
    {
        return new CompStatement(
            new VariableDeclStatement(
                    "a",
                    new IntType()
            ),
            new CompStatement(
                    new AssignStatement(
                            "a",
                            new ValueExpression(
                                    new IntValue(1)
                            )
                    ),
                    new CompStatement(
                            new VariableDeclStatement(
                                    "b",
                                    new IntType()
                            ),
                            new CompStatement(
                                    new AssignStatement(
                                            "b",
                                            new ValueExpression(
                                                    new IntValue(2)
                                            )
                                    ),
                                    new CompStatement(
                                            new VariableDeclStatement(
                                                    "c",
                                                    new IntType()
                                            ),
                                            new CompStatement(
                                                    new AssignStatement(
                                                            "c",
                                                            new ValueExpression(
                                                                    new IntValue(5)
                                                            )
                                                    ),
                                                    new CompStatement(
                                                            new SwitchStatement(
                                                                    new ArithmeticalExpression(
                                                                            "*",
                                                                            new VariableExpression("a"),
                                                                            new ValueExpression(
                                                                                    new IntValue(10)
                                                                            )
                                                                    ),
                                                                    new ArithmeticalExpression(
                                                                            "*",
                                                                            new VariableExpression("b"),
                                                                            new VariableExpression("c")
                                                                    ),
                                                                    new ValueExpression(
                                                                            new IntValue(10)
                                                                    ),
                                                                    new CompStatement(
                                                                            new PrintStatement(
                                                                                    new VariableExpression("a")
                                                                            ),
                                                                            new PrintStatement(
                                                                                    new VariableExpression("b")
                                                                            )
                                                                    ),
                                                                    new CompStatement(
                                                                            new PrintStatement(
                                                                                    new ValueExpression(new IntValue(100))
                                                                            ),
                                                                            new PrintStatement(
                                                                                    new ValueExpression(new IntValue(200))
                                                                            )
                                                                    ),
                                                                    new PrintStatement(
                                                                            new ValueExpression(
                                                                                    new IntValue(300)
                                                                            )
                                                                    )
                                                            ),
                                                            new PrintStatement(
                                                                    new ValueExpression(
                                                                            new IntValue(300)
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

    public static IStatement pr17()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "v",
                        new IntType()
                ),
                new CompStatement(
                        new VariableDeclStatement(
                                "x",
                                new IntType()
                        ),
                        new CompStatement(
                                new VariableDeclStatement(
                                        "y",
                                        new IntType()
                                ),
                                new CompStatement(
                                        new AssignStatement(
                                                "v",
                                                new ValueExpression(
                                                        new IntValue(0)
                                                )
                                        ),
                                        new CompStatement(
                                                new RepeatStatement(
                                                        new RelationalExpression(
                                                                new VariableExpression("v"),
                                                                new ValueExpression(new IntValue(3)),
                                                                "=="
                                                        ),
                                                        new CompStatement(
                                                                new Fork(
                                                                        new CompStatement(
                                                                                new PrintStatement(
                                                                                        new VariableExpression("v")
                                                                                ),
                                                                                new AssignStatement(
                                                                                        "v",
                                                                                        new ArithmeticalExpression(
                                                                                                "-",
                                                                                                new VariableExpression("v"),
                                                                                                new ValueExpression(new IntValue(1))
                                                                                        )
                                                                                )
                                                                        )
                                                                ),
                                                                new AssignStatement(
                                                                        "v",
                                                                        new ArithmeticalExpression(
                                                                                "+",
                                                                                new VariableExpression("v"),
                                                                                new ValueExpression(new IntValue(1))
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompStatement(
                                                        new AssignStatement(
                                                                "x",
                                                                new ValueExpression(
                                                                        new IntValue(1)
                                                                )
                                                        ),
                                                        new CompStatement(
                                                                new NOpStatement(),
                                                                new CompStatement(
                                                                        new AssignStatement(
                                                                                "y",
                                                                                new ValueExpression(
                                                                                        new IntValue(3)
                                                                                )
                                                                        ),
                                                                        new CompStatement(
                                                                                new NOpStatement(),
                                                                                new PrintStatement(
                                                                                        new ArithmeticalExpression(
                                                                                                "*",
                                                                                                new VariableExpression("v"),
                                                                                                new ValueExpression(
                                                                                                        new IntValue(10)
                                                                                                )
                                                                                        )
                                                                                )
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

    public static IStatement pr18()
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
                                        new IntValue(20)
                                )
                        ),
                        new CompStatement(
                                new WaitStatement(
                                        new ValueExpression(
                                                new IntValue(10)
                                        )
                                ),
                                new PrintStatement(
                                        new ArithmeticalExpression(
                                                "*",
                                                new VariableExpression("v"),
                                                new ValueExpression(
                                                        new IntValue(10)
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static IStatement pr19()
    {
        return new CompStatement(
                new VariableDeclStatement(
                        "i",
                        new IntType()
                ),
                new CompStatement(
                        new AssignStatement(
                                "i",
                                new ValueExpression(
                                        new IntValue(0)
                                )
                        ),
                        new CompStatement(
                                new VariableDeclStatement(
                                        "n",
                                        new IntType()
                                ),
                                new CompStatement(
                                        new AssignStatement(
                                                "n",
                                                new ValueExpression(
                                                        new IntValue(10)
                                                )
                                        ),
                                        new ForStatement(
                                                new NOpStatement(),
                                                new RelationalExpression(
                                                        new VariableExpression("i"),
                                                        new VariableExpression("n"),
                                                        "<"
                                                ),
                                                new AssignStatement(
                                                        "i",
                                                        new ArithmeticalExpression(
                                                                "+",
                                                                new VariableExpression("i"),
                                                                new ValueExpression(
                                                                        new IntValue(1)
                                                                )
                                                        )
                                                ),
                                                new PrintStatement(
                                                        new VariableExpression("i")
                                                )
                                        )
                                )
                        )
                )
        );
    }
}
