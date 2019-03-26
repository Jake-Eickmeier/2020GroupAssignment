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
  Button btnMouthLeft, btnMouthRight, btnEyeLeft, btnEyeRight, btnSave, btnLoad;
  ColorPicker colpick;
  Color col;
  MouthIndex mi;
  EyeIndex ei;
  TextField usernameField;
  TextField loadId;
  CurrentUser currentUser;

  public Customize(GridPane p, Avatar avt, CurrentUser currUs){
    pane = p;
    avtr = avt;
    currentUser = currUs;
    col = Color.web("White");
    mi = new MouthIndex(5);
    ei = new EyeIndex(5,4);
    btnMouthLeft = new Button("Left");
    btnMouthRight = new Button("Right");
    btnEyeLeft = new Button("Left");
    btnEyeRight = new Button("Right");
    colpick= new ColorPicker();
    btnSave = new Button("Save");
    btnLoad = new Button("Load");
    usernameField = new TextField();
    loadId = new TextField();
    usernameField.setPromptText("Enter Username");
    loadId.setPromptText("Enter load ID");
  }

  @Override
  public void run(){
    Platform.runLater(() -> {
      pane.add(btnMouthLeft, 0, 0);
      pane.add(new Text("Mouth"), 1, 0);
      pane.add(btnMouthRight, 2, 0);
      pane.add(btnEyeLeft, 0, 1);
      pane.add(new Text("Eye"), 1, 1);
      pane.add(btnEyeRight, 2, 1);
      pane.add(colpick, 2, 2);
      pane.add(btnSave, 0, 3);
      pane.add(btnLoad, 2, 3);
      pane.add(usernameField, 0, 4);
      pane.add(loadId, 2, 5);

      btnMouthLeft.setOnAction(e -> {
        mi.incDownMIndex();
        //System.out.println(mi.getMIndex());
        avtr.setImgMouth(new Image(String.format("res/m%d.png", mi.getMIndex())));
      });

      btnMouthRight.setOnAction(e -> {
        mi.incUpMIndex();
        //System.out.println(mi.getMIndex());
        avtr.setImgMouth(new Image(String.format("res/m%d.png", mi.getMIndex())));
      });

      btnEyeLeft.setOnAction(e -> {
        ei.incDownEIndex();
        //System.out.println(mi.getMIndex());
        avtr.setImgEye(new Image(String.format("res/e%d.png", ei.getEIndex())));
        avtr.setIsGlasses(ei.getGlassesind());
      });

      btnEyeRight.setOnAction(e -> {
        ei.incUpEIndex();
        //System.out.println(mi.getMIndex());
        avtr.setImgEye(new Image(String.format("res/e%d.png", ei.getEIndex())));
        avtr.setIsGlasses(ei.getGlassesind());
      });

      btnSave.setOnAction(e -> {
        UserClass user = makeUser(usernameField.getText(), "" + col, ei.getEIndex(), mi.getMIndex());

        try {
          user.Save();
        } catch (Exception ex ) {
          System.out.println("Unable to save");
        }
      });

      btnLoad.setOnAction(e -> {
        UserFileAccess ac = new UserFileAccess();
        int maxid = ac.getMaxID();
        System.out.println(maxid);
        try {
          int curid = Integer.parseInt(loadId.getText().trim());
          if (curid <= 0 || curid > maxid){
            loadId.setText("Please Enter Valid Id to load");
          } else {
            currentUser.setCurrentUser(ac.GetInfo(curid));
            avtr.setUser(currentUser.getCurrentUser());
            usernameField.setText(currentUser.getCurrentUser().getUsername());
            EyeIndex tempEID = new EyeIndex(5,4);
            tempEID.setEIndex(currentUser.getCurrentUser().getEyetype());
            avtr.setIsGlasses(tempEID.getGlassesind());
          }
        } catch (Exception ex) {
          System.out.println(ex);
          loadId.setText("Please enter id to load");
        }
      });

      colpick.setOnAction(e -> {
        col = colpick.getValue();
        avtr.setColor(col);
      });
    });
  }

  public UserClass makeUser(String username, String colour, int mouthtype, int eyetype) {
    UserClass user;
    try {
      user = new UserClass(username, colour, mouthtype, eyetype);
    } catch (Exception ie) {
      System.out.println("Oh darn");
      user = new UserClass();
    }
    return user;
  }
}

class MouthIndex {
  int ind, maxind;
  public MouthIndex(int maxIndex) {
    ind = 1;
    maxind = maxIndex;
  }

  public int getMIndex() {return ind;}
  public void setMIndex(int it) {ind = it;}

  public void incUpMIndex() {
    ind ++;
    if (ind == maxind+1) {
      ind = 1;
    }
  }

  public void incDownMIndex() {
    ind --;
    if (ind == 0) {
      ind = maxind;
    }
  }
}

class EyeIndex {
  int ind, maxind, glassesIndex;
  public EyeIndex(int maxIndex, int glassesind) {
    ind = 1;
    maxind = maxIndex;
    glassesIndex = glassesind;
  }
  public int getEIndex() {return ind;}
  public void setEIndex(int it) {ind = it;}
  public void incUpEIndex() {
    ind ++;
    if (ind == maxind+1) {
      ind = 1;
    }
  }
  public Boolean getGlassesind() {
    if (ind >= glassesIndex) {
      return true;
    } else {
      return false;
    }
  }
  public void incDownEIndex() {
    ind --;
    if (ind == 0){
      ind = maxind;
    }
  }
}
