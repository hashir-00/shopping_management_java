import java.util.ArrayList;

public class ShoppingCart extends Product {
    private ArrayList<Product> cartList;

    public ShoppingCart() {
    }



    public int calculate_price(ArrayList<Product> cartList){
        int price=0;

        for(int i=0;i<cartList.size();i++){
            price=cartList.get(i).getProductPrice()+price;
        }
        return (price);
    }


}
