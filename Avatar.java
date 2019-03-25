import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Avatar{

  //mouth and  eye objects
  Image imgMouth;
  ImageView imgMouthView;
  Image imgEye;
  ImageView imgEyeView;
  //head
  Circle head;
  //eyes are invisible act as position references for pupils
  Ellipse eye1, eye2;
  //pupil objects
  Circle pupil1, pupil2;
  //circle shape reference for active looking area
  Circle mainCircleArea;
  //heada colour
  Color col;
  //center position references
  double centerXMain, centerYMain;
  //value to check if avatar eyes obstruct pupils
  Boolean isGlasses;

  UserClass user;


  public Avatar(double mainX, double mainY, UserClass us){
    //set main avatar origin
    centerXMain = mainX;
    centerYMain = mainY;

    user = us;
    //create main circle area
    mainCircleArea = new Circle(centerXMain,centerYMain,150);
    mainCircleArea.setStroke(Color.TRANSPARENT);
    mainCircleArea.setFill(Color.TRANSPARENT);

    head = new Circle(centerXMain,centerYMain,50);
    head.setStroke(Color.TRANSPARENT);
    col = Color.web(user.getColour());
    head.setFill(col);

    pupil1 = new Circle(centerXMain-25,centerYMain-20,5);
    pupil1.setStroke(Color.TRANSPARENT);
    pupil1.setFill(Color.BLACK);

    pupil2 = new Circle(centerXMain+25,centerYMain-20,5);
    pupil2.setStroke(Color.TRANSPARENT);
    pupil2.setFill(Color.BLACK);

    eye1 = new Ellipse(centerXMain-25,centerYMain-20,25,25);
    eye1.setStroke(Color.TRANSPARENT);
    eye1.setFill(Color.TRANSPARENT);

    eye2 = new Ellipse(centerXMain+25,centerYMain-20,25,25);
    eye2.setStroke(Color.TRANSPARENT);
    eye2.setFill(Color.TRANSPARENT);

    /*
    Image imgEye = new Image("res/ey.png");
    ImageView r1 = new ImageView(imgEye);
    */

    int mindtemp = user.getMouthtype();
    int eindtemp = user.getEyetype();
    //create mouth objects
    imgMouth = new Image("res/m" + mindtemp + ".png");
    imgMouthView  = new ImageView(imgMouth);
    imgMouthView.setX(centerXMain-30);
    imgMouthView.setY(centerYMain+10);
    imgMouthView.setFitHeight(30);
    imgMouthView.setFitWidth(60);

    //create eye objects
    imgEye = new Image("res/e" + eindtemp + ".png");
    imgEyeView  = new ImageView(imgEye);
    imgEyeView.setX(centerXMain-50);
    imgEyeView.setY(centerYMain-40);
    imgEyeView.setFitHeight(40);
    imgEyeView.setFitWidth(100);

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

  private void checkIsGlasses(){
    //if avatar eyes block pupils make pupils transparent
    if (isGlasses == false){
      pupil1.setFill(Color.BLACK);
      pupil2.setFill(Color.BLACK);
    }else{
      pupil1.setFill(Color.TRANSPARENT);
      pupil2.setFill(Color.TRANSPARENT);
    }
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
  public ImageView getImgEyeView(){
    return imgEyeView;
  }
  public double getCenterXMain(){
    return centerXMain;
  }
  public double getCenterYMain(){
    return centerYMain;
  }




  public void setIsGlasses(Boolean isg){
    //set IsGlasses
    isGlasses = isg;
    this.checkIsGlasses();
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
  public void setImgEyeView(ImageView imv){
    imgEyeView = imv;
  }
  public void setImgMouth(Image imv){
    imgMouth = imv;
    //also change imageview to new image
    this.getImgMouthView().setImage(imgMouth);
  }
  public void setImgEye(Image imv){
    imgEye = imv;
    //also change imageview to new image
    this.getImgEyeView().setImage(imgEye);
  }
  public void setColor(Color color){
    col = color;
    //also change colour to new colour
    head.setFill(col);
  }

  public void setUser(UserClass us){
    this.user = us;
    this.setColor(Color.web(user.getColour()));
    int mindtemp = user.getMouthtype();
    int eindtemp = user.getEyetype();
    this.setImgMouth(new Image(String.format("res/m%d.png",mindtemp)));
    this.setImgEye(new Image(String.format("res/e%d.png",eindtemp)));

  }



}
