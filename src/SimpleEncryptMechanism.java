public class SimpleEncryptMechanism extends EncryptMechanism{
    /*Simple method used ASCII+1 for encrypting */
    @Override
    public String encrypt(String newString) {
    String result="";
    for (int i =0;i<newString.length();i++){
    int ascci = newString.charAt(i);
    ascci+=1;
    result+=(char) ascci;
    }
    return result;
    }
    @Override
    public String decrypt(String oldString) {
    String result="";
    for (int i =0;i<oldString.length();i++){
    int ascci = oldString.charAt(i);
    ascci-=1;
    result+=(char) ascci;
    }
    return result;
    }
    
   }
   