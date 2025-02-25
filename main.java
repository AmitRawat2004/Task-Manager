import java.sql.SQLException;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        try {
            taskManager taskManager = new taskManager();
            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nTask Manager");
                System.out.println("1. Add Task");
                System.out.println("2. View Tasks");
                System.out.println("3. Update Task");
                System.out.println("4. Mark Task as Completed");
                System.out.println("5. Delete Task");
                System.out.println("6. Add Note to Task");
                System.out.println("7. View Notes");
                System.out.println("8. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> taskManager.addTask();
                    case 2 -> taskManager.viewTasks();
                    case 3 -> taskManager.updateTask();
                    case 4 -> taskManager.markTaskCompleted();
                    case 5 -> taskManager.deleteTask();
                    case 6 -> taskManager.addNoteToTask();
                    case 7 -> taskManager.viewNotes();
                    case 8 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } while (choice != 8);
            sc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
