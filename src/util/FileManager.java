package util;

import java.io.*;

public class FileManager {

    public static void saveUser(Object obj,String path){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(obj);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Object loadUser(String path){
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(path))){
            return ois.readObject();
        }catch(Exception e){
            return null;
        }
    }
}