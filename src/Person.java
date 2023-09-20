public abstract class Person implements Filewritable, IComparable{
    protected String name;
    protected String email;
    protected int id;
    private static int count=0;
    public Person(String name, String email) {
    count+=1;
    this.id = count;
    this.name = name;
    this.email = email;
    }
    public String getName() {
    return name;
    }
    public String getEmail() {
    return email;
    }
    public void setEmail(String email) {
    this.email = email;
    }
    public void setName(String name) {
    this.name = name;
    }
    public static int getcount(){
    return count;
    }
   }
   
