package dao;
import DbConnectivity.Connectivity;
import java.sql.*;

public class AdminDaoImpl implements AdminDao{
    Connection con=Connectivity.getConnection();
    public  boolean checkAdmin(String email,String pwd){
        try{
            String query="SELECT * FROM AdminPanel WHERE email =? AND pwd=?";
            try(PreparedStatement stmt=con.prepareStatement(query)){
                stmt.setString(1,email);
                stmt.setString(2,pwd);
                try(ResultSet r=stmt.executeQuery()){
                    if(r.next())
                        return true;
                    else
                        return false;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public  void AdminHomeWindow() {
        System.out.println("              Welcome to Admin Home Window        ");
        System.out.println("--------------------------------------------------");
        System.out.println("        Press 1 to View the registered buyer list");
        System.out.println("        Press 2 to View the registered Seller list" );
        System.out.println("        Press 3 to View the daily selling report");
        System.out.println("        Press 4 to View the Product items" );
        System.out.println("        Press 5 to Solve the dispute report");
        System.out.println("        Press 6 to LOGOUT AND RETURN TO HOMEPAGE");
    }
}
