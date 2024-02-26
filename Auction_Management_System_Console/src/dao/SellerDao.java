package dao;
import exception.SellerException;
import model.Seller;
public interface SellerDao {
    public Seller checkSeller(String e2, String p2) throws SellerException;
    public  boolean  addSeller(String name, String phoneno,String email, String password,String location) throws SellerException;
    public  void SellerHomeWindow() throws SellerException;
    public  void categoryView() throws SellerException;

}
