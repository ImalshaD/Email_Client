public class Me{
    private String name;
    private String emailAdress;//email adress of the user
    private String password;//encrypted password of the user
    protected Me(String name,String emailAdress, String password,EncryptMechanism mechanism ) {
    this.name = name;
    this.emailAdress = emailAdress;
    this.password = Encode.encrypt(password, mechanism);
    }
    protected String getEmailAdress() {
    return emailAdress;
    }
    protected void setEmailAdress(String emailAdress) {
    this.emailAdress = emailAdress;
    }
    protected void setPassword(String password) {
    this.password = password;
    }
    protected String getPassword() {
    return password;
    }
    protected String getName() {
    return name;
    }
    public void setName(String name) {
    this.name = name;
    }
   }
   
