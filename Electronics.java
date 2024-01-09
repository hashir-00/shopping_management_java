import java.io.Serializable;
import java.util.Map;

public class Electronics extends Product implements Serializable {
    private String brand;
    private int warrantyPeriod_months;


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


    @Override
    public String toString() {
        return super.toString()+
                ":"+ brand +
                ":"+warrantyPeriod_months ;
    }
    public String productDetails(){
    return super.productDetails()
        + "\n\nBrand: "
        + getBrand()
        + "\n\nWarranty: "
        + getWarrantyPeriod_months()
        + " months";
    }
    public String GUIinfo(){
        return getBrand()+" "+getWarrantyPeriod_months()+": months warranty";
    }
    public String cartGUIinfo(){
        return getProductId()+",\n"+getProductName()+",\n"+getBrand();
    }
    }

