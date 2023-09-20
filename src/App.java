import java.util.ArrayList;
import java.util.Scanner;

public class App{
    public static void main(String[] args){
    Tree<Person> contacts = new Tree<Person>();
    Tree<Email> sentEmails = new Tree<Email>();
    UserNamePassword method = new UserNamePassword();
    SimpleEncryptMechanism mech = new SimpleEncryptMechanism();
    MeProxy me = MeProxy.getInstance("Imalsha Puranegedara","javaemailclient.487B@gmail.com", 
   "kzcwyvupcvsekduz", mech);
    EmailFactory fac = new EmailFactory(me);
    PersonFactory pfac = PersonFactory.getInstance();
    FileObj personRecord = new FileObj("clientList.txt","rw");
    Serial<Tree<Email>> serializedEmails = new Serial<Tree<Email>>("Emails.ser", "rw");
    ArrayList<Tree<Email>> temp = serializedEmails.deserialize();
    if (temp.size()>0){
    sentEmails = temp.get(0);
    }else{
    sentEmails = new Tree<Email>();
    }
    for (String s : personRecord.readfromFile()){
    Person newPerson = pfac.getPerson(s);
    contacts.insert(newPerson);
    }
    Serial<ValidDate> lastwishedDate = new Serial<ValidDate>("LastWishedDay.ser","rw");
    ValidDate lwday =(ValidDate) DateFactory.getDate("2000/01/01");
    ValidDate today = DateFactory.getToday();
    if (lastwishedDate.deserialize().size()>0){
    lwday = lastwishedDate.deserialize().get(0);
    }
    lastwishedDate.serialize(today);
    lastwishedDate.close();
    Authorizer auth = Authorizer.getInstance(me, sentEmails);
    auth.authenticate(method); 
    me.addObserver(auth);
    BirthdayManager bmanager = new BirthdayManager(contacts, fac, me.getEmailAdress(), auth); 
    // lwday = (ValidDate) DateFactory.getDate("2000/01/01");
    //Tempory line used to simulate the lwday
    if (lwday.greaterThan(today)){
    // if the last wished date is lesser than today then it will wish
    bmanager.wishAs(me.getName());
    };
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter option type: \n"
    + "1 - Adding a new recipient\n"
    + "2 - Sending an email\n"
    + "3 - Printing out all the recipients who have birthdays\n"
    + "4 - Printing out details of all the emails sent\n"
    + "5 - Printing out the number of recipient objects in the application");
    int option = scanner.nextInt();
    String userInput = "";
    scanner.nextLine();
    switch(option){
    case 1:
    System.out.println("Enater person details: - ");
    System.out.println("Format - Official: nimal,nimal@gmail.com,ceo");
    System.out.println(" - Office_friend: nimal,nimal@gmail.com,ceo,birthday(yyyy/mm/dd)");
    System.out.println(" - Personal: nimal,nima,nimal@gmail.com,birthday(yyyy/mm/dd)");
    userInput = scanner.nextLine();
    Person newPerson = pfac.getPerson(userInput);
    if (!(newPerson instanceof PersonError)){
    contacts.insert(newPerson);
    if (newPerson instanceof IWhishable){
    if (newPerson.getObject().isEqual(today)){
    //if newly inserted person celebrates birthday today then it wishes
    bmanager.wishSingleAs(me.getName(), (IWhishable) newPerson);
    }
    }
    }else{
    System.out.println(newPerson.toString());
    }
    break;
    case 2:
    System.out.println("Enter email details: - ");
    System.out.println("Format -EmailAdress,Subject,Body");
    userInput = scanner.nextLine();
    String arr[] = userInput.split(",");
    if (arr.length!=3){
    System.out.println("Invalid input");
    break;
    }
    try{
    Email newEmail = fac.createEmail(arr[0], arr[1], arr[2]);
    auth.sendEmail(newEmail);
    }catch(Exception e){
    System.out.println("Error trying to send a Email");
    e.printStackTrace();
    }
    break;
    case 3:
    System.out.println("Input a date -");
    System.out.println("Format - yyyy/mm/dd");
    userInput = scanner.nextLine();
    Date newDate = DateFactory.getDate(userInput);
    if (newDate instanceof DateError){
    System.out.println("Invalid date input");
    System.out.println(newDate.toString());
    }else if (newDate instanceof ValidDate){
    bmanager.printToday((ValidDate) newDate);
    }
    break;
    case 4:
    System.out.println("Input a date -");
    System.out.println("Format - yyyy/mm/dd");
    userInput = scanner.nextLine();
    Date emailDate = DateFactory.getDate(userInput);
    if (emailDate instanceof DateError){
    System.out.println("Invalid date input");
    System.out.println(emailDate.toString());
    }else if (emailDate instanceof ValidDate){
    ValidDate validemailDate = (ValidDate) emailDate;
    System.out.println("Emails sent on the date:- "+ validemailDate.toString() );
    for (Email e : sentEmails.search(validemailDate)){
    if (e.getObject().exact(validemailDate)){
    System.out.println("\n"+e.toString());
    }
    }
    }
    break;
    case 5:
    System.out.println("Number of Contacts on contact list - " + PersonFactory.getcount());
    break;
    }
    personRecord.writeToFile(contacts.toString());
    personRecord.close();
    serializedEmails.serialize(sentEmails);
    serializedEmails.close();
    scanner.close();
    }
   }
