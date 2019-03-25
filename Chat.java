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

public class Chat extends Application{
  private TextField tf = new TextField();
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
    ta.setEditable(false);
    tf.setPrefColumnCount(17);
    ta.setPrefColumnCount(14);

    GridPane gridPane = new GridPane();
    gridPane.add(ta, 0, 0);
    gridPane.add(tf, 0, 1);

    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setBottom(gridPane);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    ChatClient client = new ChatClient("Localhost", 8000);
    client.execute(ta, tf, primaryStage);

    tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ESCAPE)) {
          primaryStage.close();
        }
      }
    });
}

  public static void main(String[] args) {
    launch(args);
	}
}
