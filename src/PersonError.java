public class PersonError extends Person{
    String error;
    Exception e;
    public PersonError(String error, Exception e) {
    super("Error","Error@person");
    this.error = error;
    this.e = e;
    }
    @Override
    public String toString() {
    String newString = error + " ";
    if (e!=null){
    newString+=e.toString();
    }
    return newString;
    }
    @Override
    public IComparing getObject() {
    return DateFactory.getFakeDate();
    }
    
   }
   
