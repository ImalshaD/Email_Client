import java.time.LocalDateTime;

public class DateFactory {
    public static Date getDate(String dateString){
    String arr[] = dateString.split("/");
    if (arr.length!=3){
    DateError error = new DateError("Invalid Parameters", null);
    return error;
    }
    try{
    int year = Integer.valueOf(arr[0]);
    int month = Integer.valueOf(arr[1]);
    int day = Integer.valueOf(arr[2]);
    ValidDate date = new ValidDate(year, month, day);
    if (ValidDate.isValidDate(date)){
    return date;
    }else{
    DateError error = new DateError("Data validity Error", null);
    return error;
    }
    }catch(NumberFormatException e){
    DateError error = new DateError("convertion Error to int", e);
    return error;
    }catch(Exception e){
    DateError error = new DateError("Unhandled Error", e);
    return error;
    }
    }
    public static FakeDate getFakeDate(){
    return new FakeDate();
    }
    public static ValidDate getToday(){ 
    LocalDateTime today = LocalDateTime.now();
    return new ValidDate(today.getYear(),today.getMonthValue(),today.getDayOfMonth());
    }
   }
   
