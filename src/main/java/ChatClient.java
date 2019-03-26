import java.net.*;
import java.io.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

/**
 * This is the chat client program.
 * Type 'bye' to terminte the program.
 *
 * TO RUN: "java ChatClient localhost <port>"
 */

public class ChatClient {
	private String userName;
	private String hostName;
	private int port;
	private Socket socket;

	public ChatClient(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;
	}

	public void execute(TextArea ta, TextField tf, String username) {
		try {
			socket = new Socket(hostName, port);

			ta.appendText("Connected to the chat server");

			this.setUserName(username);

			new ChatInputThread(socket, this, ta, tf).start();
			new ChatOutputThread(socket, this, ta, tf).start();

		} catch (UnknownHostException ex) {
			ta.appendText("Server not found: " + ex.getMessage() + "\n");
		} catch (IOException ex) {
			ta.appendText("I/O Error: " + ex.getMessage() + "\n");
		}
	}

	public void endClient() {
		try{
			socket.close();
		} catch (Exception ex) {

		}
	}

	void setUserName(String userName) {this.userName = userName;}
	String getUserName() {return this.userName;}
}
