package dao;

import exception.ByerException;
import model.Buyer;
import model.SearchBuyerDTO;
import model.SoldItemsDTO;

import java.time.LocalDate;
import java.util.List;

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
