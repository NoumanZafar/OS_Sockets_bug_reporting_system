package gmit.ie;

import java.io.*;
import java.util.Scanner;

public class EmployeeRegistration {
	/**
	 * exit value controls the loop.
	 */
	private boolean exit = true;
	private Scanner console;

	public EmployeeRegistration() {
		super();
		console = new Scanner(System.in);
	}

	/**
	 * Provide the details of the user to register.
	 * 
	 * @param in
	 * @param out
	 * @param message
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void registration(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		do {
			/**
			 * Name of the user
			 */
			receiveAndSendMessage(in, out, message);

			/**
			 * Id has to be unique.
			 */
			for (int i = 0; i < 1; i++) {
				receiveAndSendMessage(in, out, message);
				boolean found = in.readBoolean();
				if (found) {
					System.out.println("Error ------> ID Already Exists. Try Again.");
					i--;
				}
			}

			/**
			 * Checks whether email is unique or not if not ask user to input again.
			 */
			for (int i = 0; i < 1; i++) {
				receiveAndSendMessage(in, out, message);
				boolean found = in.readBoolean();
				if (found) {
					System.out.println("Error ------> Email Already Exists. Try Again.");
					i--;
				}
			}

			/**
			 * Department attribute.
			 */
			receiveAndSendMessage(in, out, message);

			/**
			 * Stop the loop
			 */
			this.exit = false;
		} while (exit);
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
