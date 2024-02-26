package DbConnectivity;
import java.sql.*;
public class Connectivity {
    private static Connection con;
    static{
        try{
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/auction_management_system",
                    "root","Kali@742003");
        }
        catch(Exception e){
                e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        try {
            if (con.isClosed()) {
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/auction_management_system",
                        "root",
                        "Kali@742003");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
