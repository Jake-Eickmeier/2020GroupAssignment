import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class Chat implements Runnable{
  private TextField tf = new TextField();
  private TextArea ta = new TextArea();
  BorderPane pane;
  Stage stage;
  ChatClient client;
  String username;

  public Chat(BorderPane p, String usern) {
    pane = p;
    username = usern;
    ta.setWrapText(true);
    ta.setEditable(false);
    tf.setPrefColumnCount(17);
    ta.setPrefColumnCount(14);
    ta.setPrefRowCount(60);

    GridPane gridPane = new GridPane();
    gridPane.setStyle("-fx-background-color: transparent;");

    gridPane.add(tf, 0, 1);

    ScrollPane scrPane = new ScrollPane(ta);
    scrPane.setStyle("-fx-background-color: transparent;");

    gridPane.add(scrPane, 0, 0);
    pane.setCenter(gridPane);
  }

  public void run(){
    client = new ChatClient("Localhost", 8000);
    client.execute(ta, tf, username);
  }
  public void killChat(){
    client.endClient();
  }
}
