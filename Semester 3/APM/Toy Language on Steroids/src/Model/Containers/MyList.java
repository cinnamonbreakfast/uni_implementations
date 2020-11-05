package Model.Containers;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MyList<T> implements IList<T> {
    private Deque<T> list;

    public MyList()
    {
        list = new ConcurrentLinkedDeque<>();
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public boolean add(T elem)
    {
        return list.add(elem);
    }

    @Override
    public boolean remove(T elem)
    {
        return list.remove(elem);
    }

    public String toString()
    {
        StringBuilder res = new StringBuilder();
        for(T elem : this.list)
            res.append(elem.toString()).append(" ");

        res.append("\n");
        return res.toString();
    }

    public Deque<T> getList()
    {
        return this.list;
    }
}
