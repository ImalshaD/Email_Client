import java.io.Serializable;
import java.util.ArrayList;

public class Tree <T extends IComparable> extends Container<T> implements Serializable{
    /*Data Structure uses to store data */
    private ArrayList<T> head;
    private IComparing cmp;
    private Tree<T> left;
    private Tree<T> right;
    public Tree() {
    this.head = new ArrayList<T>();
    this.cmp =null;
    this.left = null;
    this.right = null;
    }
    public void insert(T object){
    try{
    if (cmp==null){
    head.add(object);
    cmp=object.getObject();
    }else if (cmp.isEqual(object.getObject())){
    head.add(object);
    }else if (cmp.greaterThan(object.getObject())){
    if (right==null){
    right = new Tree<T> ();
    }
    right.insert(object);
    }else{
    if (left==null){
    left = new Tree<T>();
    }
    left.insert(object);
    }
    }catch(IllegalArgumentException e){
    e.printStackTrace();
    return;
    }
    }
    public T search(IComparable obj){
    ArrayList<T> newList = search(obj.getObject());
    for (T t : newList){
    if (t.equals(obj)){
    return t;
    }
    }
    return null;
    }
    public ArrayList<T> search(IComparing obj){
    if (cmp==null){
    return new ArrayList<T> ();
    }else if (cmp.isEqual(obj)){
    return head;
    }else if (cmp.greaterThan(obj)){
    if (right!=null){
    return right.search(obj);
    }else{
    return new ArrayList<T> ();
    }
    }else {
    if (left!=null){
    return left.search(obj);
    }else{
    return new ArrayList<T> ();
    }
    }
    }
    public void print(){
    if (cmp==null){
    return;
    }else{
    if (left!=null){
    left.print();
    }
    for (T t : head){
    System.out.println(t.toString());
    }
    if (right!=null){
    right.print();
    }
    }
    }
    public String toString(){
    String newString="";
    if (cmp==null){
    return newString;
    }else{
    if (left!=null){
    newString+=left.toString();
    }
    for (T t : head){
    newString+=t.toString()+"\n";
    }
    if (right!=null){
    newString+=right.toString()+"\n";
    }
    }
    return newString;
    }
   }
