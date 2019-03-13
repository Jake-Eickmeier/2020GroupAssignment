import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Avatar{
  Image imgMouth;
  ImageView imgMouthView;
  double hue;
  double saturation;
  double brightness;
  Circle head;
  Ellipse eye1;
  Ellipse eye2;
  Circle pupil1;
  Circle pupil2;
  Circle mainCircleArea;
  Color col;

  public Avatar(){
    mainCircleArea = new Circle(200,200,150);
    mainCircleArea.setStroke(Color.BLACK);
    mainCircleArea.setFill(Color.WHITE);


    head = new Circle(200,200,50);
    head.setStroke(Color.RED);
    head.setFill(Color.RED);

    pupil1 = new Circle(175,180,5);
    pupil1.setStroke(Color.BLACK);
    pupil1.setFill(Color.BLACK);

    pupil2 = new Circle(225,180,5);
    pupil2.setStroke(Color.BLACK);
    pupil2.setFill(Color.BLACK);

    eye1 = new Ellipse(175,180,25,25);
    eye1.setStroke(Color.BLACK);
    eye1.setFill(Color.WHITE);

    eye2 = new Ellipse(225,180,25,25);
    eye2.setStroke(Color.BLACK);
    eye2.setFill(Color.WHITE);

    hue = 1900;

    /*
    Image imgEye = new Image("res/ey.png");
    ImageView r1 = new ImageView(imgEye);
    */

    imgMouth = new Image("res/m1.png");
    imgMouthView  = new ImageView(imgMouth);
    imgMouthView.setX(170);
    imgMouthView.setY(210);

    /*
    Rectangle r1 = new Rectangle(150,160,100,40);
    r1.setStroke(Color.TRANSPARENT);
    r1.setFill(Color.TRANSPARENT);
    */

    /*

    Rectangle r2 = new Rectangle(170,210,60,30);
    r2.setStroke(Color.BLUE);
    r2.setFill(Color.BLUE);
    */

  }

  public Circle getHead(){
    return head;
  }
  public Ellipse getEye1(){
    return eye1;
  }
  public Ellipse getEye2(){
    return eye2;
  }
  public Circle getPupil1(){
    return pupil1;
  }
  public Circle getPupil2(){
    return pupil2;
  }
  public Circle getMainCircleArea(){
    return mainCircleArea;
  }
  public ImageView getImgMouthView(){
    return imgMouthView;
  }
  public double getHue(){
    return hue;
  }





  public void setHead(Circle h){
    head = h;
  }
  public void setEye1(Ellipse e1){
    eye1 = e1;
  }
  public void setEye2(Ellipse e2){
    eye2 = e2;
  }
  public void setPupil1(Circle p1){
    pupil1 = p1;
  }
  public void setPupil2(Circle p2){
    pupil2 = p2;
  }
  public void setMainCircleArea(Circle mca){
    mainCircleArea = mca;
  }
  public void setImgMouthView(ImageView imv){
    imgMouthView = imv;
  }
  public void setHue(double hu){
    hue = hu;
  }



}
