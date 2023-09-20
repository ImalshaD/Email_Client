import java.io.Serializable;

public interface IComparing extends Serializable{
    public Boolean isEqual(IComparing object);
    public Boolean greaterThan(IComparing object);
    public Boolean exact(IComparing object);
   }
   