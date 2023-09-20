import java.io.Serializable;

public class Email implements Serializable, IComparable {
    /*Email object */
    private String senderAdress;
    private String recieverAdress;
    private String subject;
    private String body;
    private ValidDate sentDate;
    private Boolean sent;
    public Email(String senderAdress, String recieverAdress, String subject, String body) {
    this.senderAdress = senderAdress;
    this.recieverAdress = recieverAdress;
    this.subject = subject;
    this.body = body;
    this.sentDate = DateFactory.getToday();
    this.sent =false;
    }
    public void setDate(){
    this.sentDate = DateFactory.getToday();
    }
    public String getSenderAdress() {
    return senderAdress;
    }
    public void setSenderAdress(String senderAdress) {
    this.senderAdress = senderAdress;
    }
    public String getRecieverAdress() {
    return recieverAdress;
    }
    public void setRecieverAdress(String recieverAdress) {
    this.recieverAdress = recieverAdress;
    }
    public String getSubject() {
    return subject;
    }
    public void setSubject(String subject) {
    this.subject = subject;
    }
    public String getBody() {
    return body;
    }
    public void setBody(String body) {
    this.body = body;
    }
    public ValidDate getSentDate() {
    return sentDate;
    }
    public void setSentDate(ValidDate sentDate) {
    this.sentDate = sentDate;
    }
    @Override
    public String toString() {
    return ("Date: " + sentDate.toString()+"\n"
    +"Sent: " + sent + "\n"
    +"Sender: "+ senderAdress +"\n"
    +"Reciver: "+ recieverAdress+ "\n"
    + "Subject: " + subject +"\n"
    + body+"\n"
    );
    }
    public void sent(){
    this.sent = true;
    }
    @Override
    public IComparing getObject() {
    return sentDate;
    }
   }
   
