import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Authorizer implements ISubscriber {
    private Session session;//session obeject returned
    private MeProxy myself;//To get client data
    private AuthorizingMechanism preUsedMechanism;//Mechanism used for previous authentication
    private static Authorizer instance = null;//Singleton purpose
    private Container<Email> sentEmails;//Sent emails will be collected here for serialization
    private Authorizer(MeProxy myself,Container<Email> sentEmails) {
    /*Used to create a new Authorixer */
    this.myself = myself;
    this.sentEmails = sentEmails;
    }
    public static Authorizer getInstance(MeProxy myself, Container<Email> sentEmails){
    /*If authorixer exists it will be returned unless will be created */
    if (instance==null){
    instance = new Authorizer(myself,sentEmails);
    }
    return instance;
    }
    public void authenticate(AuthorizingMechanism mechanism){
    session = 
   mechanism.authenticate(myself.getEmailAdress(),Encode.decrypt(myself.getPassword(),myself.getMechanism()));
    preUsedMechanism= mechanism;
    }
    public Session getSession() {
    return session;
    }
    @Override
    public void update() {
    /*If data in Myproxy object changes this method will authorize again for new data */
    this.authenticate(this.preUsedMechanism);
    
    }
    @Override
    public void subscribe(ISubject channel) {
    channel.addObserver(this);
    
    }
    @Override
    public void unSubscribe(ISubject channel) {
    channel.removeObserver(this);
    }
    public Boolean sendEmail(Email email){
    /*Logic for sending a email using session */
    if (session == null){
    System.out.println("Sending Failed due to authorizing error.. try to re-authorize and send again");
    return false;
    }
    Message finalEmail = new MimeMessage(session);
    try {
    finalEmail.setFrom( new InternetAddress(email.getSenderAdress()));
    finalEmail.setRecipients(
    Message.RecipientType.TO,
    InternetAddress.parse(email.getRecieverAdress())
    );
    finalEmail.setSubject(email.getSubject());
    finalEmail.setText(email.getBody());
    } catch (AddressException e) {
    e.printStackTrace();
    return false;
    } catch (MessagingException e) {
    e.printStackTrace();
    return false;
    }
    try{
    email.setDate();
    Transport.send(finalEmail);
    email.sent();
    sentEmails.insert(email);
    }catch (Exception aException){
    System.out.println("Error while sending the email" + aException.toString());
    return false;
    }
    return true;
    }
   }
   abstract class AuthorizingMechanism {
    protected abstract Session authenticate(String username,String password);
   }
   class UserNamePassword extends AuthorizingMechanism {
    /*Authorizing mechanism that uses username and password */
    protected Session authenticate(String username,String password){
    try {
    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.starttls.enable", "true"); 
    
    Session session = Session.getInstance(prop,
    new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(username, password);
    }
    });
    return session;
    }catch(Exception e){
    System.out.println("Authorizing to gamil account failed......");
    e.printStackTrace();
    return null;
    }
    }
   }