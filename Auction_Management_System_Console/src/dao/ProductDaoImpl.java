package dao;
import exception.AdminException;
import exception.ByerException;
import exception.SellerException;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.ProductDTO;

public class ProductDaoImpl implements  ProductDao{
    static ProductDTO p=new ProductDTO();
    //admin
    public  void viewBuyerList(){
        try {
            List<BuyerDTO> list=p.ViewBuyers();
            System.out.println("---------------------Table of all the Buyers---------------------------");

            System.out.println("---------------------------------------------------------------------------------");

            System.out.printf("%7s %12s %10s %25s", "BuyerId", "BuyerName","Email", "Location" );
            System.out.println();
            System.out.print("---------------------------------------------------------------------------------");

            System.out.println();
            list.forEach(li->{
                System.out.format("%2s %14s %20s %13s",li.getBuyerId(),li.getBuyerName(),li.getEmail(),li.getLocation());
                System.out.println();

            });
            System.out.println("---------------------------------------------------------------------------------");

        } catch (AdminException e) {
            System.out.println( e.getMessage());
        }
    }
    public  void viewSellerList(){
        try {
            List<SellerDTO> list=p.ViewSellers();
            System.out.println("--------------------- Table of all the Sellers ---------------------------");

            System.out.println("---------------------------------------------------------------------------------");

            System.out.printf("%7s %12s %10s %25s","SellerId", "SellerName","Email", "Location");
            System.out.println();
            System.out.print("---------------------------------------------------------------------------------");

            System.out.println();
            list.forEach(li->{
                System.out.format("%2s %14s %20s %13s",li.getSellerId(),li.getSellerName(),li.getEmail(),li.getLocation());
                System.out.println();

            });
            System.out.println("---------------------------------------------------------------------------------");

        } catch (AdminException e) {
            System.out.println(e.getMessage());
        }
    }
    public  void dailySellingReport(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter date you want to find list of sold items \n Example : (YYYY-MM-DD)");
        String date=sc.next();
        try {
            List<SearchBuyerDTO> list= p.DailySellingReport(date);
            System.out.println("---------------------Daily Selling Report---------------------------");

            System.out.println("---------------------------------------------------------------------------------------------");

            System.out.printf("%7s %13s %12s %15s %10s %13s %10s","Id", "BuyerName","email","ProductName","CategoryName","SellerName","Price");
            System.out.println();
            System.out.print("----------------------------------------------------------------------------------------------");

            System.out.println();
            list.forEach(li->{
                System.out.format("%2s %10s %18s %12s %12s %11s %12s",li.getBuyerId(),li.getBuyerName(),li.getEmail(),li.getProductName(),li.getCategoryName(),li.getSellerName(),li.getPrice());
                System.out.println();

            });
            System.out.println("-------------------------------------------------------------------------------------------");

        } catch (AdminException e) {
            System.out.println( e.getMessage());
        }
    }


    public  void disputeResolve(){
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter productId of product you want to solve dispute of ");
        int productId=sc.nextInt();
        System.out.println("Enter the correct category Id from the below table- \n"+
                "|       1001 | Bikes        |\n" +
                "|       1002 | Cars         |\n" +
                "|       1003 | Watches      |\n" +
                "|       1004 | Shoes        |\n" +
                "|       1005 | Jewellery    |\n" +
                "|       1006 | Paintings    |\n" +
                "|       1007 | Electronics     |\n" +
                "|       1008 | Swords      |\n"+
                "------------------------------------------");
        int categoryId=sc.nextInt();

        String result;
        try {
            result= p.SolveDispute(categoryId, productId);
            System.out.println( result );
        } catch (AdminException e) {
            result=e.getMessage();
            System.out.println( result);
        }

    }
    //seller
    public  void createProductList(int id){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("------ Enter the number of products you want to insert in auction list ---------");
            int N = sc.nextInt();
            List<Products> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                System.out.println("Enter Details of Product-" + (i + 1));
                Products products = new Products();
                products.setSellerId(id);
                System.out.print("Enter Product Name-");
                String name = sc.next();
                products.setProductName(name);
                System.out.print("Enter Category ID-");
                int cid = sc.nextInt();
                products.setCategoryId(cid);
                System.out.print("Enter Quantity-");
                int quantity = sc.nextInt();
                products.setQuantity(quantity);
                System.out.print("Enter Price-");
                int price = sc.nextInt();
                products.setPrice(price);
                list.add(products);
                System.out.println("---------------------------------------------------------------------------------");
            }
            String result;
            try {
                result = p.CreateListofProductstoSell(list);
                System.out.println( result );
            } catch (SellerException e) {
                result = e.getMessage();
                System.out.println(result);
            }
        }catch (InputMismatchException i){
            System.out.println("Exception : Invalid Input Data Type" );
        }
    }
    public  void updateProductlist(){
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter productId of product you want to update");
        int productId=sc.nextInt();
        System.out.println("Enter the updated Price");
        int price=sc.nextInt();
        String result;
        try {
            result=p.UpdateProductPrice(productId,price);
            System.out.println(result );
        } catch (SellerException e) {
            result=e.getMessage();
            System.out.println(result );
        }

    }
    public  void deleteProductitem(){
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter productId of product you want to delete");
        int productId=sc.nextInt();

        String result;
        try {
            result= p.DeleteProductItems(productId);
            System.out.println( result);
        } catch (SellerException e) {
            result=e.getMessage();
            System.out.println( result );
        }

    }
    public  void soldItemHistory(int id){
        try {
            List<SoldItemsDTO> list=p.SoldItemHistory(id);
            System.out.println("--------------------- Table of all the Sold Items ---------------------------");

            System.out.println("---------------------------------------------------------------------------------");

            System.out.printf("%7s %13s %15s %15s %10s ","Id", "ProductName","SellerName","CategoryName","Price");
            System.out.println();
            System.out.print("---------------------------------------------------------------------------------");

            System.out.println();
            list.forEach(li->{
                System.out.format("%2s %12s %12s %16s %13s",li.getProductId(),li.getProductName(),li.getSellerName(),li.getCategoryName(),li.getPrice());
                System.out.println();

            });
            System.out.println("---------------------------------------------------------------------------------");

        } catch (SellerException e) {
            System.out.println( e.getMessage() );
        }
    }
    //admin or buyer
    public  void viewProduct(int id) throws Exception{
        try {
            List<ViewProductDTO> list=p.ViewProducts(id);
            System.out.println("--------------------- Table of all the Products ---------------------------");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("%7s %15s %15s %15s %15s","ProductId", "ProductName","category_id", "quantity" ,"price");
            System.out.println();
            System.out.print("---------------------------------------------------------------------------------");
            System.out.println();
            list.forEach(li->{
                System.out.format("%2s %20s %15s %15s %15s",li.getProductId(),li.getProductName(),li.getCategory_id(),li.getQuantity(),li.getPrice());
                System.out.println();

            });
            System.out.println("---------------------------------------------------------------------------------");

        } catch (AdminException e) {
            System.out.println(e.getMessage());
        }
    }
//buyer
    public  void searchItemCategory(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Category Id from the below table to want to find list of products -\n" +
                "|       1001 | Bikes        |\n" +
                "|       1002 | Cars         |\n" +
                "|       1003 | Watches      |\n" +
                "|       1004 | Shoes        |\n" +
                "|       1005 | Jewellery    |\n" +
                "|       1006 | Paintings    |\n" +
                "|       1007 | Electronics   |\n" +
                "|       1008 | Swords      |\n"+
                "------------------------------------------");
        int category =sc.nextInt();
        try {
            List<SoldItemsDTO> list= p.searchItemByCategory(category);
            System.out.println("--------------------- Search Product By Category ---------------------------");

            System.out.println("---------------------------------------------------------------------------------");

            System.out.printf("%7s %13s %15s %15s %10s %15s","Id", "ProductName","SellerName","CategoryName","Price", "Status");
            System.out.println();
            System.out.print("---------------------------------------------------------------------------------");

            System.out.println();
            list.forEach(li->{
                System.out.format("%2s %12s %12s %16s %13s %14s",li.getProductId(),li.getProductName(),li.getSellerName(),li.getCategoryName(),li.getPrice(),li.getStatus());
                System.out.println();

            });
            System.out.println("---------------------------------------------------------------------------------");

        } catch (ByerException e) {
            System.out.println(e.getMessage());
        }
    }
    public  void buyitem(int id){
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter productName you want to buy");
        String productName=sc.next();
        LocalDate date= LocalDate.now();
        String result;
        try {
            result= p.BuyItem(id,date,productName);
            System.out.println( result);
        } catch (ByerException e) {
            result=e.getMessage();
            System.out.println( result);
        }
    }
    public  void searchBuyerItembyCategory(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the category Id to want to find list of bought items by buyers -\n" +
                "|       1001 | Bikes        |\n" +
                "|       1002 | Cars         |\n" +
                "|       1003 | Watches      |\n" +
                "|       1004 | Shoes        |\n" +
                "|       1005 | Jewellery    |\n" +
                "|       1006 | Paintings    |\n" +
                "|       1007 | Electronics    |\n" +
                "|       1008 | Swords       |\n"+
                "------------------------------------------");
        int category =sc.nextInt();
        try {
            List<SearchBuyerDTO> list= p.ViewAllBuyersDetails(category);
            System.out.println("---------------------Search Buyer By Category Name---------------------------");

            System.out.println("---------------------------------------------------------------------------------------------");

            System.out.printf("%7s %13s %12s %15s %10s %13s %10s","Id", "BuyerName","email","ProductName","CategoryName","SellerName","Price");
            System.out.println();
            System.out.print("----------------------------------------------------------------------------------------------");

            System.out.println();
            list.forEach(li->{
                System.out.format("%2s %15s %22s %12s %12s %12s %12s",li.getBuyerId(),li.getBuyerName(),li.getEmail(),li.getProductName(),li.getCategoryName(),li.getSellerName(),li.getPrice());
                System.out.println();

            });
            System.out.println("-------------------------------------------------------------------------------------------");

        } catch (ByerException e) {
            System.out.println(e.getMessage());
        }
    }
}
