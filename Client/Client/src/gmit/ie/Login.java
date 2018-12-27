package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Login {
	private Scanner console;
	private boolean found = false;

	public Login() {
		super();
		console = new Scanner(System.in);
	}

	/**
	 * Get the instructions from server and send back the information to login
	 * 
	 * @param in
	 * @param out
	 * @param message
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean login(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		System.out.println("Enter the following Details.");
		receiveAndSendMessage(in, out, message);
		receiveAndSendMessage(in, out, message);
		found = in.readBoolean();
		/**
		 * If details are not found in the server side. then provide the details again.
		 */
		if (found == false) {
			System.out.println("Wrong ID or Email. Try Again.");
		}
		return found;
	}

	/**
	 * Receive and send the message to server.
	 * 
	 * @param in      ObjectInputStream
	 * @param out     ObjectOutputStream
	 * @param message Message for the server
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void receiveAndSendMessage(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		message = (String) in.readObject();
		System.out.println(message);
		message = console.nextLine();
		sendMessage(message, out);
	}

	public void sendMessage(String message, ObjectOutputStream out) {
		try {
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
