import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class MeProxy implements ISubject , Serializable{
    private Me myself;
    private static MeProxy instance=null;
    private EncryptMechanism mechanism;
    private ArrayList<ISubscriber> subscribers;
    private MeProxy(String name,String emailAdress,String password, EncryptMechanism mechanism) {
    myself = new Me(name,emailAdress,password,mechanism);
    this.mechanism=mechanism;
    subscribers = new ArrayList<ISubscriber>();
    }
    public static MeProxy getInstance(String name,String emailAdress,String password,EncryptMechanism 
   mechanism) {
    if (instance==null){
    instance = new MeProxy(name,emailAdress,password,mechanism);
    }
    return instance;
    }
    public String getEmailAdress() {
    return myself.getEmailAdress();
    }
    public void setEmailAdress() {
    System.out.print("---Enter the current password<<");
    Scanner sc = new Scanner(System.in);
    String enteredPassword = sc.nextLine();
    if (enteredPassword.equals(Encode.decrypt(myself.getPassword(),mechanism))){
    System.out.print("---Enter new Email<< ");
    String newEmail = sc.nextLine();
    if (isValidEmail(newEmail)){
    System.out.print("---Eneter new Password<< ");
    String newPassword = sc.nextLine();
    myself.setEmailAdress(newEmail);
    myself.setPassword(Encode.encrypt(newPassword,mechanism));
    String newName = sc.nextLine();
    System.out.print("---Eneter new Name<< ");
    myself.setName(newName);
    System.out.println("New credentials updated successfully!");
    notifyObservers();
    }else{
    System.out.println("Warning!!! Invalid Email credential update failed!!!");
    }
    sc.close();
    }else{
    System.out.println("Logging Error!!!");
    }
    }
    private Boolean isValidEmail(String emailString){
    String arr[] = emailString.split("@");
    if (arr.length>1){
    return true;
    }else{
    return false;
    }
    }
    public String getPassword(){
    return myself.getPassword();
    }
    public EncryptMechanism getMechanism() {
    return mechanism;
    }
    public void setMechanism(EncryptMechanism newMechanism){
    
   myself.setPassword(Encode.encrypt(Encode.decrypt(myself.getPassword(),mechanism),newMechanism
   ));
    mechanism=newMechanism;
    }
    public String getName(){
    return myself.getName();
    }
    @Override
    public void notifyObservers() {
    for (ISubscriber subscriber : subscribers){
    subscriber.update();
    }
    
    }
    @Override
    public void addObserver(ISubscriber newSubscriber) {
    subscribers.add(newSubscriber);
    
    }
    @Override
    public void removeObserver(ISubscriber aSubscriber) {
    subscribers.remove(aSubscriber);
    
    }
    
   }
