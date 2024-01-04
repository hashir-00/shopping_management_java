import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;

        for (Product product : cartItems) {
            totalPrice += product.getProductPrice();
        }
        if (discount()){
            return totalPrice*.8;
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

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void addItem(Product selectedProduct) {
        cartItems.add(selectedProduct);
    }
    }


