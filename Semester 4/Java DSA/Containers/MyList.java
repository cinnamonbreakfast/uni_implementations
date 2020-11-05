package Containers;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList<T> {
    private ArrayList<T> list;

    public MyList()
    {
        list = new ArrayList<T>();
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

    @Override
    public T get(int index)
    {
        return list.get(index);
    }

    public String toString()
    {
        StringBuilder res = new StringBuilder();
        for(T elem : this.list)
            res.append(elem.toString()).append(" ");

        res.append("\n");
        return res.toString();
    }

    public List<T> getList()
    {
        return this.list;
    }
}
