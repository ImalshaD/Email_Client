public class DateError extends Date{
    /*Invalid date will be represented using this class */
    private String error;
    private Exception e;
    public DateError(String error, Exception e) {
    this.error = error;
    this.e = e;
    }
    @Override
    public String toString() {
    String newString = error+" ";
    if (e!=null){
    newString+=e.toString();
    }
    return newString;
    }
    
   }
   
