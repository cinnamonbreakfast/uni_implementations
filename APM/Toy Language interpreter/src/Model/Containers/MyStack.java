package Model.Containers;

import java.util.Stack;
import Model.Exceptions.StackException;

public class MyStack<T> implements IStack<T> {
    private Stack<T> stack;

    public MyStack()
    {
        stack = new Stack<T>();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void push(T elem)
    {
        stack.push(elem);
    }

    @Override
    public T pop()
    {
        if (this.stack.isEmpty()) {
            throw new StackException("Stack is empty!");
        }

        return stack.pop();
    }

    @Override
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for(T elem : this.stack) {
            s.append(elem.toString()).append(" ");
        }

        return s.toString();
    }

    @Override
    public Stack<T> getStack()
    {
        return stack;
    }

    @Override
    public void clear()
    {
        stack.clear();
    }

    @Override
    public T peek()
    {
        return this.getStack().peek();
    }
}
