import java.util.Properties;

import javax.mail.*;

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
   
