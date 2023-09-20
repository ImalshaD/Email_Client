public class OfficialFriend extends OfficialPerson implements IWhishable{
    public ValidDate bday;
    public OfficialFriend(String name, String email, String designation,ValidDate bday) {
    super(name, email, designation);
    this.bday=bday;
    }
    @Override
    public String toString() {
    String newString;
    newString = "Office_friend" + super.toString().substring(8)+","+bday.toString();
    return newString;
    }
    public Date getBday() {
    return bday;
    }
    @Override
    public IComparing getObject() {
    return bday;
    }
    @Override
    public String genarateWish(String name) {
    return "Happy birthday.\n"+name;
    }
   }
   
