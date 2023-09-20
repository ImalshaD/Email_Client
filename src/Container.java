import java.util.ArrayList;

public abstract class Container<T extends IComparable> {
    public abstract void insert(T obj);
    public abstract T search(IComparable obj);
    public abstract ArrayList<T> search(IComparing obj);
    public abstract void print();
   }
   