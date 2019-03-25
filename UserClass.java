//Imports

import java.io.IOException;

public class UserClass{
    private String username = "";
    private String colour = "";
    private int mouthtype = 0;
    private int eyetype = 0;
    private int id=0;
    public UserClass(String username, String colour, int mouthtype, int eyetype) throws IOException{
    //Setting information
        this.username = username;
        this.colour = colour;
        this.mouthtype = mouthtype;
        this.eyetype = eyetype;


}

public UserClass(String username, String colour, int mouthtype, int eyetype, int id) throws IOException{
//Setting information
    this.username = username;
    this.colour = colour;
    this.mouthtype = mouthtype;
    this.eyetype = eyetype;
    this.id = id;


}

    public UserClass(){

    }
    public void Save() throws IOException{
      UserFileAccess F = new UserFileAccess(this);
      if (this.id == 0) this.id = findId();
      F.AddUser(this);
      System.out.println(this.id);
    }
    public int findId() throws IOException{
        UserFileAccess F = new UserFileAccess();
        this.id = F.IncrementUsersNumber();
        System.out.println(this.id);
        return id;
    }
    //Setters
    public void setUsername(String username){ this.username = username; }
    public void setColour(String colour){ this.colour = colour; }
    public void setMouthtype(int mouthtype){ this.mouthtype = mouthtype; }
    public void setEyetype(int eyetype){ this.eyetype = eyetype; }
    public void setID(int id){this.id = id;}
    //Accessors
    public String getUsername(){ return this.username; }
    public String getColour(){ return this.colour;}
    public int getMouthtype(){return this.mouthtype;}
    public int getEyetype(){return this.eyetype;}
    public int getID(){ return this.id;}
    /*
    public static void main(String args[]) throws IOException{
        //This is a function to test
        UserClass user = new UserClass("myusername", "blue", 1,2);
    }*/
}
