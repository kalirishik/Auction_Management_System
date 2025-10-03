package dao;

import exception.ByerException;
import model.Buyer;

public interface BuyerDao {

//    public List<SoldItemsDTO> searchItemByCategory(int categoryId) throws ByerException;
//
//    public String BuyItem(int buyerId, LocalDate date,String productName) throws ByerException;
//
//    public List<SearchBuyerDTO> ViewAllBuyersDetails(String categoryName) throws ByerException;

    public Buyer checkBuyer(String email, String pwd) throws ByerException;
    public  boolean  addBuyer(String name, String phoneno,String email, String password,String location) throws ByerException;
    public  void BuyerHomeWindow() throws Exception;


}
