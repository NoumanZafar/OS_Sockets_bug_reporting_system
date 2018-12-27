package gmit.ie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Menu {
	/**
	 * Attributes to create the menu.
	 */
	private Scanner console;
	private int option;
	private boolean exit = true;
	private EmployeeDetails empDetails;
	private AddBug addBug;
	private BugAssignment bugAsi;
	private ViewBugs viewBugs;
	private UpdateBug updateBug;

	/**
	 * Instantiate all the objects which are involved in the menu.
	 */
	public Menu() {
		super();
		console = new Scanner(System.in);
		empDetails = new EmployeeDetails();
		addBug = new AddBug();
		bugAsi = new BugAssignment();
		viewBugs = new ViewBugs();
		updateBug = new UpdateBug();
	}

	/**
	 * Provide the menu and ask for the user choice.
	 * 
	 * @param in
	 * @param out
	 * @param message
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void selectOption(ObjectInputStream in, ObjectOutputStream out, String message)
			throws ClassNotFoundException, IOException {
		do {
			message = (String) in.readObject();
			System.out.println(message);
			message = console.next();
			out.writeObject(message);
			out.flush();
			option = Integer.parseInt(message);
			if (option == 1) {
				System.out.println("Get all Engineers");
				empDetails.getEmployees(in);
			} else if (option == 2) {
				System.out.println("Add a bug");
				addBug.addBug(in, out, message);
			} else if (option == 3) {
				System.out.println("Assign a bug to Engineer.");
				bugAsi.assignToEngineer(in, out, message);
			} else if (option == 4) {
				System.out.println("All bugs (Not Assigned)");
				viewBugs.getAllBugs(in);
			} else if (option == 5) {
				System.out.println("All bugs");
				viewBugs.getAllBugs(in);
			} else if (option == 6) {
				System.out.println("Update a bug");
				updateBug.update(in, out, message);
			} else if (option == 7) {
				this.exit = false;
			} else {
				System.out.println("WRONG INPUT ------> TRY AGAIN.");
			}
		} while (exit);
	}
}
