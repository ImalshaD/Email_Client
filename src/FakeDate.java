public class FakeDate extends ValidDate {
    /*Date which will be lesser than any date 
    * contains 1800 0 0 which is invalid
    */
    public FakeDate() {
    super(1800,0,0);
    }
   }
   
