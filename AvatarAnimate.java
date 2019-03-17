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

public class AvatarAnimate implements Runnable{
  Avatar avtr;
  Pane pane;
  public AvatarAnimate(Avatar avt, Pane p){
    avtr = avt;
    pane = p;
  }

  @Override
  public void run(){

    pane.getChildren().add(avtr.getMainCircleArea());
    pane.getChildren().add(avtr.getHead());
    pane.getChildren().add(avtr.getImgMouthView());
    pane.getChildren().add(avtr.getEye1());
    pane.getChildren().add(avtr.getEye2());
    pane.getChildren().add(avtr.getPupil1());
    pane.getChildren().add(avtr.getPupil2());

    pane.setOnMouseMoved(e -> {
      double xd = (e.getX()-avtr.getMainCircleArea().getCenterX());
      double yd = (e.getY()-avtr.getMainCircleArea().getCenterY());
      double dnorm = Math.sqrt(Math.pow(xd,2)+Math.pow(yd,2));
      double xu = xd/dnorm;
      double yu = yd/dnorm;
      int x;
      int y;
      if (dnorm >= avtr.getMainCircleArea().getRadius()){
        x = (int)(Math.floor(10*xu));
        y = (int)(Math.floor(10*yu));
      }else{
        x = (int)(Math.floor((dnorm*1/15)*xu));
        y = (int)(Math.floor((dnorm*1/15)*yu));

      }

      Circle c1 = avtr.getHead();
      c1.setCenterX(-x+avtr.getMainCircleArea().getCenterX());
      c1.setCenterY(-y+avtr.getMainCircleArea().getCenterY());
      avtr.setHead(c1);

      ImageView r2 = avtr.getImgMouthView();
      r2.setX(x/2+avtr.getMainCircleArea().getCenterX()-30);
      r2.setY(y/2+avtr.getMainCircleArea().getCenterY()+10);


      Ellipse e1 = avtr.getEye1();
      e1.setCenterX(x/2+avtr.getMainCircleArea().getCenterX()-25);
      e1.setCenterY(y/2+avtr.getMainCircleArea().getCenterY()-20);


      Ellipse e2 = avtr.getEye2();
      e2.setCenterX(x/2+avtr.getMainCircleArea().getCenterX()+25);
      e2.setCenterY(y/2+avtr.getMainCircleArea().getCenterY()-20);


      if (e2.getCenterY() < avtr.getCenterYMain()-20){
        e1.setRadiusY(25+(e2.getCenterY()-avtr.getCenterYMain()+20));
        e2.setRadiusY(25+(e2.getCenterY()-avtr.getCenterYMain()+20));
      }else{
        e1.setRadiusY(25);
        e2.setRadiusY(25);
      }
      if (r2.getY() > avtr.getCenterYMain()+10){
        r2.setFitHeight(30-(r2.getY()-avtr.getCenterYMain()-10));
      }else{
        r2.setFitHeight(30);
      }
      avtr.setImgMouthView(r2);
      avtr.setEye1(e1);
      avtr.setEye2(e2);
      int[] f = place((int)e.getX(),(int)e.getY(),e1,e2);
      Circle pup1 = avtr.getPupil1();
      Circle pup2 = avtr.getPupil2();
      pup1.setCenterX(f[0]);
      pup1.setCenterY(f[1]);
      pup2.setCenterX(f[2]);
      pup2.setCenterY(f[3]);
      avtr.setPupil1(pup1);
      avtr.setPupil2(pup2);

    });
  }
  private int[] place(int x, int y, Ellipse eye1, Ellipse eye2){

    double xd1 = (x-eye1.getCenterX());
    double yd1 = (y-eye1.getCenterY());
    double xd2 = (x-eye2.getCenterX());
    double yd2 = (y-eye2.getCenterY());
    double dnorm1 = Math.sqrt(Math.pow(xd1,2)+Math.pow(yd1,2));
    double xu1 = xd1/dnorm1;
    double yu1 = yd1/dnorm1;
    double dnorm2 = Math.sqrt(Math.pow(xd2,2)+Math.pow(yd2,2));
    double xu2 = xd2/dnorm2;
    double yu2 = yd2/dnorm2;
    int ex1;
    int ey1;
    int ex2;
    int ey2;
    if (dnorm1 >= 25){
      ex1 = (int)((Math.floor(12.5*xu1))+eye1.getCenterX());
      ey1 = (int)((Math.floor(12.5*yu1))+eye1.getCenterY());
    }else{
      ex1 = (int)((Math.floor((dnorm1*1/2)*xu1))+eye1.getCenterX());
      ey1 = (int)((Math.floor((dnorm1*1/2)*yu1))+eye1.getCenterY());

    }
    if (dnorm2 >= 25){
      ex2 = (int)((Math.floor(12.5*xu2))+eye2.getCenterX());
      ey2 = (int)((Math.floor(12.5*yu2))+eye2.getCenterY());
    }else{
      ex2 = (int)((Math.floor((dnorm2*1/2)*xu2))+eye2.getCenterX());
      ey2 = (int)((Math.floor((dnorm2*1/2)*yu2))+eye2.getCenterY());

    }
    int [] full = {ex1,ey1,ex2,ey2};
    return full;



  }
}
