import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/EMS";
    private static final String username = "root";
    private static final String password = "";
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL,username,password);
    }
}
