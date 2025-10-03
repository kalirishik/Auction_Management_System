package DbConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectivity {
    private static Connection con;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/auction_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "kali@12345";

    public static Connection getConnection() {
        try {
            // if connection is null or closed, create a new one
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
