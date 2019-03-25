import java.io.*;
import java.net.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * This thread is responsible for reading server's input (text sent from other
 * users)and printing it to the local chat console.
 * It runs in an infinite loop until the client disconnects from the server.
 */

public class ChatInputThread extends Thread {

	private Socket socket;
	private ChatClient client;
	private BufferedReader reader;
  private TextArea ta;
  private TextField tf;

	public ChatInputThread(Socket socket, ChatClient client, TextArea ta, TextField tf) {
		this.socket = socket;
		this.client = client;
    this.ta = ta;
    this.tf = tf;

		try {
			InputStream input = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input));
		} catch (IOException ex) {
			System.out.println("Error getting input stream: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

  public void run() {
		while (true) {
			try {
				//TODO: We will need to change this to display to game window instead of console

				String response = reader.readLine();
				ta.appendText("\n" + response);

			} catch (IOException ex) {
				System.out.println("Error reading from server: " + ex.getMessage());
				ex.printStackTrace();
				break;
			}
		}
	}
}
