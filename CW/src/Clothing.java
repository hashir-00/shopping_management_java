public class Clothing extends Product {
    private String colour;
    private int size;

    public Clothing() {
    }


    public Clothing(String type,String productId, String productName, int stocksAvailable, int productPrice, String colour, int size) {
        super(productId, productName, stocksAvailable, productPrice,type);

        this.colour = colour;
        this.size = size;
    }


    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() +
                ",colour='" + colour + '\'' +
                ", size=" + size + " "
                ;
    }
}
