import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This is the chat server program.
 * Press Ctrl + C to terminate the program.
 *
 * TO RUN: "java ChatServer <port>"
 */

public class ChatServer {
	private int port;
	private Set<String> userNames = new HashSet<>();
	private Set<ChatUserThread> chatUserThreads = new HashSet<>();

	public ChatServer(int port) {
		this.port = port;
	}

	public void execute() {
		try (ServerSocket serverSocket = new ServerSocket(port)) {

			System.out.println("Chat Server is listening on port " + port);

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New user connected");

				ChatUserThread newUser = new ChatUserThread(socket, this);
				chatUserThreads.add(newUser);
				newUser.start();
			}
		} catch (IOException ex) {
			System.out.println("Error in the server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	//Delivers a message from one user to others (broadcasting)
	void broadcast(String message, ChatUserThread excludeUser) {
		for (ChatUserThread aUser : chatUserThreads) {
			if (aUser != excludeUser) {
				aUser.sendMessage(message);
			}
		}
	}

  void broadcast(String message) {
    for (ChatUserThread aUser : chatUserThreads) {
      aUser.sendMessage(message);
    }
  }

	//Stores username of the newly connected client.
	void addUserName(String userName) {
		userNames.add(userName);
	}

	//When a client is disconneted, removes the associated username and ChatUserThread
	void removeUser(String userName, ChatUserThread aUser) {
		boolean removed = userNames.remove(userName);
		if (removed) {
			chatUserThreads.remove(aUser);
			System.out.println("The user " + userName + " has left the chat");
		}
	}

	Set<String> getUserNames() {
		return this.userNames;
	}

	//Returns true if there are other users connected (not count the currently connected user)
	boolean hasUsers() {
		return !this.userNames.isEmpty();
	}
}
