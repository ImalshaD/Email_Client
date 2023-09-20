public class CasualFriend extends Person implements IWhishable{
    private ValidDate bday;
    private String nickName;
    public CasualFriend(String name, String email, ValidDate bday, String nickName) {
    super(name, email);
    this.bday = bday;
    this.nickName = nickName;
    }
    public ValidDate getBday() {
    return bday;
    }
    public String getNickName() {
    return nickName;
    }
    public void setNickName(String nickName) {
    this.nickName = nickName;
    }
    public String toString(){
    String newString;
    newString = "Personal: "+name+","+nickName+","+email+","+bday.toString();
    return newString;
    }
    @Override
    public IComparing getObject() {
    return bday;
    }
    @Override
    public String genarateWish(String name) {
    return "Hugs and love on your birthday\n"+name;
    }
    
   }
   
