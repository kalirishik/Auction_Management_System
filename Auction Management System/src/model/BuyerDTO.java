package model;

public class BuyerDTO {
    private int buyerId;
    private String buyerName;
    private String phoneno;
    private String email;
    private String location;

    public BuyerDTO() {
    }

    public BuyerDTO(int buyerId, String buyerName, String phoneno,String email, String location) {
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.phoneno=phoneno;
        this.email = email;
        this.location = location;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public String getPhoneno() {
        return phoneno;
    }
    public void setPhoneno(String phoneno){this.phoneno=phoneno;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "BuyerDTO{" +
                "buyerId=" + buyerId +
                ", buyerName='" + buyerName + '\'' +
                ", phoneno='"+ phoneno+'\''+
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
