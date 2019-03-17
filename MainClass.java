import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.input.KeyCode;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.animation.Animation;

public class MainClass extends Application{
  @Override
  public void start(Stage primaryStage){
    BorderPane menuMain = new BorderPane();
    menuMain.setStyle("-fx-background-color: violet;");
    Pane aviPane = new Pane();
    aviPane.setStyle("-fx-background-color: yellow;");
    Pane selectPane = new Pane();
    selectPane.setStyle("-fx-background-color: pink;");



    Avatar avt1 = new Avatar(200,200);
    AvatarAnimate ani1 = new AvatarAnimate(avt1, aviPane);
    Thread t1 = new Thread(ani1);
    /*
    Avatar avt2 = new Avatar(200,200);
    AvatarAnimate ani2 = new AvatarAnimate(avt2, aviPane);
    Thread t3 = new Thread(ani2);
    */
    t1.start();
    /*
    }catch (InterruptedException e){

    }

    t3.start();*/

    MenuSelect selectM = new MenuSelect(selectPane);
    Thread t2 = new Thread(selectM);
    t2.start();

    /*Circle temp = avt1.getMainCircleArea();
    temp.setCenterX(90);
    avt1.setMainCircleArea(temp);
    System.out.println(avt1.getMainCircleArea().getCenterX());
    */

    menuMain.setLeft(aviPane);
    menuMain.setCenter(selectPane);

    primaryStage.setTitle(" ");
    primaryStage.setScene(new Scene(menuMain, 1200, 600));
    primaryStage.show();
    selectPane.requestFocus();


  }
  public static void main(String[] args){
    launch(args);
  }

}
