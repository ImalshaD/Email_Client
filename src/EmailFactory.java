public class EmailFactory {
    MeProxy myself;
    public EmailFactory(MeProxy myself) {
    this.myself = myself;
    }
    public Email createEmail(String recieverAdress, String subject, String body ){
    return new Email(myself.getEmailAdress(), recieverAdress, subject, body);
    }
    public Email createEmail(Person reciever, String subject, String body){
    return new Email(myself.getEmailAdress(), reciever.getEmail(),subject,body);
    }
   }
   
