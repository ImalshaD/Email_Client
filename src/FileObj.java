import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileObj extends Record {
    private FileReader freader;
    private BufferedReader reader;
    private FileWriter fwriter;
    private BufferedWriter writer;
    private ArrayList<String> content;
    public FileObj(String path,String permission){
    super(path, permission);
    }
    protected Boolean getReaderReady(String path){
    try {
    freader = new FileReader(path);
    reader = new BufferedReader(freader);
    return true;
    } catch (FileNotFoundException e) {
    System.out.println("Error while trying to open the file");
    e.printStackTrace();
    return false;
    }
    }
    protected Boolean getWriterReady(String path){
    try{
    fwriter = new FileWriter(path);
    writer = new BufferedWriter(fwriter);
    return true;
    }catch(IOException e){
    System.out.println("Error while trying to setting the file for writting");
    e.printStackTrace();
    return false;
    }
    }
    private ArrayList<String> readFile(){
    ArrayList<String> lines = new ArrayList<String>();
    String line;
    try {
    while ((line = reader.readLine()) != null){
    if (!line.equals("")){
    lines.add(line);
    }
    }
    } catch (IOException e) {
    e.printStackTrace();
    }
    return lines;
    }
    public ArrayList<String> readfromFile(){
    if (closed){
    System.out.println("File closed");
    return new ArrayList<String>();
    }
    if (!rPerm){
    System.out.println("Do not have Permission to read the file");
    return new ArrayList<String>();
    }
    if (content==null){
    content = readFile();
    }
    return content;
    }
    public Boolean writeToFile(Filewritable obj){
    return writeToFile(obj.toString());
    }
    public Boolean writeToFile(String s){
    if (closed){
    System.out.println("File closed");
    return false;
    }
    if (!wPerm){
    System.out.println("Do not have permissions to write to file");
    return false;
    }
    try {
    writer.write(s);
    return true;
    } catch (IOException e) {
    System.out.println("Warning..! Unbale to write to the file ");
    System.out.println("Data may be lost if we close the program now");
    e.printStackTrace();
    return false;
    }
    }
    public void close(){
    closed = true;
    if (this.reader!=null){
    try {
    this.reader.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    if (this.freader!=null){
    try {
    this.freader.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    if (this.writer!=null){
    try {
    this.writer.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    if (fwriter!=null){
    try {
    this.fwriter.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    }
    @Override
    protected void load() {
    /*When a file opens in writing mode data will be cleared thsi keeps the data
    * of the file if laterly needed by the application
    */
    content = readFile();
    
    }
    @Override
    protected Boolean validFiletype(String type) {
    if (type.equals("txt")){
    return true;
    }
    return false;
    }
   }
   
