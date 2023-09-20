public class ValidDate extends Date implements IComparing{
    private int year;
    private int month;
    private int day;
    public ValidDate(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
    }
    public String toString(){
    String tostring ="";
    tostring+=year+"/"+month+"/"+day;
    return tostring;
    }
    public static Boolean isValidDate(ValidDate date){
    if (date.year< 1800){
    return false;
    }else if (date.month>12 || date.month<1){
    return false;
    }else{
    if (date.month>=8 & date.month%2==0){
    if (date.day>31){
    return false;
    } 
    }else if (date.month>8){
    if (date.day>30){
    return false;
    }
    }else if (date.month==2 & date.year%4==0 & !(date.year%400 == 0)){
    if (date.day>29){
    return false;
    }
    }else if (date.month==2){
    if (date.day>28){
    return false;
    }
    }else if (date.month%2==1){
    if (date.day>31){
    return false;
    }
    }else{
    if (date.day>30){
    return false;
    }else{
    return true;
    }
    }
    }
    return true;
    }
    public Boolean isEqual(IComparing object){
    ValidDate date;
    if (object instanceof ValidDate){
    date = (ValidDate) object;
    }else{
    throw new IllegalArgumentException("Expected Valid date got" + object.getClass());
    }
    if (this.day == date.day & this.month == date.month){
    return true;
    }else{
    return false;
    }
    }
    public boolean compare(ValidDate date1,ValidDate date2){
    if (date1.month>date2.month){
    return true;
    }else if (date1.month<date2.month){
    return false;
    }else{
    return (date1.day>date2.day);
    }
    }
    @Override
    public Boolean greaterThan(IComparing object) {
    ValidDate date;
    if (object instanceof ValidDate){
    date = (ValidDate) object;
    }else{
    throw new IllegalArgumentException("Expected Valid date got" + object.getClass());
    }
    return compare(date, this);
    }
    public int getYear() {
    return year;
    }
    public int getMonth() {
    return month;
    }
    public int getDay() {
    return day;
    }
    @Override
    public Boolean exact(IComparing object) {
    ValidDate date;
    if (object instanceof ValidDate){
    date = (ValidDate) object;
    }else{
    throw new IllegalArgumentException("Expected Valid date got" + object.getClass());
    }
    if (this.day == date.day & this.month == date.month & this.year == date.year){
    return true;
    }else{
    return false;
    }
    }
   }
   
