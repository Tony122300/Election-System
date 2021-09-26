import java.io.*;

public class FileUtil {

    public static void saveObjectByObjectOutput(Object o,String fileStr){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileStr));
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
            System. out .println( "" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object getObjectByObjectInput(String fileStr) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileStr));
            Object o = inputStream.readObject();
            inputStream.close();
            System. out .println( fileStr + "" );
            return o;
        } catch (Exception e) {
            //e.printStackTrace();
            System. out .println( fileStr + "" );
        }
        return null;
    }
}
