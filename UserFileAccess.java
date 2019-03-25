//Libraries used
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.*;


public class UserFileAccess { //Made to access/create user files
    private String folder = "res\\Users\\"; //Directory
    private UserClass user;
    public UserFileAccess(){
        //Default Constructor
    }
    public UserFileAccess(UserClass user){
        this.user = user;
    }
    public void AddUser(UserClass user) throws IOException { //Pass a user class into this;
        String filepath = folder + user.getID() +".dat"; //Sets the file to write to
        File file = new File(filepath);
        if (file.exists()) { //File needs to not exist to call this method
           if(!file.delete()) System.out.println("ERR:File did not delete properly");
       }
        boolean newFile = file.createNewFile(); //Is new file created
        if (newFile) {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(file)); //Opens outstream
            output.writeUTF(user.getUsername()); //Username
            output.writeUTF(user.getColour()); //Colour
            output.writeInt(user.getEyetype());//Eyetype
            output.writeInt(user.getMouthtype());//Mouthtype
            output.close();
        }
    }
    public UserClass GetInfo(int id) throws IOException{
        String filepath = folder + id +".dat";
        File file = new File(filepath);
        if(!file.exists()) System.exit(-1);
        DataInputStream input = new DataInputStream(new FileInputStream(file));
        String userna = input.readUTF(); //Username
        String colo = input.readUTF(); //Colour
        int ei = input.readInt();//Mouthtype
        int mi = input.readInt();//Eyetype
        UserClass user = makeUser(userna, colo, ei, mi);
        input.close();
        return user;
    }

    public int getMaxID(){

      try{
        String filepath = folder + "numUsers.dat";
        File file = new File(filepath);
        DataInputStream input = new DataInputStream(new FileInputStream(file));
        int total = input.readInt();
        input.close();
        return total;
      }
      catch(IOException ex){
        return 0;
      }
    }
    public int IncrementUsersNumber() throws IOException{ //Increments it for the number of users
        String filepath = folder + "numUsers.dat";
        File file = new File(filepath);
        if(!file.exists()){
            file.createNewFile(); //Creates the file
            DataOutputStream output = new DataOutputStream(new FileOutputStream(file)); //Opens outstream
            output.writeInt(1); //This is where we would put the information we get
            output.close();
            return 1;
        }
        else{ //The file exists
            //Input
            DataInputStream input = new DataInputStream(new FileInputStream(file));
            int total = input.readInt();
            input.close();
            total++;
            //Output
            DataOutputStream output = new DataOutputStream(new FileOutputStream(file)); //Opens outstream
            output.writeInt(total);
            output.close();
            return total;
        }
    }
    public UserClass makeUser(String username, String colour, int mouthtype, int eyetype){
      UserClass user;
      try{
      user = new UserClass(username, colour, mouthtype, eyetype);
    }catch (Exception ie){
      System.out.println("Oh darn");
      user = new UserClass();
    }
    return user;
    }
}
