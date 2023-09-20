import java.util.ArrayList;

public class BirthdayManager {
    /*Manages birthdays wished on relevent days */
    Container<Person> people;
    String casulaFriendwish;
    ArrayList<Person> bdayPeople;
    EmailFactory emailFactory;
    Authorizer auth;
    public BirthdayManager(Container<Person> people, EmailFactory emailFactory,String myEmail,Authorizer auth) {
    this.people = people;
    this.emailFactory = emailFactory;
    this.auth = auth;
    }
    public void wishAs(String name ){
    bdayPeople =people.search(DateFactory.getToday());
    for (Person bdayPerson : bdayPeople){
    if (bdayPerson instanceof IWhishable){
    IWhishable person = (IWhishable) bdayPerson;
    Email email = emailFactory.createEmail(person.getEmail(), "Birthday wish", 
   person.genarateWish(name));
    auth.sendEmail(email);
    }
    }
    }
    public void wishSingleAs(String name, IWhishable person ){
    Email email = emailFactory.createEmail(person.getEmail(), "Birthday wish", 
   person.genarateWish(name));
    auth.sendEmail(email);
    }
    public void printToday(){
    printToday(DateFactory.getToday());
    }
    public void printToday(ValidDate date){
    /*It won't care about the year only day and month */
    bdayPeople =people.search(date);
    if (bdayPeople.size()==0){
    System.out.println("No bithdays on Date : "+date.toString());
    return;
    }
    System.out.println("Birthdays on Date: "+date.toString());
    int c=1;
    for (Person bdayPerson : bdayPeople){
    System.out.println("\t+"+c+"."+bdayPerson.toString());
    c++;
    }
    }
    public void print(){
    people.print();
    }
    
   }
