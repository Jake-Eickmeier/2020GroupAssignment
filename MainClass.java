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

    Pane aviPane = new Pane();
    aviPane.setStyle("-fx-background-color: transparent;");
    Pane selectPane = new Pane();
    selectPane.setStyle("-fx-background-color: transparent;");
    GridPane customizePane = new GridPane();
    customizePane.setAlignment(Pos.CENTER);
    customizePane.setStyle("-fx-background-color: transparent;");

    Button btnExit = new Button("Exit");
    btnExit.setMaxSize(100,200);
    HBox exiPane = new HBox();
    exiPane.setAlignment(Pos.BASELINE_RIGHT);
    exiPane.getChildren().add(btnExit);


    new Thread(new AvatarAnimate(new Avatar(200,300), aviPane,menuMain)).start();

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
    menuMain.setBottom(exiPane);

    Scene mainMenuScene = new Scene(menuMain,800,600);





    customizeMain.setCenter(customizePane);
    customizeMain.setBottom(exiPane);

    Scene customizeScene = new Scene(customizeMain,800,600);

    primaryStage.setTitle(" ");
    primaryStage.setScene(mainMenuScene);
    primaryStage.show();
    selectPane.requestFocus();

    btnExit.setOnAction(e->{
      if (primaryStage.getScene() == customizeScene){
        menuMain.setLeft(aviPane);
        aviPane.getChildren().clear();
        Avatar avtMain = new Avatar(200,300);
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


    selectPane.setOnKeyPressed(e -> {
        switch (e.getCode()) {
          case DOWN: {
              selectMain.animateToNext();
              System.out.println(selectMain.getISelect());
              break;
            }

            case ENTER: {
              if (selectMain.getISelect() == 1){
                customizeMain.setLeft(aviPane);
                aviPane.getChildren().clear();
                Avatar avtCust = new Avatar(200,300);
                AvatarAnimate aniCust = new AvatarAnimate(avtCust, aviPane,customizeMain);
                Thread tCust = new Thread(aniCust);
                tCust.start();
                Customize cust = new Customize(customizePane,avtCust);
                Thread customizeThread = new Thread(cust);
                customizeThread.start();
                primaryStage.setScene(customizeScene);
                break;
              }else if(selectMain.getISelect() == 2){
                primaryStage.close();
              }
            }
          }

    });

  }

  public static void main(String[] args){
    launch(args);
  }
}
