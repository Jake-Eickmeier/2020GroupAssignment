import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
import javafx.animation.*;

public class CustomizeTest extends Application{
  @Override
  public void start(Stage primaryStage){
    BorderPane menuMain = new BorderPane();
    menuMain.setStyle("-fx-background-color: violet;");
    Pane aviPane = new Pane();
    aviPane.setStyle("-fx-background-color: transparent;");
    GridPane selectPane = new GridPane();
    selectPane.setAlignment(Pos.CENTER);
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

    Customize cust = new Customize(selectPane,avt1);
    Thread t2 = new Thread(cust);
    t2.start();

    /*Circle temp = avt1.getMainCircleArea();
    temp.setCenterX(90);
    avt1.setMainCircleArea(temp);
    System.out.println(avt1.getMainCircleArea().getCenterX());
    */
    avt1.setImgEye(new Image("res/e4.png"));

    menuMain.setCenter(aviPane);
    menuMain.setRight(selectPane);

    primaryStage.setTitle(" ");
    primaryStage.setScene(new Scene(menuMain, 1200, 600));
    primaryStage.show();
    selectPane.requestFocus();
  }

  public static void main(String[] args){
    launch(args);
  }
}
