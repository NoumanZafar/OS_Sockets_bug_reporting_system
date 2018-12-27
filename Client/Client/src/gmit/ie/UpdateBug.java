package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class UpdateBug {
	private boolean exit = true;
	private Scanner console;
	private int option;

	public UpdateBug() {
		super();
		console = new Scanner(System.in);
	}

	/**
	 * Provide the new details to update the existing bug. and provide options to
	 * the user e.g change the status or change the assigned Engineer.
	 * 
	 * @param in
	 * @param out
	 * @param message
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void update(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		do {
			message = (String) in.readObject();
			System.out.println(message);
			message = console.nextLine();
			sendMessage(message, out);
			option = Integer.parseInt(message);
			if (option == 1) {
				updateStatus(in, out, message);
			} else if (option == 2) {
				appendDesc(in, out, message);
			} else if (option == 3) {
				System.out.println("Change Assigned Engineer");
				new BugAssignment().assignToEngineer(in, out, message);
			} else if (option == 4) {
				this.exit = false;
			} else {
				System.out.println("Error -------> Wrong Input. Try Again.");
			}
		} while (exit);
	}

	/**
	 * Append to the existing description of the bug. If provided Bug id is not
	 * valid then provide again.
	 * 
	 * @param in
	 * @param out
	 * @param message
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void appendDesc(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		receiveAndSendMessage(in, out, message);
		for (int i = 0; i < 1; i++) {
			receiveAndSendMessage(in, out, message);
			boolean found = in.readBoolean();
			if (found == false) {
				System.out.println("There is no bug of this ID. Try again");
				i--;
			}
		}
	}

	/**
	 * Update the status of the existing bug. either set to open, close or assigned
	 * if status is set to assigned then provide the engineers id along with it.
	 */
	public void updateStatus(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		System.out.println("Update Status");
		for (int i = 0; i < 1; i++) {
			message = (String) in.readObject();
			System.out.println(message);
			message = console.nextLine();
			sendMessage(message, out);
			int selection = Integer.parseInt(message);
			if (selection == 2) {
				new BugAssignment().assignToEngineer(in, out, message);
			} else if (!(selection == 1 || selection == 2 || selection == 3)) {
				System.out.println("Error -------> Wrong Input. Try again.");
				i--;
			}

			if (selection == 1 || selection == 3) {
				for (int j = 0; j < 1; j++) {
					receiveAndSendMessage(in, out, message);
					boolean found = in.readBoolean();
					if (found == false) {
						System.out.println("There is no bug of this ID. Try again");
						j--;
					}
				}
			}
		}
	}

	/**
	 * Send and receive messages to/from server.
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
