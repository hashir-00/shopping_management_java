import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;

        for (Product product : cartItems) {
            totalPrice += product.getProductPrice();
        }

        return totalPrice;
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void addItem(Product selectedProduct) {
        cartItems.add(selectedProduct);
    }

    // You may need additional methods for removing items, etc.
}
