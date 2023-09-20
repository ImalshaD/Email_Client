public class Encode {
    /*Used to encode and decode senstive data such as passwords */
    public static String encrypt(String newString,EncryptMechanism mechanism){
    //encodes the given String by the given mechanism
    return mechanism.encrypt(newString);
    }
    public static String decrypt(String oldString,EncryptMechanism mechanism){
    //decodes a given string by the given mechanism
    return mechanism.decrypt(oldString);
    }
   }
   
