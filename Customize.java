import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.image.Image;
import javafx.geometry.Pos;

public class Customize implements Runnable{
  GridPane pane;
  Avatar avtr;
  Button btnMouthLeft, btnMouthRight, btnEyeLeft, btnEyeRight;
  ColorPicker colpick;
  Color col;
  MouthIndex mi;
  EyeIndex ei;

  public Customize(GridPane p, Avatar avt){
    pane = p;
    avtr = avt;
    mi = new MouthIndex(5);
    ei = new EyeIndex(5,4);
    btnMouthLeft = new Button("Left");
    btnMouthRight = new Button("Right");
    btnEyeLeft = new Button("Left");
    btnEyeRight = new Button("Right");
    colpick= new ColorPicker();

  }
  @Override
  public void run(){
    Platform.runLater(()->{

      pane.add(btnMouthLeft,0,0);
      pane.add(new Text("Mouth"),1,0);
      pane.add(btnMouthRight,2,0);
      pane.add(btnEyeLeft,0,1);
      pane.add(new Text("Eye"),1,1);
      pane.add(btnEyeRight,2,1);
      pane.add(colpick,2,2);

      btnMouthLeft.setOnAction(e->{
        mi.incDownMIndex();
        //System.out.println(mi.getMIndex());
        avtr.setImgMouth(new Image(String.format("res/m%d.png",mi.getMIndex())));
      });

      btnMouthRight.setOnAction(e->{
        mi.incUpMIndex();
        //System.out.println(mi.getMIndex());
        avtr.setImgMouth(new Image(String.format("res/m%d.png",mi.getMIndex())));
      });

      btnEyeLeft.setOnAction(e->{
        ei.incDownEIndex();
        //System.out.println(mi.getMIndex());
        avtr.setImgEye(new Image(String.format("res/e%d.png",ei.getEIndex())));
        avtr.setIsGlasses(ei.getGlassesind());
      });

      btnEyeRight.setOnAction(e->{
        ei.incUpEIndex();
        //System.out.println(mi.getMIndex());
        avtr.setImgEye(new Image(String.format("res/e%d.png",ei.getEIndex())));
        avtr.setIsGlasses(ei.getGlassesind());
      });

      colpick.setOnAction(e->{
        Color c = colpick.getValue();
        System.out.println(c);
        avtr.setColor(c);
      });
    });
  }

}

class MouthIndex{
  int ind, maxind;
  public MouthIndex(int maxIndex){
    ind = 1;
    maxind = maxIndex;
  }
  public int getMIndex(){
    return ind;
  }
  public void setMIndex(int it){
    ind = it;
  }
  public void incUpMIndex(){
    ind ++;
    if (ind == maxind+1){
      ind = 1;
    }
  }
  public void incDownMIndex(){
    ind --;
    if (ind == 0){
      ind = maxind;
    }
  }
}

class EyeIndex{
  int ind, maxind, glassesIndex;
  public EyeIndex(int maxIndex, int glassesind){
    ind = 1;
    maxind = maxIndex;
    glassesIndex = glassesind;
  }
  public int getEIndex(){
    return ind;
  }
  public void setEIndex(int it){
    ind = it;
  }
  public void incUpEIndex(){
    ind ++;
    if (ind == maxind+1){
      ind = 1;
    }
  }
  public Boolean getGlassesind(){
    if (ind >= glassesIndex){
      return true;
    }else{
      return false;
    }
  }
  public void incDownEIndex(){
    ind --;
    if (ind == 0){
      ind = maxind;
    }
  }
}
