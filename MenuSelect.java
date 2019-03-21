import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;

public class MenuSelect implements Runnable{
  Rectangle item1, item2, item3;
  Ellipse menuPath;
  ScaleTransition st1, st2, st3;
  PathTransition pt1, pt2, pt3;
  Timeline setup, select;
  Pane pane;
  ItemSelect iSelect;

  Rectangle[] items;

  public MenuSelect(Pane p){
    pane = p;
    menuPath = new Ellipse(200,300,50,200);
    menuPath.setFill(Color.TRANSPARENT);
    menuPath.setStroke(Color.BLACK);
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

    iSelect = new ItemSelect();

    st1 = new ScaleTransition(Duration.millis(1500));
    st1.setByX(1.5);
    st1.setByY(1.5);
    st1.setCycleCount(Timeline.INDEFINITE);
    st1.setAutoReverse(true);
    st1.setNode(item1);

    st2 = new ScaleTransition(Duration.millis(1500));
    st2.setByX(1.5);
    st2.setByY(1.5);
    st2.setCycleCount(Timeline.INDEFINITE);
    st2.setAutoReverse(true);
    st2.setNode(item2);

    st3 = new ScaleTransition(Duration.millis(1500));
    st3.setByX(1.5);
    st3.setByY(1.5);
    st3.setCycleCount(Timeline.INDEFINITE);
    st3.setAutoReverse(true);
    st3.setNode(item3);

    pt1 = new PathTransition(Duration.millis(3000),menuPath,item1);
    pt1.setInterpolator(Interpolator.LINEAR);
    pt1.setCycleCount(Timeline.INDEFINITE);
    pt1.setAutoReverse(false);

    pt2 = new PathTransition(Duration.millis(3000),menuPath,item2);
    pt2.setInterpolator(Interpolator.LINEAR);
    pt2.setCycleCount(Timeline.INDEFINITE);
    pt2.setAutoReverse(false);

    pt3 = new PathTransition(Duration.millis(3000),menuPath,item3);
    pt3.setInterpolator(Interpolator.LINEAR);
    pt3.setCycleCount(Timeline.INDEFINITE);
    pt3.setAutoReverse(false);

    setup = new Timeline(
    new KeyFrame(Duration.millis(650), event -> {
      pt1.pause();
      st1.pause();
      pt2.pause();
      st2.pause();
      pt3.pause();
      st3.pause();
    }));

    select = new Timeline(
    new KeyFrame(Duration.seconds(0), event -> {
      pt1.play();
      st1.play();
      pt2.play();
      st2.play();
      pt3.play();
      st3.play();
    }),
    new KeyFrame(Duration.millis(1000), event -> {
      pt1.pause();
      st1.pause();
      pt2.pause();
      st2.pause();
      pt3.pause();
      st3.pause();
    }));
    select.setCycleCount(1);
    Rectangle[] temp = {item1,item2,item3};
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
      try
      {
        Thread.sleep(1000);
      }
      catch(InterruptedException ex)
      {
        Thread.currentThread().interrupt();
      }
      pt2.play();
      st2.play();
      try
      {
        Thread.sleep(1000);
      }
      catch(InterruptedException ex)
      {
        Thread.currentThread().interrupt();
      }
      pt3.play();
      st3.play();

      setup.play();

      pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e){
          switch (e.getCode()) {
            case DOWN: {select.play();
              if (select.getCurrentTime() == Duration.seconds(0)){
                iSelect.incItem();
                items[iSelect.getItem()].toFront();
              }
              break;
            }
          }
        }
      });
    });
  }

  public int getISelect(){
    return iSelect.getItem();
  }
}

class ItemSelect{
  int item;
  public ItemSelect(){
    item = 1;
  }
  public int getItem(){
    return item;
  }
  public void setItem(int it){
    item = it;
  }
  public void incItem(){
    item ++;
    if (item == 3){
      item = 0;
    }
  }
}
