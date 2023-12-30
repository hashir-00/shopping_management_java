public abstract class Product {
    protected String productId;
    protected String productName;


    protected String type;

    protected int stocksAvailable, productPrice;

    //check for the instance var brand_name
    public Product() {
    }


    public Product(String productId, String productName, int stocksAvailable, int productPrice,String type) {
        this.productId = productId;
        this.type=type;
        this.productName = productName;
        this.stocksAvailable = stocksAvailable;
        this.productPrice = productPrice;
    }
    public String getType() {
        return type;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public int getStocksAvailable()
    {
        return stocksAvailable;
    }

    public void setStocksAvailable(int stocksAvailable) {

        this.stocksAvailable = stocksAvailable;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {

        this.productPrice = productPrice;
    }


    @Override
    public String toString() {
        return type+":"+ productId +":"+  productName+":" +
                  stocksAvailable+":" + productPrice;
    }
    public String productDetails(){
        return "Type: "+getType()+"\nProduct Name: "+getProductName()+ "\nStocks Available: "+getStocksAvailable()+"\nPrice: "+getProductPrice();
    }



}
