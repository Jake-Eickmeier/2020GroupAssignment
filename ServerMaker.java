public class ServerMaker implements Runnable{
  public ServerMaker(){

  }
  public void run(){
    ChatServer cServ = new ChatServer(8000);
    cServ.execute();
  }
}
