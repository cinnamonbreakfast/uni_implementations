package Model;

import Model.Containers.*;
import Model.Statements.IStatement;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class ProgramState {
    private IStack<IStatement> exeStack;
    private IDictionary<String, Value> symTable;
    private IList<Value> out;
    private IDictionary<StringValue, BufferedReader> fileTable;

    private IStatement originalProgram;

    public ProgramState(IStack<IStatement> exeStack, IDictionary<String, Value> symTable, IDictionary<StringValue, BufferedReader> fileTable, IList<Value> out, IStatement originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.fileTable = fileTable;
        this.out = out;
        this.originalProgram = originalProgram;
//        exeStack.push(originalProgram);
    }

    public IStack<IStatement> getExeStack()
    {
        return exeStack;
    }

    public void setExeStack(MyStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public IDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public IList<Value> getOut() {
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

    public IDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setSymTable(IDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    @Override
    public String toString() {
        return
                "\nExeStack:\n\t" + exeStack +
                "\nSymTable:\n" + symTable +
                "\nOut:" + out;
    }

    public ProgramState deepCopy()
    {
        return new ProgramState(exeStack, symTable, fileTable, out, originalProgram);
    }
}
