import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {
    public void createEmployeeTable() {
        String createEmployeeTableSQL = "CREATE TABLE IF NOT EXISTS Employee ("
                + "EmployeeID INT PRIMARY KEY AUTO_INCREMENT, "
                + "firstName VARCHAR(30), "
                + "lastName VARCHAR(40), "
                + "position VARCHAR(40), "
                + "department VARCHAR(40), "
                + "salary DOUBLE)";
        try (Connection connection = DBConnection.connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createEmployeeTableSQL);
            System.out.println("Employee table created successfully.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

