import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class AvatarAnimate implements Runnable{
  Avatar avtr;
  Pane pane;
  Pane getmousepane;

  public AvatarAnimate(Avatar avt, Pane p, Pane x) {
    avtr = avt;
    pane = p;
    getmousepane = x;
  }

  @Override
  public void run() {
    Platform.runLater(() -> {
      //add all avatar objects to pane
      pane.getChildren().add(avtr.getMainCircleArea());
      pane.getChildren().add(avtr.getHead());
      pane.getChildren().add(avtr.getImgMouthView());
      pane.getChildren().add(avtr.getEye1());
      pane.getChildren().add(avtr.getEye2());
      pane.getChildren().add(avtr.getImgEyeView());
      pane.getChildren().add(avtr.getPupil1());
      pane.getChildren().add(avtr.getPupil2());

      //action on mouse moved on pane
      getmousepane.setOnMouseMoved(e -> {
        //get mouse co-ordinates in relation to circle center
        double xd = (e.getX()-avtr.getMainCircleArea().getCenterX());
        double yd = (e.getY()-avtr.getMainCircleArea().getCenterY());
        //get euclidean vector norm from co-ordinates
        double dnorm = Math.sqrt(Math.pow(xd, 2)+Math.pow(yd, 2));
        //create unit vector
        double xu = xd/dnorm, yu = yd/dnorm;
        //main x and y variables created
        int x, y;

        if (dnorm >= avtr.getMainCircleArea().getRadius()) {
          //if mouse outside main circle make general position to max
          x = (int)(Math.floor(10*xu));
          y = (int)(Math.floor(10*yu));
        } else {
          //if mouse inside main circle, make relatie position towards mouse
          x = (int)(Math.floor((dnorm*1/15)*xu));
          y = (int)(Math.floor((dnorm*1/15)*yu));
        }

        //move head inverse to mouse position
        Circle head = avtr.getHead();
        head.setCenterX(-x+avtr.getMainCircleArea().getCenterX());
        head.setCenterY(-y+avtr.getMainCircleArea().getCenterY());
        avtr.setHead(head);

        ImageView eyeimg = avtr.getImgEyeView();
        eyeimg.setX(x/2+avtr.getMainCircleArea().getCenterX()-50);
        eyeimg.setY(y/2+avtr.getMainCircleArea().getCenterY()-40);

        ImageView mouthimg = avtr.getImgMouthView();
        mouthimg.setX(x/2+avtr.getMainCircleArea().getCenterX()-30);
        mouthimg.setY(y/2+avtr.getMainCircleArea().getCenterY()+10);

        Ellipse e1 = avtr.getEye1();
        e1.setCenterX(x/2+avtr.getMainCircleArea().getCenterX()-25);
        e1.setCenterY(y/2+avtr.getMainCircleArea().getCenterY()-20);
        avtr.setEye1(e1);

        Ellipse e2 = avtr.getEye2();
        e2.setCenterX(x/2+avtr.getMainCircleArea().getCenterX()+25);
        e2.setCenterY(y/2+avtr.getMainCircleArea().getCenterY()-20);
        avtr.setEye2(e2);

        if (eyeimg.getY() < avtr.getCenterYMain()-40) {
          eyeimg.setFitHeight(40+(eyeimg.getY()-avtr.getCenterYMain()+40));
        } else {
          eyeimg.setFitHeight(40);
        }

        if (mouthimg.getY() > avtr.getCenterYMain()+10) {
          mouthimg.setFitHeight(30-(mouthimg.getY()-avtr.getCenterYMain()-10));
        } else {
          mouthimg.setFitHeight(30);
        }

        avtr.setImgMouthView(mouthimg);

        int[] f = place((int)e.getX(),(int)e.getY(), e1, e2);
        Circle pup1 = avtr.getPupil1(), pup2 = avtr.getPupil2();
        pup1.setCenterX(f[0]);
        pup1.setCenterY(f[1]);
        pup2.setCenterX(f[2]);
        pup2.setCenterY(f[3]);
        avtr.setPupil1(pup1);
        avtr.setPupil2(pup2);
      });
    });
  }

  public void setGetMousePane(Pane mpane) {
    getmousepane = mpane;
  }

  private int[] place(int x, int y, Ellipse eye1, Ellipse eye2) {
    double xd1 = (x-eye1.getCenterX()), yd1 = (y-eye1.getCenterY());
    double xd2 = (x-eye2.getCenterX()), yd2 = (y-eye2.getCenterY());
    double dnorm1 = Math.sqrt(Math.pow(xd1, 2)+Math.pow(yd1, 2));
    double dnorm2 = Math.sqrt(Math.pow(xd2, 2)+Math.pow(yd2, 2));
    double xu1 = xd1/dnorm1, yu1 = yd1/dnorm1;
    double xu2 = xd2/dnorm2, yu2 = yd2/dnorm2;
    int ex1, ey1, ex2, ey2;

    if (dnorm1 >= 25) {
      ex1 = (int)((Math.floor(12.5*xu1))+eye1.getCenterX());
      ey1 = (int)((Math.floor(12.5*yu1))+eye1.getCenterY());
    } else {
      ex1 = (int)((Math.floor((dnorm1*1/2)*xu1))+eye1.getCenterX());
      ey1 = (int)((Math.floor((dnorm1*1/2)*yu1))+eye1.getCenterY());
    }

    if (dnorm2 >= 25) {
      ex2 = (int)((Math.floor(12.5*xu2))+eye2.getCenterX());
      ey2 = (int)((Math.floor(12.5*yu2))+eye2.getCenterY());
    } else {
      ex2 = (int)((Math.floor((dnorm2*1/2)*xu2))+eye2.getCenterX());
      ey2 = (int)((Math.floor((dnorm2*1/2)*yu2))+eye2.getCenterY());
    }

    int [] full = {ex1, ey1, ex2, ey2};
    return full;
  }
}
