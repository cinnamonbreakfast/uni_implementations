//package View;
//
//import Controller.Controller;
//import Model.Containers.*;
//import Model.Exceptions.MyException;
//import Model.Expressions.ArithmeticalExpression;
//import Model.Expressions.ValueExpression;
//import Model.Expressions.VariableExpression;
//import Model.ProgramState;
//import Model.Statements.*;
//import Model.Types.BoolType;
//import Model.Types.IntType;
//import Model.Types.StringType;
//import Model.Values.BoolValue;
//import Model.Values.IntValue;
//import Model.Values.StringValue;
//import Model.Values.Value;
//import Repository.IRepository;
//import Repository.Repository;
//
//import java.io.BufferedReader;
//import java.util.Scanner;
//
//public class View {
//    private Controller controller;
//
//    private static Scanner keyboard = new Scanner(System.in);
//
//    // int v; v = 2; print(v): ==> 2
//
//    private static IStatement ex1 =
//            new CompStatement(
//                    new VariableDeclStatement(
//                            "v",
//                            new IntType()
//                    ),
//                    new CompStatement(
//                            new AssignStatement(
//                                    "v",
//                                    new ValueExpression(
//                                            new IntValue(2)
//                                    )
//                            ),
//                            new PrintStatement(
//                                    new VariableExpression(
//                                            "v"
//                                    )
//                            )
//                    )
//            );
//
//    // int a; int b; a=2+3*5; b=a+1; print(b): ===> 18
//
//    private static IStatement ex2 =
//            new CompStatement(
//                    new VariableDeclStatement(
//                      "a",
//                            new IntType()
//                    ),
//                    new CompStatement(
//                            new VariableDeclStatement(
//                                    "b",
//                                    new IntType()
//                            ),
//                            new CompStatement(
//                                    new AssignStatement(
//                                            "a",
//                                            new ArithmeticalExpression(
//                                                    "+",
//                                                    new ValueExpression(
//                                                            new IntValue(2)
//                                                    ),
//                                                    new ArithmeticalExpression(
//                                                            "*",
//                                                            new ValueExpression(
//                                                                    new IntValue(3)
//                                                            ),
//                                                            new ValueExpression(
//                                                                    new IntValue(5)
//                                                            )
//                                                    )
//                                            )
//                                    ),
//                                    new CompStatement(
//                                            new AssignStatement(
//                                                    "b",
//                                                    new ArithmeticalExpression(
//                                                            "+",
//                                                            new VariableExpression("a"),
//                                                            new ValueExpression(
//                                                                    new IntValue(1)
//                                                            )
//                                                    )
//                                            ),
//                                            new PrintStatement(
//                                                    new VariableExpression("b")
//                                            )
//                                    )
//                            )
//                    )
//            );
//
//    // bool a; int v; a=true; (if a then v = 2 else v = 3); print(v) ==> 3
//    private static IStatement ex3 =
//            new CompStatement(
//                    new VariableDeclStatement(
//                            "a",
//                            new BoolType()
//                    ),
//                    new CompStatement(
//                            new VariableDeclStatement(
//                                    "v",
//                                    new IntType()
//                            ),
//                            new CompStatement(
//                                    new AssignStatement(
//                                            "a",
//                                            new ValueExpression(
//                                                    new BoolValue(false)
//                                            )
//                                    ),
//                                    new CompStatement(
//                                            new IfStatement(
//                                                    new VariableExpression("a"),
//                                                    new AssignStatement(
//                                                            "v",
//                                                            new ValueExpression(
//                                                                    new IntValue(2)
//                                                            )
//                                                    ),
//                                                    new AssignStatement(
//                                                            "v",
//                                                            new ValueExpression(
//                                                                    new IntValue(3)
//                                                            )
//                                                    )
//                                            ),
//                                            new PrintStatement(
//                                                    new VariableExpression("v")
//                                            )
//                                    )
//                            )
//                    )
//            );
//
//    private static IStatement ex4 =
//
//            new CompStatement(
//                    new VariableDeclStatement(
//                            "varf",
//                            new StringType()
//                    ),
//                    new CompStatement(
//                            new AssignStatement(
//                                    "varf",
//                                    new ValueExpression(
//                                            new StringValue("test.in")
//                                    )
//                            ),
//                            new CompStatement(
//                                    new OpenRFileStatement(
//                                            new VariableExpression(
//                                                    "varf"
//                                            )
//                                    ),
//                                    new CompStatement(
//                                            new VariableDeclStatement(
//                                                    "varc",
//                                                    new IntType()
//                                            ),
//                                            new CompStatement(
//                                                    new ReadFileStatement(
//                                                            new VariableExpression("varf"),
//                                                            "varc"
//                                                    ),
//                                                    new CompStatement(
//                                                            new PrintStatement(
//                                                                    new VariableExpression("varc")
//                                                            ),
//                                                            new CompStatement(
//                                                                    new ReadFileStatement(
//                                                                            new VariableExpression("varf"),
//                                                                            "varc"
//                                                                    ),
//                                                                    new CompStatement(
//                                                                            new PrintStatement(
//                                                                                    new VariableExpression("varc")
//                                                                            ),
//                                                                            new CloseRFileStatement(
//                                                                                    new VariableExpression("varf")
//                                                                            )
//                                                                    )
//                                                            )
//                                                    )
//                                            )
//                                    )
//                            )
//                    )
//            );
//
//
//    public static void main(String[] args)
//    {
//        IStack<IStatement> stack = new MyStack<IStatement>();
//        IDictionary<String, Value> symTable = new MyDictionary<String, Value>();
//        IDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
//        IList<Value> out = new MyList<Value>();
//
//        System.out.println("There are currently 3 programs loaded.");
//        System.out.println("1 : Program 1");
//        System.out.println("2 : Program 2");
//        System.out.println("3 : Program 3");
//
//        int option;
//
//        ProgramState state = new ProgramState(stack, symTable, fileTable, out, ex4);
//
////        try {
////            option = Integer.parseInt(keyboard.nextLine());
////            switch(option)
////            {
////                case 1:
////                    state = new ProgramState(stack, symTable, out, ex1);
////                    break;
////                case 2:
////                    state = new ProgramState(stack, symTable, out, ex2);
////                    break;
////                case 3:
////                    state = new ProgramState(stack, symTable, out, ex3);
////                    break;
////                default:
////                    throw new MyException("Wrong program cmd.");
////            }
////        } catch (MyException e)
////        {
////            return;
////        }
//
//        IRepository repo = new Repository(state, "exec_log.log");
//
//        repo.logPrgStateExec();
//
//        Controller ctrl = new Controller(repo);
//
//        try {
//            ctrl.allStep();
//
//            for(int i = 0; i < out.size(); ++i)
//            {
//                System.out.println(out.get(i).toString());
//            }
//        }
//        catch (MyException e)
//        {
//            System.out.println(e.getMessage());
//        }
//    }
//}