//package View;
//
//import Controller.Controller;
//import Model.Containers.*;
//import Model.Exceptions.MyException;
//import Model.Expressions.*;
//import Model.ProgramState;
//import Model.Statements.*;
//import Model.Types.*;
//import Model.Values.BoolValue;
//import Model.Values.IntValue;
//import Model.Values.StringValue;
//import Model.Values.Value;
//import Repository.IRepository;
//import Repository.Repository;
//
//import java.io.BufferedReader;
//
//public class Interpreter {
//
//    public static void main(String[] args)
//    {
//        // -------------------------- EX 1
//
//        IStack<IStatement> stack1 = new MyStack<IStatement>();
//        IHeap<Value> heap1 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable1 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out1 = new MyList<Value>();
//
//        IStatement ex1 = pr1();
//        IStatement ex2 = pr2();
//        IStatement ex3 = pr3();
//        IStatement ex4 = pr4();
//        IStatement ex5 = pr5();
//        IStatement ex6 = pr6();
//        IStatement ex7 = pr7();
//        IStatement ex8 = pr8();
//        IStatement ex9 = pr9();
//        IStatement ex10 = pr10();
//        IStatement ex11 = pr11();
//
//        int crPrg = 0;
//
//        try {
//            MyDictionary<String, Type> typeEnv = new MyDictionary<>();
//
//            ex1.typeCheck(typeEnv);
//            crPrg++;
//
//            ex2.typeCheck(typeEnv);
//            crPrg++;
//
//            ex3.typeCheck(typeEnv);
//            crPrg++;
//
//            ex4.typeCheck(typeEnv);
//            crPrg++;
//
//            ex5.typeCheck(typeEnv);
//            crPrg++;
//
//            ex6.typeCheck(typeEnv);
//            crPrg++;
//
//            ex7.typeCheck(typeEnv);
//            crPrg++;
//
//            ex8.typeCheck(typeEnv);
//            crPrg++;
//
//            ex9.typeCheck(typeEnv);
//            crPrg++;
//
//            ex10.typeCheck(typeEnv);
//            crPrg++;
//
//            ex11.typeCheck(typeEnv);
//            crPrg++;
//
//            System.out.println("No problems during TypeChecking.");
//        } catch (Exception ex)
//        {
//            System.out.println("[TypeChecking] On program "+crPrg+ " / " +ex.getMessage());
//        }
//
//        ProgramState prg1 = new ProgramState(stack1, heap1, symTable1, fileTable1, out1, ex1);
//        IRepository repo1 = new Repository(prg1, "log1.txt");
//        Controller ctrl1 = new Controller(repo1);
//
//        // -------------------------- EX 2
//
//        IStack<IStatement> stack2 = new MyStack<IStatement>();
//        IHeap<Value> heap2 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable2 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out2 = new MyList<Value>();
//
//        ProgramState prg2 = new ProgramState(stack2, heap2, symTable2, fileTable2, out2, ex2);
//        IRepository repo2 = new Repository(prg2, "log2.txt");
//        Controller ctrl2 = new Controller(repo2);
//
//        // -------------------------- EX 3
//
//        IStack<IStatement> stack3 = new MyStack<IStatement>();
//        IHeap<Value> heap3 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable3 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out3 = new MyList<Value>();
//
//        ProgramState prg3 = new ProgramState(stack3, heap3, symTable3, fileTable3, out3, ex3);
//        IRepository repo3 = new Repository(prg3, "log3.txt");
//        Controller ctrl3 = new Controller(repo3);
//
//        // -------------------------- EX 4
//
//        IStack<IStatement> stack4 = new MyStack<IStatement>();
//        IHeap<Value> heap4 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable4 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out4 = new MyList<Value>();
//
//        ProgramState prg4 = new ProgramState(stack4, heap4, symTable4, fileTable4, out4, ex4);
//        IRepository repo4 = new Repository(prg4, "log4.txt");
//        Controller ctrl4 = new Controller(repo4);
//
//        // -------------------------- EX 5
//
//        IStack<IStatement> stack5 = new MyStack<IStatement>();
//        IHeap<Value> heap5 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable5 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out5 = new MyList<Value>();
//
//        ProgramState prg5 = new ProgramState(stack5, heap5, symTable5, fileTable5, out5, ex5);
//        IRepository repo5 = new Repository(prg5, "log5.txt");
//        Controller ctrl5 = new Controller(repo5);
//
//        // -------------------------- EX 6
//
//        IStack<IStatement> stack6 = new MyStack<IStatement>();
//        IHeap<Value> heap6 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable6 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out6 = new MyList<Value>();
//
//        ProgramState prg6 = new ProgramState(stack6, heap6, symTable6, fileTable6, out6, ex6);
//        IRepository repo6 = new Repository(prg6, "log6.txt");
//        Controller ctrl6 = new Controller(repo6);
//
//        // -------------------------- EX 7
//
//        IStack<IStatement> stack7 = new MyStack<IStatement>();
//        IHeap<Value> heap7 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable7 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable7 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out7 = new MyList<Value>();
//
//        ProgramState prg7 = new ProgramState(stack7, heap7, symTable7, fileTable7, out7, ex7);
//        IRepository repo7 = new Repository(prg7, "log7.txt");
//        Controller ctrl7 = new Controller(repo7);
//
//        // -------------------------- EX 8
//
//        IStack<IStatement> stack8 = new MyStack<IStatement>();
//        IHeap<Value> heap8 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable8 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable8 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out8 = new MyList<Value>();
//
//        ProgramState prg8 = new ProgramState(stack8, heap8, symTable8, fileTable8, out8, ex8);
//        IRepository repo8 = new Repository(prg8, "log8.txt");
//        Controller ctrl8 = new Controller(repo8);
//
//        // -------------------------- EX 9
//
//        IStack<IStatement> stack9 = new MyStack<IStatement>();
//        IHeap<Value> heap9 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable9 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable9 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out9 = new MyList<Value>();
//
//        ProgramState prg9 = new ProgramState(stack9, heap9, symTable9, fileTable9, out9, ex9);
//        IRepository repo9 = new Repository(prg9, "log9.txt");
//        Controller ctrl9 = new Controller(repo9);
//
//        // -------------------------- EX 10
//
//        IStack<IStatement> stack10 = new MyStack<IStatement>();
//        IHeap<Value> heap10 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable10 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable10 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out10 = new MyList<Value>();
//
//        ProgramState prg10 = new ProgramState(stack10, heap10, symTable10, fileTable10, out10, ex10);
//        IRepository repo10 = new Repository(prg10, "log10.txt");
//        Controller ctrl10 = new Controller(repo10);
//
//        // -------------------------- EX 11
//
//        IStack<IStatement> stack11 = new MyStack<IStatement>();
//        IHeap<Value> heap11 = new MyHeap<Value>();
//        IDictionary<String, Value> symTable11 = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable11 = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out11 = new MyList<Value>();
//
//        ProgramState prg11 = new ProgramState(stack11, heap11, symTable11, fileTable11, out11, ex11);
//        IRepository repo11 = new Repository(prg11, "log11.txt");
//        Controller ctrl11 = new Controller(repo11);
//
//
//        //TODO: TYPECHECKING
//        TextMenu menu = new TextMenu();
//        menu.addCommand(new ExitCommand("0", "Exit"));
//        menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
//        menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
//        menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));
//        menu.addCommand(new RunExample("4", ex4.toString(), ctrl4));
//        menu.addCommand(new RunExample("5", ex5.toString(), ctrl5));
//        menu.addCommand(new RunExample("6", ex6.toString(), ctrl6));
//        menu.addCommand(new RunExample("7", ex7.toString(), ctrl7));
//        menu.addCommand(new RunExample("8", ex8.toString(), ctrl8));
//        menu.addCommand(new RunExample("9", ex9.toString(), ctrl9));
//        menu.addCommand(new RunExample("10", ex10.toString(), ctrl10));
//        menu.addCommand(new RunExample("11", ex11.toString(), ctrl11));
//        menu.show();
//
//
//    }
//
//    private static IStatement pr1()
//    {
//        return new CompStatement(
//                new VariableDeclStatement(
//                        "v",
//                        new IntType()
//                ),
//                new CompStatement(
//                        new AssignStatement(
//                                "v",
//                                new ValueExpression(
//                                        new IntValue(2)
//                                )
//                        ),
//                        new PrintStatement(
//                                new VariableExpression(
//                                        "v"
//                                )
//                        )
//                )
//        );
//    }
//
//    private static IStatement pr2()
//    {
//        return new CompStatement(
//                new VariableDeclStatement(
//                        "a",
//                        new IntType()
//                ),
//                new CompStatement(
//                        new VariableDeclStatement(
//                                "b",
//                                new IntType()
//                        ),
//                        new CompStatement(
//                                new AssignStatement(
//                                        "a",
//                                        new ArithmeticalExpression(
//                                                "+",
//                                                new ValueExpression(
//                                                        new IntValue(2)
//                                                ),
//                                                new ArithmeticalExpression(
//                                                        "*",
//                                                        new ValueExpression(
//                                                                new IntValue(3)
//                                                        ),
//                                                        new ValueExpression(
//                                                                new IntValue(5)
//                                                        )
//                                                )
//                                        )
//                                ),
//                                new CompStatement(
//                                        new AssignStatement(
//                                                "b",
//                                                new ArithmeticalExpression(
//                                                        "+",
//                                                        new VariableExpression("a"),
//                                                        new ValueExpression(
//                                                                new IntValue(1)
//                                                        )
//                                                )
//                                        ),
//                                        new PrintStatement(
//                                                new VariableExpression("b")
//                                        )
//                                )
//                        )
//                )
//        );
//    }
//
//    private static IStatement pr3()
//    {
//        return new CompStatement(
//                new VariableDeclStatement(
//                        "a",
//                        new BoolType()
//                ),
//                new CompStatement(
//                        new VariableDeclStatement(
//                                "v",
//                                new IntType()
//                        ),
//                        new CompStatement(
//                                new AssignStatement(
//                                        "a",
//                                        new ValueExpression(
//                                                new BoolValue(false)
//                                        )
//                                ),
//                                new CompStatement(
//                                        new IfStatement(
//                                                new VariableExpression("a"),
//                                                new AssignStatement(
//                                                        "v",
//                                                        new ValueExpression(
//                                                                new IntValue(2)
//                                                        )
//                                                ),
//                                                new AssignStatement(
//                                                        "v",
//                                                        new ValueExpression(
//                                                                new IntValue(3)
//                                                        )
//                                                )
//                                        ),
//                                        new PrintStatement(
//                                                new VariableExpression("v")
//                                        )
//                                )
//                        )
//                )
//        );
//    }
//
//    private static IStatement pr5()
//    {
//        return new PrintStatement(
//                new RelationalExpression(
//                        new ValueExpression(new IntValue(5)),
//                        new ValueExpression(new IntValue(7)),
//                        ">"
//                )
//        );
//    }
//
//    private static IStatement pr4()
//    {
//        return new CompStatement(
//                new VariableDeclStatement(
//                        "varf",
//                        new StringType()
//                ),
//                new CompStatement(
//                        new AssignStatement(
//                                "varf",
//                                new ValueExpression(
//                                        new StringValue("test.in")
//                                )
//                        ),
//                        new CompStatement(
//                                new OpenRFileStatement(
//                                        new VariableExpression(
//                                                "varf"
//                                        )
//                                ),
//                                new CompStatement(
//                                        new VariableDeclStatement(
//                                                "varc",
//                                                new IntType()
//                                        ),
//                                        new CompStatement(
//                                                new ReadFileStatement(
//                                                        new VariableExpression("varf"),
//                                                        "varc"
//                                                ),
//                                                new CompStatement(
//                                                        new PrintStatement(
//                                                                new VariableExpression("varc")
//                                                        ),
//                                                        new CompStatement(
//                                                                new ReadFileStatement(
//                                                                        new VariableExpression("varf"),
//                                                                        "varc"
//                                                                ),
//                                                                new CompStatement(
//                                                                        new PrintStatement(
//                                                                                new VariableExpression("varc")
//                                                                        ),
//                                                                        new CloseRFileStatement(
//                                                                                new VariableExpression("varf")
//                                                                        )
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//    }
//
//    private static IStatement pr6()
//    {
//        return new CompStatement(
//                new VariableDeclStatement(
//                        "v",
//                        new RefType(
//                                new IntType()
//                        )
//                ),
//                new CompStatement(
//                        new NewStatement(
//                                "v",
//                                new ValueExpression(
//                                        new IntValue(20)
//                                )
//                        ),
//                        new CompStatement(
//                                new PrintStatement(
//                                        new RHeapExpression(
//                                                new VariableExpression("v")
//                                        )
//                                ),
//                                new CompStatement(
//                                        new VariableDeclStatement(
//                                                "a",
//                                                new RefType(
//                                                        new RefType(
//                                                                new IntType()
//                                                        )
//                                                )
//                                        ),
//                                        new CompStatement(
//                                                new NewStatement(
//                                                        "a",
//                                                        new VariableExpression("v")
//                                                ),
//                                                new CompStatement(
//                                                        new WHeapStatement(
//                                                                "v",
//                                                                new ValueExpression(
//                                                                        new IntValue(30)
//                                                                )
//                                                        ),
//                                                        new PrintStatement(
//                                                                new ArithmeticalExpression(
//                                                                        "+",
//                                                                        new RHeapExpression(
//                                                                                new RHeapExpression(
//                                                                                        new VariableExpression(
//                                                                                                "a"
//                                                                                        )
//                                                                                )
//                                                                        ),
//                                                                        new ValueExpression(
//                                                                                new IntValue(5)
//                                                                        )
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//    }
//
//    private static IStatement pr7()
//    {
//        return new CompStatement(
//                new VariableDeclStatement(
//                        "v",
//                        new IntType()
//                ),
//                new CompStatement(
//                        new AssignStatement(
//                                "v",
//                                new ValueExpression(
//                                        new IntValue(4)
//                                )
//                        ),
//                        new CompStatement(
//                                new WhileStatement(
//                                        new RelationalExpression(
//                                                new VariableExpression("v"),
//                                                new ValueExpression(
//                                                        new IntValue(0)
//                                                ),
//                                                ">"
//                                        ),
//                                        new CompStatement(
//                                                new PrintStatement(
//                                                        new VariableExpression("v")
//                                                ),
//                                                new AssignStatement(
//                                                        "v",
//                                                        new ArithmeticalExpression(
//                                                                "-",
//                                                                new VariableExpression("v"),
//                                                                new ValueExpression(
//                                                                        new IntValue(1)
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                ),
//                                new PrintStatement(
//                                        new VariableExpression("v")
//                                )
//                        )
//                )
//        );
//    }
//
//    public static IStatement pr8()
//    {
//        return new CompStatement(
//                new VariableDeclStatement(
//                        "v",
//                        new RefType(
//                                new IntType()
//                        )
//                ),
//                new CompStatement(
//                        new NewStatement(
//                                "v",
//                                new ValueExpression(
//                                        new IntValue(20)
//                                )
//                        ),
//                        new CompStatement(
//                                new VariableDeclStatement(
//                                        "a",
//                                        new RefType(
//                                                new RefType(
//                                                        new IntType()
//                                                )
//                                        )
//                                ),
//                                new CompStatement(
//                                        new NewStatement(
//                                                "a",
//                                                new VariableExpression("v")
//                                        ),
//                                        new CompStatement(
//                                                new NewStatement(
//                                                        "v",
//                                                        new ValueExpression(
//                                                                new IntValue(30)
//                                                        )
//                                                ),
//                                                new PrintStatement(
//                                                        new RHeapExpression(
//                                                                new RHeapExpression(
//                                                                        new VariableExpression("a")
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//    }
//
//    public static IStatement pr9()
//    {
//        return new CompStatement(
//                new VariableDeclStatement(
//                        "v",
//                        new RefType(
//                                new IntType()
//                        )
//                ),
//                new CompStatement(
//                        new NewStatement(
//                                "v",
//                                new ValueExpression(
//                                        new IntValue(20)
//                                )
//                        ),
//                        new CompStatement(
//                                new PrintStatement(
//                                        new RHeapExpression(
//                                                new VariableExpression("v")
//                                        )
//                                ),
//                                new CompStatement(
//                                        new NewStatement(
//                                                "v",
//                                                new ValueExpression(
//                                                        new IntValue(26)
//                                                )
//                                        ),
//                                        new PrintStatement(
//                                                new RHeapExpression(
//                                                        new VariableExpression("v")
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//    }
//
//    public static IStatement pr10()
//    {
//        return new CompStatement(
//                new VariableDeclStatement(
//                        "v",
//                        new RefType(
//                                new IntType()
//                        )
//                ),
//                new CompStatement(
//                        new NewStatement(
//                                "v",
//                                new ValueExpression(
//                                        new IntValue(20)
//                                )
//                        ),
//                        new CompStatement(
//                                new VariableDeclStatement(
//                                        "a",
//                                        new RefType(
//                                                new RefType(
//                                                        new IntType()
//                                                )
//                                        )
//                                ),
//                                new CompStatement(
//                                        new NewStatement(
//                                                "a",
//                                                new VariableExpression("v")
//                                        ),
//                                        new CompStatement(
//                                                new PrintStatement(
//                                                        new RHeapExpression(
//                                                                new VariableExpression("v")
//                                                        )
//                                                ),
//                                                new PrintStatement(
//                                                        new RHeapExpression(
//                                                                new RHeapExpression(
//                                                                        new VariableExpression("a")
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//    }
//
//    public static IStatement pr11()
//    {
//        return new CompStatement(
//                new VariableDeclStatement(
//                        "v",
//                        new IntType()
//                ),
//                new CompStatement(
//                        new VariableDeclStatement(
//                                "a",
//                                new RefType(
//                                        new IntType()
//                                )
//                        ),
//                        new CompStatement(
//                                new AssignStatement(
//                                        "v",
//                                        new ValueExpression(
//                                                new IntValue(10)
//                                        )
//                                ),
//                                new CompStatement(
//                                        new NewStatement(
//                                                "a",
//                                                new ValueExpression(
//                                                        new IntValue(22)
//                                                )
//                                        ),
//                                        new CompStatement(
//                                                new Fork(
//                                                        new CompStatement(
//                                                                new WHeapStatement(
//                                                                        "a",
//                                                                        new ValueExpression(
//                                                                                new IntValue(30)
//                                                                        )
//                                                                ),
//                                                                new CompStatement(
//                                                                        new AssignStatement(
//                                                                                "v",
//                                                                                new ValueExpression(
//                                                                                        new IntValue(32)
//                                                                                )
//                                                                        ),
//                                                                        new CompStatement(
//                                                                                new PrintStatement(
//                                                                                        new VariableExpression("v")
//                                                                                ),
//                                                                                new PrintStatement(
//                                                                                        new RHeapExpression(
//                                                                                                new VariableExpression("a")
//                                                                                        )
//                                                                                )
//                                                                        )
//                                                                )
//                                                        )
//                                                ),
//                                                new CompStatement(
//                                                        new PrintStatement(
//                                                                new VariableExpression("v")
//                                                        ),
//                                                        new PrintStatement(
//                                                                new RHeapExpression(
//                                                                        new VariableExpression("a")
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//    }
//}
