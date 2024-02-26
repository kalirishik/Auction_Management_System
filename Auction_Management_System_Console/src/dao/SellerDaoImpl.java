package dao;
import DbConnectivity.Connectivity;
import model.Seller;
import java.sql.*;
public class SellerDaoImpl implements SellerDao{

    public Seller checkSeller(String email, String pwd){
        try{
            Connection con= Connectivity.getConnection();
            String query="SELECT * FROM seller WHERE email =? AND pwd=?";
            try(PreparedStatement stmt=con.prepareStatement(query)){
                stmt.setString(1,email);
                stmt.setString(2,pwd);
                try(ResultSet r=stmt.executeQuery()){
                    if (r.next()){
                        String sellername=r.getString("sellerName");
                        int sellerId=r.getInt("sellerId");
                        return new Seller(sellername,sellerId);
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
    public  boolean  addSeller(String name, String phoneno,String email, String password,String location) {
        try (Connection connection =Connectivity.getConnection()) {
            if (isEmailExists(connection, email))
                return false;
            String query = "INSERT INTO seller (sellerName,phoneno,email, pwd,location) VALUES (?, ?, ? , ? ,? )";
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
        String query = "SELECT COUNT(*) FROM seller WHERE email = ?";
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
    public  void categoryView(){
        System.out.println("To Create the Product to store category_id also  from the below table \n" +
                "|       1001 | Bikes        |\n" +
                "|       1002 | Cars         |\n" +
                "|       1003 | Watches      |\n" +
                "|       1004 | Shoes        |\n" +
                "|       1005 | Jewellery    |\n" +
                "|       1006 | Paintings    |\n" +
                "|       1007 | Electronics   |\n" +
                "|       1008 | Swords       |\n"+
                "-------------------------------------");
    }
    public  void SellerHomeWindow() {
        System.out.println("-------------------------------------------------");
        System.out.println("            Welcome to Seller Home Window : ");
        System.out.println("-------------------------------------------------");
        System.out.println("        Press 1 to Create list of items to sell");
        System.out.println("        Press 2 to Update Item price" );
        System.out.println("        Press 3 to Remove items from the list" );
        System.out.println("        Press 4 to View the sold Item history");
        System.out.println("        Press 5 to View the product items" );
        System.out.println("        Press 6 to LOGOUT AND RETURN TO HOMEPAGE");
    }

}
