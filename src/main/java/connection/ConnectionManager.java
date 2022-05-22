package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String port = "5432";
    private static String password = "pgadmin";
    private static String server = "localhost";
    private static String username="postgres";
    private static String dbname = "school";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://" + server + ":" + port + "/" + dbname;
        return DriverManager.getConnection(url, username, password);
    }
}
