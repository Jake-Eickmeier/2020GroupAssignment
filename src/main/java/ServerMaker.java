public class ServerMaker implements Runnable{
  ChatServer cServ;
  
  public ServerMaker(){}

  public void run(){
    cServ = new ChatServer(8000);
    cServ.execute();
  }
}
