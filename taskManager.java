import java.sql.*;
import java.util.Scanner;

public class taskManager {

    private final Connection conn;
    private final Scanner sc = new Scanner(System.in);

    public taskManager() throws SQLException{
        this.conn = databaseConnection.getConnection();
    }
    public void addTask() throws SQLException{   // adding task
        System.out.println("Enter Title : ");
        String title = sc.nextLine();
        System.out.println("Enter Description : ");
        String description = sc.nextLine();
        System.out.println("Enter your Priority (Low,Medium,High) : ");
        String priority = sc.nextLine();
        System.out.println("Enter duration (in Days) : ");
        int duration = sc.nextInt();
        sc.nextLine();

        String query = "INSERT INTO tasks (title, description, priority, duration) Values (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,title);
            stmt.setString(2,description);
            stmt.setString(3,priority);
            stmt.setInt(4,duration);
            stmt.executeUpdate();
            System.out.println("Task Added Successfully!");
        }
    }

    public void viewTasks() throws SQLException{
        String query = "SELECT * FROM tasks";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Title: " + rs.getString("title") +
                        " | Priority: " + rs.getString("priority") +
                        " | Status: " + rs.getString("status") +
                        " | Duration: " + rs.getInt("duration") + " days");
            }
        }
    }

    public void updateTask() throws SQLException {
        System.out.print("Enter tasks ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new title: ");
        String title = sc.nextLine();
        System.out.print("Enter new description: ");
        String description = sc.nextLine();
        System.out.print("Enter new priority (Low, Medium, High): ");
        String priority = sc.nextLine();

        String query = "UPDATE tasks SET title = ?, description = ?, priority = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, priority);
            stmt.setInt(4, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Task updated successfully!");
            } else {
                System.out.println("Task not found.");
            }
        }
    }

    public void markTaskCompleted() throws SQLException {
        System.out.print("Enter task ID to mark as completed: ");
        int id = sc.nextInt();

        String query = "UPDATE tasks SET status = 'Completed' WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Task marked as completed!");
        }
    }

    public void deleteTask() throws SQLException {
        System.out.print("Enter task ID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Task deleted successfully!");
        }
    }

    public void addNoteToTask() throws SQLException {
        System.out.print("Enter task ID to add note: ");
        int taskId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter note: ");
        String note = sc.nextLine();

        String query = "INSERT INTO notes (task_id, note) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, taskId);
            stmt.setString(2, note);
            stmt.executeUpdate();
            System.out.println("Note added successfully!");
        }
    }

    public void viewNotes() throws SQLException {
        System.out.print("Enter task ID to view notes: ");
        int taskId = sc.nextInt();

        String query = "SELECT note FROM notes WHERE task_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, taskId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Note: " + rs.getString("note"));
            }
        }
    }
}
