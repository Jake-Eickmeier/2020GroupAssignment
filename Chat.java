import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Chat implements Runnable{
  private TextField tf = new TextField();
  private TextArea ta = new TextArea();
  BorderPane pane;
  Stage stage;


  public Chat(BorderPane p,Stage s) {
    pane = p;
    ta.setWrapText(true);
    ta.setEditable(false);
    tf.setPrefColumnCount(17);
    ta.setPrefColumnCount(14);

    GridPane gridPane = new GridPane();
    gridPane.setStyle("-fx-background-color: transparent;");
    gridPane.add(ta, 0, 0);
    gridPane.add(tf, 0, 1);


    ScrollPane scrPane = new ScrollPane(gridPane);
    scrPane.setStyle("-fx-background-color: transparent;");

    pane.setCenter(scrPane);



    /*
    tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ESCAPE)) {
          primaryStage.close();
        }
      }
    });
    */
  }

  public void run(){
    ChatClient client = new ChatClient("Localhost", 8000);
    client.execute(ta, tf);
  }

}
