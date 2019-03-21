import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;

public class MainClass extends Application{
  @Override
  public void start(Stage primaryStage){
    BorderPane menuMain = new BorderPane();
    menuMain.setStyle("-fx-background-color: pink;");
    Pane aviPane = new Pane();
    aviPane.setStyle("-fx-background-color: transparent;");
    Pane selectPane = new Pane();
    selectPane.setStyle("-fx-background-color: transparent;");

    Avatar avt1 = new Avatar(200,200);
    AvatarAnimate ani1 = new AvatarAnimate(avt1, aviPane,menuMain);
    Thread t1 = new Thread(ani1);
    /*
    Avatar avt2 = new Avatar(200,400);
    AvatarAnimate ani2 = new AvatarAnimate(avt2, aviPane);
    Thread t3 = new Thread(ani2);
    */

    t1.start();

    /*t3.start();
    */

    MenuSelect selectM = new MenuSelect(selectPane);
    Thread t2 = new Thread(selectM);
    t2.start();

    /*Circle temp = avt1.getMainCircleArea();
    temp.setCenterX(90);
    avt1.setMainCircleArea(temp);
    System.out.println(avt1.getMainCircleArea().getCenterX());
    */
    avt1.setImgEye(new Image("res/e4.png"));

    menuMain.setLeft(aviPane);
    menuMain.setCenter(selectPane);

    primaryStage.setTitle(" ");
    primaryStage.setScene(new Scene(menuMain, 1000, 600));
    primaryStage.show();
    selectPane.requestFocus();
  }

  public static void main(String[] args){
    launch(args);
  }
}
