import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This thread handles connection for each connected client, so the server
 * can handle multiple clients at the same time.
 */

public class ChatUserThread extends Thread {
	private Socket socket;
	private ChatServer server;
	private PrintWriter writer;

	public ChatUserThread(Socket socket, ChatServer server) {
		this.socket = socket;
		this.server = server;
	}

	public void run() {
		try {
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);

			printUsers();

			String userName = reader.readLine();
			server.addUserName(userName);

			String serverMessage = "New user connected: " + userName;
			server.broadcast(serverMessage, this);

			String clientMessage;

			do {
				clientMessage = reader.readLine();
				serverMessage = "[" + userName + "]: " + clientMessage;
				server.broadcast(serverMessage);

			} while (!clientMessage.equals("bye"));		//TODO: once again, can customize this to 'esc' key etc if desired

			server.removeUser(userName, this);
			socket.close();

			serverMessage = userName + " has left the chat.";
			server.broadcast(serverMessage, this);

		} catch (IOException ex) {
			System.out.println("Error in ChatUserThread: " + ex.getMessage());
			ex.printStackTrace();
		}
	}


	//Sends a list of online users to the newly connected user.
	void printUsers() {
		if (server.hasUsers()) {
			writer.println("Connected users: " + server.getUserNames());
		} else {
			writer.println("No other users connected");
		}
	}

	//Sends a message to the client.
	void sendMessage(String message) {
		writer.println(message);
	}
}
