import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private List<Product> cartItems;
    private int count=0;
    private HashMap<String,String> purchaseHistory= new HashMap<>();


    private double item_discount_amount=0;
    private double first_purchase_discount=0;
    private final static File PurchaseFile = new File("purchaseHistory.txt");//creating a file


    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public double calculateTotalPrice(String user) {
        double totalPrice = 0;

        for (Product product : cartItems) {
            totalPrice += product.getProductPrice();
        }
        if (firstPurchaseDiscount(user) && discount()){
            first_purchase_discount= totalPrice*.1;
            item_discount_amount=totalPrice*.2;
            return totalPrice*.7;
        }
        else if (discount()){
            item_discount_amount= totalPrice*.2;
            first_purchase_discount=0;
            return totalPrice*.8;

        }
        else if(firstPurchaseDiscount(user)){
            first_purchase_discount= totalPrice*.1;
            item_discount_amount=0;
            return totalPrice *.9;

        }

        else {
            return totalPrice;}

    }
    private boolean discount(){
        int electronicCount=0;
        int clothCount=0;
        for(int i=0;i<cartItems.size();i++){
            if (cartItems.get(i).getType().equals("Electronic")){
                electronicCount+=1;

            }
            else {
                clothCount+=1;
            }
        }
        if(clothCount>=3 || electronicCount>=3 ){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean firstPurchaseDiscount(String username){
        LoadPurchaseHistory();
    for (Map.Entry<String, String> user_purchase_history:
            purchaseHistory.entrySet()){
        if (username.equals(user_purchase_history.getKey())){
            return false;
        }

    }return true;



    }

    public List<Product> getCartItems() {
        return cartItems;
    }
    public double getFirst_purchase_discount() {
        return first_purchase_discount;
    }
    public double getItem_discount_amount() {
        return item_discount_amount;
    }

    public void addItem(Product selectedProduct) {


        cartItems.add(selectedProduct);

    }

    public void savePurchaseHistory(String username,String productName){
        BufferedWriter bf = null;
        try {

            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(PurchaseFile,true));



            // put key and value separated by a colon
                bf.write( username.toUpperCase()+ ":"
                        + productName);
            bf.newLine();


            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {

                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }

        }
    }
    public void LoadPurchaseHistory(){
        BufferedReader br = null;
        try {

            // create file object


            // create BufferedReader object from the File
            br = new BufferedReader(new FileReader(PurchaseFile));

            String line = null;

            // read file line by line
            while ((line = br.readLine()) != null) {

                // split the line by :
                String[] parts = line.split(":");

                // first part is name, second is pname
                String name = parts[0].trim();
                String pname= parts[1].trim();



                if (!name.equals("") && !pname.equals(""))
                    purchaseHistory.put(name,pname);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }


    }

}




