public class PersonFactory {
    private static PersonFactory instance=null;
    private static int count=0;
    private PersonFactory() {
    }
    public static PersonFactory getInstance(){
    if (instance==null){
    instance = new PersonFactory();
    }
    return instance;
    }
    public Person getPerson(String request){
    String arr[] = request.split(" ");
    String param[] = arr[1].split(",");
    if (param.length<3){
    return new PersonError("Parameter error",null);
    }
    if (arr[0].strip().equals("Official:")){
    if (param.length!=3){
    return new PersonError("Parameter error",null);
    }
    try{
    count++;
    return new OfficialPerson(param[0],param[1],param[2]);
    }catch(Exception e){
    count--;
    return new PersonError("Creational Error",e);
    }
    }else if (arr[0].strip().equals("Office_friend:")){
    Date birthday;
    if (param.length!=4){
    return new PersonError("Parameter error 4 expected for Office_friend",null);
    }
    try{
    birthday = DateFactory.getDate(param[3]);
    }catch (Exception e){
    return new PersonError("Date Error",e);
    }
    if ( birthday instanceof DateError){
    return new PersonError("Invalid date " + birthday.toString(),null);
    }else{
    try{
    count++;
    return new OfficialFriend(param[0],param[1],param[2],(ValidDate) birthday);
    }catch( Exception e){
    count--;
    return new PersonError("Creational Error",e);
    }
    }
    }else if (arr[0].strip().equals("Personal:")){
    Date birthday;
    if (param.length!=4){
    return new PersonError("Parameter error 4 expected for personal_friend",null);
    }
    try{
    birthday = DateFactory.getDate(param[3]);
    }catch (Exception e){
    return new PersonError("Date Error",e);
    }
    if ( birthday instanceof DateError){
    return new PersonError("Invalid date " + birthday.toString(),null);
    }else{
    try{
    count++;
    return new CasualFriend(param[0],param[2],(ValidDate) birthday, param[1]);
    } catch (Exception e){
    count--;
    return new PersonError("Creational Error",e);
    }
    }
    }else{
    return new PersonError("Invalid Type for person",null);
    }
    }
    public static int getcount(){
    return count;
    }
   }
   