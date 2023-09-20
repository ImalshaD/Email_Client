import javax.mail.Session;

public abstract class AuthorizingMechanism {
    protected abstract Session authenticate(String username,String password);
   }
   