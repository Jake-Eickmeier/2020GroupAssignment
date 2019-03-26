import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.animation.*;

public class MenuSelect implements Runnable{
  ImageView item1, item2, item3;
  Ellipse menuPath;
  ScaleTransition st1, st2, st3;
  PathTransition pt1, pt2, pt3;
  Timeline setup, select;
  Pane pane;
  ItemSelect iSelect;
  Image item1img, item2img, item3img;

  ImageView[] items;

  public MenuSelect(Pane p){
    pane = p;
    menuPath = new Ellipse(200, 300, 50, 200);
    menuPath.setFill(Color.TRANSPARENT);
    menuPath.setStroke(Color.TRANSPARENT);

    item1img = new Image("res/btnChat.png");
    item2img = new Image("res/btnCustomize.png");
    item3img = new Image("res/btnExit.png");

    item1 = new ImageView(item1img);
    item1.setFitWidth(100);
    item1.setFitHeight(100);

    item2 = new ImageView(item2img);
    item2.setFitWidth(100);
    item2.setFitHeight(100);

    item3 = new ImageView(item3img);
    item3.setFitWidth(100);
    item3.setFitHeight(100);

    /*
    item1 = new Rectangle (100, 40, 100, 100);
    item1.setArcHeight(50);
    item1.setArcWidth(50);
    item1.setFill(Color.VIOLET);

    item2 = new Rectangle (100, 140, 100, 100);
    item2.setArcHeight(50);
    item2.setArcWidth(50);
    item2.setFill(Color.RED);
    item3 = new Rectangle (100, 240, 100, 100);
    item3.setArcHeight(50);
    item3.setArcWidth(50);
    item3.setFill(Color.GREEN);
    */

    iSelect = new ItemSelect();

    st1 = new ScaleTransition(Duration.millis(1500));
    ScaleSetup(st1, item1);

    st2 = new ScaleTransition(Duration.millis(1500));
    ScaleSetup(st2, item2);

    st3 = new ScaleTransition(Duration.millis(1500));
    ScaleSetup(st3, item3);

    pt1 = new PathTransition(Duration.millis(3000),menuPath,item1);
    PathSetup(pt1);

    pt2 = new PathTransition(Duration.millis(3000),menuPath,item2);
    PathSetup(pt2);

    pt3 = new PathTransition(Duration.millis(3000),menuPath,item3);
    PathSetup(pt3);

    setup = new Timeline(
    new KeyFrame(Duration.millis(650), event -> {
      pt1.pause(); pt2.pause(); pt3.pause();
      st1.pause(); st2.pause(); st3.pause();
    }));

    select = new Timeline(
    new KeyFrame(Duration.seconds(0), event -> {
      pt1.play(); pt2.play(); pt3.play();
      st1.play(); st2.play(); st3.play();
    }),
    new KeyFrame(Duration.millis(1000), event -> {
      pt1.pause(); pt2.pause(); pt3.pause();
      st1.pause(); st2.pause(); st3.pause();
    }));
    select.setCycleCount(1);
    ImageView[] temp = {item1,item2,item3};
    items = temp;
  }

  @Override
  public void run(){
    Platform.runLater(() -> {
      pane.getChildren().add(menuPath);
      pane.getChildren().add(item1);
      pane.getChildren().add(item2);
      pane.getChildren().add(item3);
      item2.toFront();
      pt1.play();
      st1.play();

      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }

      pt2.play();
      st2.play();

      try {
        Thread.sleep(1000);
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }

      pt3.play();
      st3.play();

      setup.play();
    });
  }

  public int getISelect() {return iSelect.getItem();}

  public void animateToNext() {
    select.play();
    if (select.getCurrentTime() == Duration.seconds(0) && setup.getStatus() == Animation.Status.STOPPED) {
      iSelect.incItem();
      items[iSelect.getItem()].toFront();
    }
  }

  public void ScaleSetup(ScaleTransition st, ImageView item) {
    st.setByX(1.5);
    st.setByY(1.5);
    st.setCycleCount(Timeline.INDEFINITE);
    st.setAutoReverse(true);
    st.setNode(item);
  }

  public void PathSetup(PathTransition pt) {
    pt.setInterpolator(Interpolator.LINEAR);
    pt.setCycleCount(Timeline.INDEFINITE);
    pt.setAutoReverse(false);
  }
}

class ItemSelect{
  int item;

  public ItemSelect() {item = 1;}
  
  public int getItem() {return item;}
  public void setItem(int it) {item = it;}

  public void incItem(){
    item ++;
    if (item == 3){
      item = 0;
    }
  }
}
