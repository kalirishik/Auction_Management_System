package model;

public class ViewProductDTO {
    private int productId;
    private String productName;
    private int category_id;
    private int price;
    private int quantity;

    public ViewProductDTO() {
    }

    public ViewProductDTO(int productId, String productName,int category_id,int price,int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.category_id=category_id;
        this.price = price;
        this.quantity = quantity;
    }

    public void setProductId(int productId){this.productId=productId;}
    public void setProductName(String productName){this.productName=productName;}
    public void setCategoryId(int category_id){this.category_id=category_id;}
    public void setPrice(int price){this.price=price;}
    public void setQuantity(int quantity){this.quantity=quantity;}
    public int getProductId(){return productId;}
    public int getPrice(){return price;}
    public int getQuantity(){return quantity;}
    public int getCategory_id(){return category_id;}
    public String getProductName(){return productName;}


    @Override
    public String toString() {
        return "ViewProductDTO{" +
                "ProductId=" + productId +
                ", ProductName='" + productName + '\'' +
                ", category_id='"+ category_id+'\''+
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
