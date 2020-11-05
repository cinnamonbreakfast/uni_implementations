package Model;

import Model.Containers.*;
import Model.Exceptions.MyException;
import Model.Statements.IStatement;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class ProgramState {
    private IStack<IStatement> exeStack;
    private IDictionary<String, Value> symTable;
    private IList<Value> out;
    private IDictionary<StringValue, BufferedReader> fileTable;
    private IHeap<Value> heap;

    private IStatement originalProgram;

    private static int lastID = 0;
    private int ID;

    public ProgramState(
            IStack<IStatement> exeStack,
            IHeap<Value> heap,
            IDictionary<String, Value> symTable,
            IDictionary<StringValue, BufferedReader> fileTable,
            IList<Value> out,
            IStatement originalProgram) {
        this.exeStack = exeStack;
        this.heap = heap;
        this.symTable = symTable;
        this.fileTable = fileTable;
        this.out = out;
        this.originalProgram = originalProgram;
        exeStack.push(originalProgram);
    }

    public ProgramState oneStep() throws MyException
    {
        if(exeStack.isEmpty())
            throw new MyException("ProgramState stack is empty.");

        IStatement currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

    public boolean noSteps() { return exeStack.isEmpty(); };

    public synchronized void setNewID()
    {
        lastID++;
        ID = lastID;
    }

    public boolean isNotCompleted()
    {
        return !this.exeStack.isEmpty();
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

    public IHeap<Value> getHeap() {
        return heap;
    }

    public void setHeap(IHeap<Value> heap) {
        this.heap = heap;
    }

    @Override
    public String toString() {
        return
                "\n" +ID+ " ExeStack:\n\t" + exeStack +
                "\n" +ID+ " SymTable:\n" + symTable +
                "\n" +ID+ " Out:" + out +
                "\n" +ID+ " FileTable:"+fileTable+
                "\n" +ID+ " Heap:"+heap;
    }

    public ProgramState deepCopy()
    {
        return new ProgramState(exeStack, heap, symTable, fileTable, out, originalProgram);
    }
}
