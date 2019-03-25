import javafx.animation.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import javafx.scene.control.*;

public class MainClass extends Application{
  @Override
  public void start(Stage primaryStage){

    BorderPane menuMain = new BorderPane();
    menuMain.setStyle("-fx-background-image: url(\"res/backMain.png\")");
    BorderPane customizeMain = new BorderPane();
    customizeMain.setStyle("-fx-background-image: url(\"res/backCust.png\")");
    BorderPane serverMain = new BorderPane();
    serverMain.setStyle("-fx-background-image: url(\"res/backMulti.png\")");

    Pane aviPane = new Pane();
    aviPane.setStyle("-fx-background-color: transparent;");
    Pane selectPane = new Pane();
    selectPane.setStyle("-fx-background-color: transparent;");
    GridPane customizePane = new GridPane();
    customizePane.setAlignment(Pos.CENTER);
    customizePane.setStyle("-fx-background-color: transparent;");

    GridPane serverOptionPane = new GridPane();
    serverOptionPane.setAlignment(Pos.CENTER);
    serverOptionPane.setStyle("-fx-background-color: transparent;");

    Button btnExit = new Button("Exit");
    btnExit.setMaxSize(100,200);
    HBox exiPane = new HBox();
    exiPane.setAlignment(Pos.BASELINE_RIGHT);
    exiPane.getChildren().add(btnExit);

    Button btnCreateServ = new Button("Create");
    Button btnJoinServ = new Button("Join");

    serverOptionPane.add(btnCreateServ,0,0);
    serverOptionPane.add(btnJoinServ,1,0);

    UserFileAccess F = new UserFileAccess();

    UserClass userMain = makeUser("","#cc8099",1,1);
    try{
      userMain = F.GetInfo(1);
    }catch (Exception ex){

    }

    CurrentUser currUser = new CurrentUser(userMain);


    new Thread(new AvatarAnimate(new Avatar(200,300,currUser.getCurrentUser()), aviPane,menuMain)).start();



    /*
    Avatar avt2 = new Avatar(200,400);
    AvatarAnimate ani2 = new AvatarAnimate(avt2, aviPane);
    Thread t3 = new Thread(ani2);
    */

    /*t3.start();
    */

    MenuSelect selectMain = new MenuSelect(selectPane);
    Thread tMainSel = new Thread(selectMain);
    tMainSel.start();

    /*Circle temp = avt1.getMainCircleArea();
    temp.setCenterX(90);
    avt1.setMainCircleArea(temp);
    System.out.println(avt1.getMainCircleArea().getCenterX());
    */

    menuMain.setLeft(aviPane);
    menuMain.setCenter(selectPane);

    Scene mainMenuScene = new Scene(menuMain,800,600);



    customizeMain.setCenter(customizePane);


    Scene customizeScene = new Scene(customizeMain,800,600);



    Scene serverScene = new Scene(serverMain,800,600);

    Scene serverOptionScene = new Scene(serverOptionPane,800,600);




    primaryStage.setTitle(" ");
    primaryStage.setScene(mainMenuScene);
    primaryStage.setResizable(false);
    primaryStage.show();
    selectPane.requestFocus();

    btnExit.setOnAction(e->{
      if (primaryStage.getScene() != mainMenuScene){
        /*
        if(primaryStage.getScene() == serverScene){
          chat1.killChat();
        }
        */
        menuMain.setLeft(aviPane);
        aviPane.getChildren().clear();
        Avatar avtMain = new Avatar(200,300,currUser.getCurrentUser());
        AvatarAnimate aniMain = new AvatarAnimate(avtMain, aviPane,menuMain);
        new Thread(aniMain).start();
        //new Thread(aniMain).start();
        /*System.out.println(tMainAvi.isAlive());
        System.out.println(tMainAvi.isInterrupted());
        tMainAvi.start();
        */
        primaryStage.setScene(mainMenuScene);
      }

    });

    btnJoinServ.setOnAction(e->{
      serverMain.setLeft(aviPane);
      serverMain.setBottom(exiPane);
      aviPane.getChildren().clear();

      /*
      Pane aviPaneOther = new Pane();
      aviPaneOther.setStyle("-fx-background-color: transparent;");


      Avatar avtServOther = new Avatar(100,300,currUser.getCurrentUser());
      AvatarAnimate aniServOther = new AvatarAnimate(avtServOther, aviPaneOther,serverMain);
      Thread tServOther = new Thread(aniServOther);
      tServOther.start();

      serverMain.setRight(aviPaneOther);
      */
      Avatar avtServUser = new Avatar(100,300,currUser.getCurrentUser());
      AvatarAnimate aniServUser = new AvatarAnimate(avtServUser, aviPane,serverMain);
      Thread tServUser = new Thread(aniServUser);
      tServUser.start();

      Chat chat1 = new Chat(serverMain,currUser.getCurrentUser().getUsername());
      new Thread(chat1).start();
      primaryStage.setScene(serverScene);
    });

    btnCreateServ.setOnAction(e->{
      new Thread(new ServerMaker()).start();
      serverMain.setLeft(aviPane);
      serverMain.setBottom(exiPane);
      aviPane.getChildren().clear();

      /*
      Pane aviPaneOther = new Pane();
      aviPaneOther.setStyle("-fx-background-color: transparent;");


      Avatar avtServOther = new Avatar(100,300,currUser.getCurrentUser());
      AvatarAnimate aniServOther = new AvatarAnimate(avtServOther, aviPaneOther,serverMain);
      Thread tServOther = new Thread(aniServOther);
      tServOther.start();

      serverMain.setRight(aviPaneOther);
      */
      Avatar avtServUser = new Avatar(100,300,currUser.getCurrentUser());
      AvatarAnimate aniServUser = new AvatarAnimate(avtServUser, aviPane,serverMain);
      Thread tServUser = new Thread(aniServUser);
      tServUser.start();

      Chat chat1 = new Chat(serverMain,currUser.getCurrentUser().getUsername());
      new Thread(chat1).start();
      primaryStage.setScene(serverScene);

    });


    selectPane.setOnKeyPressed(e -> {
        switch (e.getCode()) {
          case DOWN: {
              selectMain.animateToNext();
              break;
            }

            case ENTER: {
              if (selectMain.getISelect() == 1){
                customizeMain.setLeft(aviPane);
                customizeMain.setBottom(exiPane);
                aviPane.getChildren().clear();
                Avatar avtCust = new Avatar(200,300,currUser.getCurrentUser());
                AvatarAnimate aniCust = new AvatarAnimate(avtCust, aviPane,customizeMain);
                Thread tCust = new Thread(aniCust);
                tCust.start();
                Customize cust = new Customize(customizePane,avtCust,currUser);
                Thread customizeThread = new Thread(cust);
                customizeThread.start();
                primaryStage.setScene(customizeScene);

              }else if(selectMain.getISelect() == 2){
                primaryStage.close();
              }else{

                primaryStage.setScene(serverOptionScene);
              }
              break;
            }
          }

    });

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

  public static void main(String[] args){
    launch(args);
  }


}
