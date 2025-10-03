package MainComponent;

import dao.*;
import model.Seller;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
            getStarted();
    }
    public static void getStarted() throws Exception {
        Scanner sc = new Scanner(System.in);
        BuyerDao b=new BuyerDaoImpl();
        SellerDao s=new SellerDaoImpl();
        AdminDao a=new AdminDaoImpl();
        int ch=6, c=6;
        System.out.println("--------------------------------------------------------");
        System.out.println("          Welcome to Auction Management System          ");
        do{
            System.out.println("--------------------------------------------------------");
            System.out.println("           Choose the Option from Below  ");
            System.out.println("        Press 1 to         LogIn as Admin.");
            System.out.println("        Press 2 to         LogIn as Seller.");
            System.out.println("        Press 3 to         LogIn as Buyer.");
            System.out.println("        Press 4 to         Register as Buyer.");
            System.out.println("        Press 5 to         Register as Seller." );
            System.out.println("        Press 6 to         EXIT");
            System.out.print("        Enter Any Number : ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("        Enter EmailId      : ");
                    String e1=sc.next().toLowerCase();
                    System.out.print("        Enter Password   : ");
                    String p1=sc.next().toLowerCase();
                    boolean ad= a.checkAdmin(e1,p1);
                    if(ad){
                        System.out.println("\n--------------------------------------------------");
                        adminProcess();
                    }
                    else System.out.println("Invalid");
                    c=1;
                    break;
                case 2:
                    System.out.print("        Enter EmailId    : ");
                    String e2=sc.next();
                    System.out.print("        Enter Password   : ");
                    String p2=sc.next();
                    Seller seller =s.checkSeller(e2,p2);
                    if(seller!=null){
                        System.out.println("\n----------------------------------------------------");
                        System.out.println("            Welcome "+seller.getSellerName()+" 🎉");
                        int id= seller.getSellerId();
                        sellerProcess(id);
                    }
                    else System.out.println("Invalid");
                    c=2;
                    break;
                case 3:
                    System.out.print("        Enter EmailId    : ");
                    String e3=sc.next();
                    System.out.print("        Enter Password   : ");
                    String p3=sc.next();
                    model.Buyer buyer= b.checkBuyer(e3,p3);
                    System.out.println("\n--------------------------------------------------");
                    if(buyer!=null){
                        System.out.println("            Welcome "+buyer.getBuyerName()+" 🎉 \n----------------------------------------");
                        int id=buyer.getBuyerId();
                        buyerProcess(id);
                    }
                    else System.out.println("           Invalid");
                    c=3;
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("        Enter Buyer Name : ");
                    String name = sc.nextLine();
                    System.out.print("        Enter phoneno  : ");
                    String ph1 = sc.next();
                    System.out.print("        Enter EmailId  : ");
                    String email3 = sc.next();
                    System.out.print("        Enter Password : ");
                    String pwd3 = sc.next();
                    System.out.print("        Enter Location : ");
                    sc.nextLine();
                    String l1 = sc.nextLine();
                    if (b.addBuyer(name,ph1,email3, pwd3,l1))
                        System.out.println("---------------------------------------\n        Buyer data are Registered. ------------------------------");
                    else
                        System.out.println("---------------------------------------\n        EmailID already exists. Cannot add customer.");
                    c=4;
                    break;
                case 5:
                    sc.nextLine();
                    System.out.print("        Enter  Seller Name     : ");
                    String name2 = sc.nextLine();
                    System.out.print("        Enter phoneno  : ");
                    String ph2 = sc.next();
                    System.out.print("        Enter EmailId  : ");
                    String email4 = sc.next();
                    System.out.print("        Enter Password : ");
                    String pwd4 = sc.next();
                    sc.nextLine();
                    System.out.print("        Enter Location : ");
                    String l2 = sc.nextLine();
                    if (s.addSeller(name2, ph2,email4, pwd4,l2))
                        System.out.println("---------------------------------------\n        Seller data are Registered. ----------------------------");
                    else
                        System.out.println("---------------------------------------\n        EmailID already exists. Cannot add customer.");
                    c=5;
                    break;
                case 6:
                    System.out.println("------------------------------------------------------\n         Thanks for Using \n------------------------------------------------------                       ");
                    c=6;
                    break;
                default:
                    c=1;
                    System.out.println("------------------------------------------\n            Please Enter Valid Option.");
                    break;
            }
        }while(ch!=c);
    }
    public static void adminProcess() throws Exception {
        Scanner sc=new Scanner(System.in);
        AdminDao a=new AdminDaoImpl();
        ProductDao p= new ProductDaoImpl();
        while(true) {
            a.AdminHomeWindow();
            System.out.print("    Enter Choice : ");
            String choiceAdmin = sc.next();
            if(choiceAdmin.equals("1")) {
                p.viewBuyerList();
            }else if(choiceAdmin.equals("2")) {
                p.viewSellerList();
            }else if (choiceAdmin.equals("3")) {
                p.dailySellingReport();
            }else if(choiceAdmin.equals("4")){
                p.viewProduct(101);
            }else if(choiceAdmin.equals("5")){
                p.disputeResolve();
            }else if(choiceAdmin.equals("6")){
                System.out.println("                LOGOUT SUCCESSFUL !");
                System.out.println("----------------------------------------------------");
                break;
            }else {
                System.out.println("CHOOSE CORRECT INPUT RANGE FROM 1 TO 6" );
            }
        }
        getStarted();
        System.out.println();
    }
    public static void buyerProcess(int id) throws Exception {
        Scanner sc=new Scanner(System.in);
        BuyerDao b=new BuyerDaoImpl();
        ProductDao p=new ProductDaoImpl();
        while(true) {
            b.BuyerHomeWindow();
            String choiceAdmin = sc.nextLine();
            if(choiceAdmin.equals("1")) {
                p.searchItemCategory();
            }else if(choiceAdmin.equals("2")) {
                p.buyitem(id);
            }else if (choiceAdmin.equals("3")) {
                p.searchBuyerItembyCategory();
            }else if(choiceAdmin.equals("4")){
                System.out.println( "LOGOUT SUCCESSFUL !");
                break;
            }else {
                System.out.println("CHOOSE CORRECT INPUT RANGE FROM 1 TO 4");
            }
        }
        getStarted();
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }
    public static void sellerProcess(int id) throws Exception {
        Scanner sc=new Scanner(System.in);
        SellerDao s=new SellerDaoImpl();
        ProductDao p=new ProductDaoImpl();
        while(true){
            s.SellerHomeWindow();
        System.out.print("        Enter Choice : ");
        String choiceAdmin = sc.next();
        if(choiceAdmin.equals("1")) {
            s.categoryView();
            p.createProductList(id);
        }else if(choiceAdmin.equals("2")) {
            p.updateProductlist();
        }else if (choiceAdmin.equals("3")) {
            p.deleteProductitem();
        }else if (choiceAdmin.equals("4")) {
            p.soldItemHistory(id);
        }
        else if (choiceAdmin.equals("5")) {
            p.viewProduct(id);
        }else if(choiceAdmin.equals("6")){
            System.out.println("LOGOUT SUCCESSFUL !");
            break;
        }else {
            System.out.println("CHOOSE CORRECT INPUT RANGE FROM 1 TO 5");
        }
    }
        getStarted();
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }
}
