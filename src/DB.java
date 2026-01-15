import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String URL =
            "jdbc:postgresql://localhost:5432/SimpleDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "asan1243658790";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
