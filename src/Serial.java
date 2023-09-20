import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Serial<T extends Serializable> extends Record{
    private FileOutputStream fileoutStream;
    private ObjectOutputStream objectoutStream;
    private FileInputStream fileinStream;
    private ObjectInputStream objectinstream;
    private ArrayList<T> content;
    public Serial(String path,String permission){
    super(path, permission);
    }
    protected Boolean getReaderReady(String path){
    try {
    this.fileinStream = new FileInputStream(path);
    this.objectinstream = new ObjectInputStream(fileinStream);
    return true;
    } catch (IOException e) {
    System.out.println("Wraning...! Unable to initialize reader to the file " + path);
    e.printStackTrace();
    return false;
    }
    }
    protected Boolean getWriterReady(String path){
    try {
    this.fileoutStream = new FileOutputStream(path);
    this.objectoutStream = new ObjectOutputStream(fileoutStream);
    return true;
    } catch (IOException e) {
    System.out.println("Warning..! Unbale to intialize Serializer to the file " + path);
    System.out.println("Data may be lost if we close the program now");
    e.printStackTrace();
    return false;
    }
    }
    public Boolean serialize(T obj){
    if (closed){
    System.out.println("File closed");
    return false;
    }
    if (!wPerm){
    System.out.println("Doesn't have permissions to write to file");
    }
    try {
    objectoutStream.writeObject(obj);
    return true;
    } catch (Exception e) {
    System.out.println("Failed serializing the object" +obj);
    e.printStackTrace();
    return false;
    }
    }
    private ArrayList<T> deserializeload(){
    ArrayList<T> objs = new ArrayList<T>();
    try {
    Object obj;
    T type;
    while (true){
    obj = objectinstream.readObject();
    if (obj instanceof EndOftheStream){
    break;
    }
    type = (T) obj;
    objs.add(type);
    }
    }catch(EOFException eof){
    //this if fine;
    }catch (Exception e) {
    System.out.println("Failed deserializing the file");
    e.printStackTrace();
    }
    return objs;
    }
    public ArrayList<T> deserialize(){
    if (!rPerm){
    System.out.println("Doen't have permissions to read the file");
    return new ArrayList<T>();
    }
    if (closed){
    System.out.println("File Closed");
    return new ArrayList<T>();
    }
    if (content == null){
    return deserializeload();
    }
    return content;
    }
    public void close(){
    if (objectoutStream!=null){
    try {
    objectoutStream.writeObject(new EndOftheStream());
    } catch (IOException e1) {
    //Do nothing
    }
    try {
    objectoutStream.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    if (fileoutStream!=null){
    try {
    fileoutStream.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    if (objectinstream!=null){
    try {
    objectinstream.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    if (fileinStream!=null){
    try {
    fileinStream.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    }
    @Override
    protected void load() {
    content = deserializeload(); 
    }
    @Override
    protected Boolean validFiletype(String type) {
    return (type.equals("txt") || type.equals("ser"));
    }
   }
   