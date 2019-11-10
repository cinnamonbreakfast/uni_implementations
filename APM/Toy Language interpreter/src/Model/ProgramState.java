package Model;

import Model.Containers.MyDictionary;
import Model.Containers.MyList;
import Model.Containers.MyStack;
import Model.Statements.IStatement;
import Model.Values.Value;

public class ProgramState {
    MyStack<IStatement> exeStack;
    MyDictionary<String, Value> symTable;
    MyList<Value> out;

    IStatement originalProgram;

    public ProgramState(MyStack<IStatement> exeStack, MyDictionary<String, Value> symTable, MyList<Value> out, IStatement originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        exeStack.push(originalProgram);
    }

    public MyStack<IStatement> getExeStack()
    {
        return exeStack;
    }

    public void setExeStack(MyStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public MyDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyList<Value> getOut() {
        return out;
    }

    public void setOut(MyList<Value> out) {
        this.out = out;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public ProgramState deepCopy()
    {
        return new ProgramState(exeStack, symTable, out, originalProgram);
    }
}
