import java.io.*;
import java.net.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * This thread is responsible for reading what the user types and
 * sending it to the server for other clients to recieve.
 * It runs in an infinite loop until the user types 'bye' to quit.
 */

public class ChatOutputThread extends Thread {

	private Socket socket;
	private ChatClient client;
	private PrintWriter writer;
  private TextArea ta;
  private TextField tf;
  Stage s;


	public ChatOutputThread(Socket socket, ChatClient client, TextArea ta, TextField tf, Stage primaryStage, Message userN) {
		this.socket = socket;
		this.client = client;
    this.ta = ta;
    this.tf = tf;
    this.s = primaryStage;
    client.setUserName(userN.getMessage());

		try {
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);
		} catch (IOException ex) {
			System.out.println("Error getting output stream: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void run() {

		//TODO: Change the console from System.console() to instead grab text entered
		//from within the "game" client

    Message mess = new Message();
		//TODO: set userName using a getter method instead of by reading from prompt
		//(user will already have created a name for their avatar)

		//TODO: this should probably be triggered by an event of the user hitting enter
		//while a message is typed in the chatbox

		do {
      tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent ke) {
          if (ke.getCode().equals(KeyCode.ENTER)) {
            mess.setMessage(tf.getText());
            writer.println(mess.getMessage());
            tf.clear();
          }
        }
      });
      tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent ke) {
          if (ke.getCode().equals(KeyCode.ESCAPE)) {
            s.close();
          }
        }
      });
		} while (s.isShowing());	//TODO: Can obviously customize this if desired or change to event (e.g. pressing 'esc' key)

		try {
			socket.close();		//After the chat exchange is finished, close the socket
		} catch (IOException ex) {
			System.out.println("Error writing to server: " + ex.getMessage() + "\n");
		}
	}
}
