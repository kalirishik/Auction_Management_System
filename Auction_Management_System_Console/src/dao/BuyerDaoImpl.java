package dao;
import DbConnectivity.Connectivity;
import exception.ByerException;
import model.SearchBuyerDTO;
import model.SoldItemsDTO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Buyer;
public class BuyerDaoImpl implements BuyerDao {

    public Buyer checkBuyer(String email, String pwd){
        try{
            Connection con= Connectivity.getConnection();
            String query="SELECT * FROM buyer WHERE email =? AND pwd=?";
            try(PreparedStatement stmt=con.prepareStatement(query)){
                stmt.setString(1,email);
                stmt.setString(2,pwd);
                try(ResultSet r=stmt.executeQuery()){
                    if (r.next()){
                        String bname= r.getString("buyerName");
                        int bid=r.getInt("buyerId");
                        return new Buyer(bname,bid);
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public  boolean  addBuyer(String name, String phoneno,String email, String password,String location) {
        try (Connection connection =Connectivity.getConnection()) {
            if (isEmailExists(connection, email))
                return false;
            String query = "INSERT INTO buyer (buyerName,phoneno,email, pwd,location) VALUES (?, ?, ?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, phoneno);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, location);
                preparedStatement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isEmailExists(Connection connection, String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM buyer WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
    public  void BuyerHomeWindow() {
        System.out.println("Welcome to Buyer Home Window : ");
        System.out.println("Press 1 to Search and view Items by category");
        System.out.println("Press 2 to Selects Items to buy");
        System.out.println("Press 3 to Select and view all the buyers and also their Items category wise");
        System.out.println("Press 4 to LOGOUT AND RETURN TO HOMEPAGE");
    }
}
