public abstract class Record {
    protected Boolean closed;
    protected Boolean wPerm;
    protected Boolean rPerm;
    public Record(String path, String permission){
    String splits[] = path.split("[.]");
    if (splits.length<2){
    System.out.println("Invalid file path");
    closed=true;
    return;
    }
    if (!validFiletype(splits[1])){
    /*Even if it's not a valid file type it will allow user to read and write
    * After warning
    */
    System.out.println("File type is non text format hight risk of data corruption..!");
    }
    if (permission.equals("r")){
    wPerm = false;
    rPerm = getReaderReady(path);
    closed = !rPerm;
    }else if (permission.equals("w")){
    rPerm = false;
    wPerm = getWriterReady(path);
    closed = !wPerm;
    }else if (permission.equals("rw")){
    rPerm = getReaderReady(path);
    if (rPerm){
    load();
    }
    wPerm = getWriterReady(path);
    closed = !(wPerm || rPerm);
    }else{
    System.out.println("Permission type error");
    closed= true;
    }
    } 
    protected abstract Boolean getReaderReady(String path);
    protected abstract Boolean getWriterReady(String path);
    protected abstract void load();
    protected abstract Boolean validFiletype(String type);//checkes whether a valid file type
   }
