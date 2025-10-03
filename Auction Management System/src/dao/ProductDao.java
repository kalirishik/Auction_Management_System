package dao;

public interface ProductDao {
    //admin
    public  void viewBuyerList() throws Exception;
    public  void viewSellerList() throws Exception;
    public  void dailySellingReport() throws Exception;
    public  void disputeResolve() throws Exception;
    //seller
    public  void createProductList(int id) throws Exception;
    public  void updateProductlist() throws Exception;
    public  void deleteProductitem() throws Exception;
    public  void soldItemHistory(int id) throws  Exception;
    // seller or admin
    public  void viewProduct(int id) throws Exception;

    //buyer
    public  void searchItemCategory() throws Exception;
    public  void buyitem(int id) throws Exception;
    public  void searchBuyerItembyCategory() throws Exception;

}
