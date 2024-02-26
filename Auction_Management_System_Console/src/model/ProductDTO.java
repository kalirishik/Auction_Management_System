package model;
import DbConnectivity.Connectivity;

import exception.AdminException;
import exception.ByerException;
import exception.SellerException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDTO {
    Scanner sc=new Scanner(System.in);
    public List<BuyerDTO> ViewBuyers() throws AdminException {
        List<BuyerDTO> list=new ArrayList<>();

        try(Connection conn= Connectivity.getConnection()) {
            PreparedStatement ps=conn.prepareStatement("select * from buyer");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                BuyerDTO buyerDTO=new BuyerDTO();
                buyerDTO.setBuyerId(rs.getInt("buyerId"));
                buyerDTO.setBuyerName(rs.getString("buyerName"));
                buyerDTO.setPhoneno(rs.getString("phoneno"));
                buyerDTO.setEmail(rs.getString("email"));
                buyerDTO.setLocation(rs.getString("location"));
                list.add(buyerDTO);
            }
            if(list.size()==0){
                throw new AdminException("No data in Buyers List");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new AdminException(e.getMessage());
        }
        return list;
    }
    public List<SellerDTO> ViewSellers() throws AdminException {
        List<SellerDTO> list=new ArrayList<>();

        try(Connection conn=Connectivity.getConnection()) {
            PreparedStatement ps=conn.prepareStatement("select * from seller");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                SellerDTO sellerDTO=new SellerDTO();
                sellerDTO.setSellerId(rs.getInt("sellerId"));
                sellerDTO.setSellerName(rs.getString("sellerName"));
                sellerDTO.setEmail(rs.getString("email"));
                sellerDTO.setLocation(rs.getString("location"));
                list.add(sellerDTO);
            }
            if(list.size()==0){
                throw new AdminException("No data in Sellers List");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new AdminException(e.getMessage());
        }
        return list;
    }
    public List<SearchBuyerDTO> DailySellingReport(String date) throws AdminException {
        List<SearchBuyerDTO> list=new ArrayList<>();

        try(Connection conn=Connectivity.getConnection()) {

            PreparedStatement ps=conn.prepareStatement("select b.buyerId,b.buyerName,b.email,p.productName,c.category_name,s.sellerName,p.price from products p " +
                    "Inner Join category c Inner Join seller s Inner Join buyer b " +
                    "On p.category_id=c.category_id and p.seller_id=s.sellerId and p.buyerId=b.buyerId " +
                    "where p.Selling_date=?");

            ps.setString(1, String.valueOf(date));

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                SearchBuyerDTO searchBuyerDTO=new SearchBuyerDTO();
                searchBuyerDTO.setBuyerId(rs.getInt("buyerId"));
                searchBuyerDTO.setBuyerName(rs.getString("buyerName"));
                searchBuyerDTO.setEmail(rs.getString("email"));
                searchBuyerDTO.setProductName(rs.getString("productName"));
                searchBuyerDTO.setCategoryName(rs.getString("category_name"));
                searchBuyerDTO.setSellerName(rs.getString("sellerName"));
                searchBuyerDTO.setPrice(rs.getInt("price"));
                list.add(searchBuyerDTO);
            }
            if(list.size()==0){
                throw new AdminException("No Item Sold on Date- "+date);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new AdminException(e.getMessage());
        }
        return list;
    }
    public List<ViewProductDTO> ViewProducts(int id) throws AdminException {
            List<ViewProductDTO> list=new ArrayList<>();
        if(id==101){
            System.out.print("        Enter Availble/Sold products type:(Available/sold) : ");
            String v=sc.next();
            try(Connection conn= Connectivity.getConnection()) {
                PreparedStatement ps=conn.prepareStatement("select * from products where status='"+v+"'");
                ResultSet rs=ps.executeQuery();
                while (rs.next()){
                    ViewProductDTO view=new ViewProductDTO();
                    view.setProductId(rs.getInt("product_id"));
                    view.setProductName(rs.getString("productName"));
                    view.setCategoryId(rs.getInt("category_id"));
                    view.setQuantity(rs.getInt("quantity"));
                    view.setPrice(rs.getInt("price"));
                    list.add(view);
                }
                if(list.size()==0){
                    throw new AdminException("No data in Buyers List");
                }
            }catch (SQLException e){
                e.printStackTrace();
                throw new AdminException(e.getMessage());
            }
            return list;
        }
        else{
            try(Connection conn= Connectivity.getConnection()) {
                PreparedStatement ps=conn.prepareStatement("select * from products where seller_id='"+id+"'");
                ResultSet rs=ps.executeQuery();
                while (rs.next()){
                    ViewProductDTO view=new ViewProductDTO();
                    view.setProductId(rs.getInt("product_id"));
                    view.setProductName(rs.getString("productName"));
                    view.setCategoryId(rs.getInt("category_id"));
                    view.setQuantity(rs.getInt("quantity"));
                    view.setPrice(rs.getInt("price"));
                    list.add(view);
                }
                if(list.size()==0){
                    throw new AdminException("No data in Buyers List");
                }
            }catch (SQLException e){
                e.printStackTrace();
                throw new AdminException(e.getMessage());
            }
            return list;
        }
    }
    public String SolveDispute(int categoryId,int productId) throws AdminException {
        String result;
        try(Connection conn=Connectivity.getConnection()) {
            PreparedStatement ps=conn.prepareStatement("update products set category_id=? where product_id=?");

            ps.setInt(1,categoryId);
            ps.setInt(2, productId);

            int x= ps.executeUpdate();
            if(x>0){
                result="Dispute Resolved Successfully";
            }else{
                throw new AdminException("No Product found with productId- "+productId );
            }


        }catch (SQLException e){
            e.printStackTrace();
            throw new AdminException(e.getMessage());
        }

        return result;
    }
        public String CreateListofProductstoSell(List<Products> products) throws SellerException {
        String result="Not Listed";

        try(Connection conn=Connectivity.getConnection()){
            int count=0;
            for(int i=0;i<products.size();i++){
                String productName = products.get(i).getProductName();
                int sellerId = products.get(i).getSellerId();
                int categoryId = products.get(i).getCategoryId();
                int quantity = products.get(i).getQuantity();
                int price = products.get(i).getPrice();
                PreparedStatement ps = null;
                try {
                    ps = conn.prepareStatement("insert into products (productName,seller_id,category_id, quantity,price) values(?,?,?,?,?)");
                    ps.setString(1, productName);
                    ps.setInt(2, sellerId);
                    ps.setInt(3, categoryId);
                    ps.setInt(4, quantity);
                    ps.setInt(5, price);
                    int x = ps.executeUpdate();
                    count++;
                } catch (SQLException e) {
                    System.out.println("Mismatch in data");
                    throw new SellerException("Failed to insert product: " + e.getMessage());
                }
            };
            if(count>0){
                result="Products Inserted in Auction List Successfully";
            }else{
                throw new SellerException(result);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SellerException(e.getMessage());
        }
        return result;
    }
        public String UpdateProductPrice(int productId,int price) throws SellerException {
        String result;

        try(Connection conn=Connectivity.getConnection()) {
            PreparedStatement ps=conn.prepareStatement("update products set price=? where product_id=?");

            ps.setInt(1,price );
            ps.setInt(2, productId);

            int x= ps.executeUpdate();
            if(x>0){
                result="Updated Successfully";
            }else{
                throw new SellerException("No Product found with productId- "+productId );
            }


        }catch (SQLException e){
            e.printStackTrace();
            throw new SellerException(e.getMessage());
        }

        return result;
    }
        public String DeleteProductItems(int productId) throws SellerException {
        String result;

        try(Connection conn=Connectivity.getConnection()) {
            PreparedStatement ps=conn.prepareStatement("delete from products where product_id=?");

            ps.setInt(1, productId);

            int x= ps.executeUpdate();
            if(x>0){
                result="Deleted Successfully";
            }else{
                throw new SellerException("No Product found with productId- "+productId );
            }


        }catch (SQLException e){
            e.printStackTrace();
            throw new SellerException(e.getMessage());
        }

        return result;
    }
    public List<SoldItemsDTO> SoldItemHistory(int sellerId) throws SellerException {
        List<SoldItemsDTO> list=new ArrayList<>();

        try(Connection conn=Connectivity.getConnection()) {

            PreparedStatement ps=conn.prepareStatement("select p.product_id,p.productName,s.sellerName,c.category_name,p.price,p.status from products p " +
                    "Inner Join category c Inner Join seller s " +
                    "On p.category_id=c.category_id and p.seller_id=s.sellerId " +
                    "where status='sold' and s.sellerId=?");

            ps.setInt(1,sellerId);

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                SoldItemsDTO soldItemsDTO=new SoldItemsDTO();
                soldItemsDTO.setProductId(rs.getInt("product_id"));
                soldItemsDTO.setProductName(rs.getString("productName"));
                soldItemsDTO.setSellerName(rs.getString("sellerName"));
                soldItemsDTO.setCategoryName(rs.getString("category_name"));
                soldItemsDTO.setPrice(rs.getInt("price"));
                soldItemsDTO.setStatus(rs.getString("status"));
                list.add(soldItemsDTO);
            }
            if(list.size()==0){
                throw new SellerException("No Item sold in Product List/ SellerId not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SellerException(e.getMessage());
        }
        return list;
    }
    public List<SoldItemsDTO> searchItemByCategory(int categoryId) throws ByerException {
        List<SoldItemsDTO> list=new ArrayList<>();
        try(Connection conn=Connectivity.getConnection()) {
            PreparedStatement ps=conn.prepareStatement("select p.product_id,p.productName,s.sellerName,c.category_name,p.price,p.status " +
                    "from auction_management_system.products p " +
                    "Inner Join auction_management_system.category c " +
                    "Inner Join auction_management_system.seller s " +
                    "On p.category_id=c.category_id and p.seller_id=s.sellerId " +
                    "where p.status='Available' AND c.category_id=?");
            ps.setInt(1,categoryId);

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                SoldItemsDTO soldItemsDTO=new SoldItemsDTO();
                soldItemsDTO.setProductId(rs.getInt("product_id"));
                soldItemsDTO.setProductName(rs.getString("productName"));
                soldItemsDTO.setSellerName(rs.getString("sellerName"));
                soldItemsDTO.setCategoryName(rs.getString("category_name"));
                soldItemsDTO.setPrice(rs.getInt("price"));
                soldItemsDTO.setStatus(rs.getString("status"));
                list.add(soldItemsDTO);
            }
            if(list.size()==0){
                throw new ByerException("No Item found in Product List/ Category not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new ByerException(e.getMessage());
        }
        return list;
    }

    //==========================================*****************===================================================//

    public String BuyItem(int buyerId, LocalDate date, String productName) throws ByerException {
        String result;

        try(Connection conn=Connectivity.getConnection()) {
            PreparedStatement ps=conn.prepareStatement("update products set status='sold',buyerId=?,Selling_date=? where productName=?");

            ps.setInt(1, buyerId);
            ps.setDate(2, Date.valueOf(date));
            ps.setString(3, productName);
            int x= ps.executeUpdate();
            if(x>0){
                result="Item Bought Successfully- Will be delivered shortly";
            }else{
                throw new ByerException("No Product found with productName- "+productName );
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new ByerException(e.getMessage());
        }

        return result;
    }

    //==========================================*****************===================================================//


    public List<SearchBuyerDTO> ViewAllBuyersDetails(int categoryId) throws ByerException {
        List<SearchBuyerDTO> list=new ArrayList<>();

        try(Connection conn=Connectivity.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT b.buyerId, b.buyerName, b.email, p.productName, c.category_name, s.sellerName, p.price " +
                            "FROM auction_management_system.products p " +
                            "INNER JOIN auction_management_system.category c ON p.category_id = c.category_id " +
                            "INNER JOIN auction_management_system.seller s ON p.seller_id = s.sellerId " +
                            "INNER JOIN auction_management_system.buyer b ON p.buyerId = b.buyerId " +
                            "WHERE c.category_id = ? AND status = 'sold'"
            );

            ps.setInt(1,categoryId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                SearchBuyerDTO searchBuyerDTO=new SearchBuyerDTO();
                searchBuyerDTO.setBuyerId(rs.getInt("buyerId"));
                searchBuyerDTO.setBuyerName(rs.getString("buyerName"));
                searchBuyerDTO.setEmail(rs.getString("email"));
                searchBuyerDTO.setProductName(rs.getString("productName"));
                searchBuyerDTO.setCategoryName(rs.getString("category_name"));
                searchBuyerDTO.setSellerName(rs.getString("sellerName"));
                searchBuyerDTO.setPrice(rs.getInt("price"));
                list.add(searchBuyerDTO);
            }
            if(list.size()==0){
                throw new ByerException("No Item found in Bought Items List/ Category not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new ByerException(e.getMessage());
        }
        return list;
    }
}

