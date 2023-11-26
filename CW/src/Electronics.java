import java.util.Map;

public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod_months;

    public Map<String, String> electronicItems;
    public Electronics() {
    }

    public Electronics(String type,String productId, String productName, String brand, int stocksAvailable, int productPrice, int warrantyPeriod_months) {
        super(productId, productName, stocksAvailable, productPrice,type);

        this.brand = brand;
        this.warrantyPeriod_months = warrantyPeriod_months;
    }



    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod_months() {
        return warrantyPeriod_months;
    }

    public void setWarrantyPeriod_months(int warrantyPeriod_months) {
        this.warrantyPeriod_months = warrantyPeriod_months;
    }
   /* public void mapItems(String type,String productId, String productName, String brand, int stocksAvailable, int productPrice, int warrantyPeriod_months){

        electronicItems.put(type,this.type);
        electronicItems.put(productId,this.productId);
        electronicItems.put(productName,this.productName);
        electronicItems.put(brand,this.brand);
        electronicItems.put(Integer.toString(stocksAvailable),Integer.toString(this.stocksAvailable));
        electronicItems.put(Integer.toString(productPrice),Integer.toString(this.productPrice));
        electronicItems.put(Integer.toString(warrantyPeriod_months),Integer.toString(this.warrantyPeriod_months));


    }*/

    @Override
    public String toString() {
        return super.toString()+
                ",brand='" + brand + '\'' +
                ", warrantyPeriod_months=" + warrantyPeriod_months ;
    }
}
