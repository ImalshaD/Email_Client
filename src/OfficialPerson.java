public class OfficialPerson extends Person {
    private String designation;
    public OfficialPerson(String name, String email, String designation) {
    super(name, email);
    this.designation = designation;
    }
    @Override
    public String toString() {
    String newString;
    newString = "Official: "+name+","+email+","+designation;
    return newString; 
    }
    @Override
    public IComparing getObject() {
    return DateFactory.getFakeDate();
    }
    
   }
   