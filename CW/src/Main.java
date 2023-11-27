import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main implements ShoppingManager {
    static ArrayList<Product> productsInSystem = new ArrayList<>();

    static ArrayList<Product> EproductsInSystem = new ArrayList<>();
    static ArrayList<Product> CproductsInSystem = new ArrayList<>();


    static File fileElectronic = new File("productsElectronic.txt");//creating a file
    static File fileCloth = new File("productsCLoth.txt");//creating a file


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean x = true;
        boolean y = true;
        int counter = 0;//items inside the array
        int countAdded = 0;//items added
        String productId, prodcutName, brand, color, type;
        int size, productPrice, stocksAvailable, warrantyPeriod;

        Electronics ss = new Electronics("elec", "11", "naa", "sds", 12, 122, 21);
        Clothing sss = new Clothing();


        while (x) {
            for (int i = 0; i < productsInSystem.size(); i++)
                if (productsInSystem.get(i) != null) counter++;
            displayConsole();

            System.out.print("\nselect option:");
            String consoleInput = scan.next();//input
            scan.nextLine();// remove the duplication of newline
            if (inputValidator(consoleInput)) {
                int option = Integer.parseInt(consoleInput);
                if (option == 1) {

                    if (counter < 50) {

                        while (y) {

                            AddProductDisplayConsole();
                            System.out.print("\nselect option:");
                            consoleInput = scan.next();//input
                            scan.nextLine();//remove the duplication of newline

                            if (inputValidator(consoleInput)) {
                                int subOption = Integer.parseInt(consoleInput);
                                if (subOption == 1) {

                                    System.out.println("\nElectronics Products\n");
                                    System.out.println("Enter productID:");
                                    productId = scan.nextLine();
                                    System.out.println("Enter product name:");
                                    prodcutName = scan.nextLine();
                                    System.out.println("Enter product Brand name:");
                                    brand = scan.nextLine();
                                    //
                                    System.out.println("Enter stocks available:");
                                    stocksAvailable = scan.nextInt();
                                    System.out.println("Enter product price:");
                                    productPrice = scan.nextInt();
                                    System.out.println("Enter product warrantyPeriod(weeks):");
                                    warrantyPeriod = scan.nextInt();

                                    //
                                    type = "Electronic";

                                    Electronics electronicsProduct = new Electronics(type, productId, prodcutName, brand, stocksAvailable, productPrice, warrantyPeriod);
                                    countAdded++;
                                    productsInSystem.add(electronicsProduct);
                                    EproductsInSystem.add(electronicsProduct);
                                    System.out.println("Product added successfully");

                                } else if (subOption == 2) {
                                    System.out.println("\nClothing Products\n");
                                    System.out.print("Enter productID:");
                                    productId = scan.nextLine();
                                    System.out.println("Enter product name:");
                                    prodcutName = scan.nextLine();
                                    System.out.println("Enter product color:");
                                    color = scan.nextLine();
                                    //
                                    System.out.println("Enter product size:");
                                    size = scan.nextInt();
                                    System.out.println("Enter stocks available:");
                                    stocksAvailable = scan.nextInt();
                                    System.out.println("Enter product price:");
                                    productPrice = scan.nextInt();
                                    //
                                    type = "Cloth";

                                    Clothing clothProduct = new Clothing(type, productId, prodcutName, stocksAvailable, productPrice, color, size);
                                    countAdded++;
                                    productsInSystem.add(clothProduct);
                                    CproductsInSystem.add(clothProduct);
                                    System.out.println("Product added successfully");


                                } else if (subOption == 3) {

                                    y = false;
                                } else if (Integer.parseInt(consoleInput) > 3 || Integer.parseInt(consoleInput) < 1) {
                                    System.out.println("enter a valid input in range");
                                }
                            } else {
                                System.out.println("enter a valid intseger");


                            }

                        }


                    } else {
                        System.out.println("Sorry,The product Capacity has reached" + "\nRemove an item to proceed");

                    }


                }
                else if (Integer.parseInt(consoleInput) == 2) {

                    if (counter == 0) {
                        System.out.println("no items to remove");
                    } else {
                        productList();
                        System.out.print("\nproduct type to remove");


                        consoleInput = scan.next();
                        if (inputValidator(consoleInput) && Integer.parseInt(consoleInput) - 1 < productsInSystem.size()) {
                            Product temp = productsInSystem.get(Integer.parseInt(consoleInput) - 1);
                            System.out.println(temp.getType() + " product with id:" + temp.getProductId() + " removed");

                            productsInSystem.remove(Integer.parseInt(consoleInput) - 1);


                        } else {
                            System.out.println("enter a valid input");


                        }


                    }


                }
                else if (Integer.parseInt(consoleInput) == 3) {
                    if (counter == 0) {
                        System.out.println("No Products To show");
                    } else {
                        productList();
                    }


                }
                else if (Integer.parseInt(consoleInput) == 4) {
                    if (countAdded == 0) {
                        System.out.println("no Products to save");
                    } else {
                        saveProductsElectronics();
                        saveproductsCloths();
                        System.out.println("Successfully saved");
                    }
                }
                else if (Integer.parseInt(consoleInput) == 5) {
//open gui
                } else if ((Integer.parseInt(consoleInput) == 6)) {
                    System.out.println("Exiting");
                    x = false;


                }
                else if (Integer.parseInt(consoleInput) > 6) {
                    System.out.println("Enter a number in the valid range");

                } else
                {
                    System.out.println("Enter a valid input ");

                }
            } else {
                System.out.println("enter a valid inteager");
            }


        }

    }


    public static void displayConsole() {
        System.out.println("\n\n\n" + "WESTMINSTER SHOPPING MANAGER CONSOLE" + "\n\nselect an option:-" + "\n1.Add a PRODUCT" + "\n2.Remove a PRODCUT" + "\n3.View all PRODUCTS" + "\n4.Save all PRODUCTS" + "\n5.OPEN CONSOLE" + "\n6.Exit");

    }

    public static void AddProductDisplayConsole() {
        System.out.println("\n\n1.Add an Electronic Product " + "\n2.Add a Clothing Product" + "\n3.Exit to main ");

    }

    public static void saveProductsElectronics() {

        try {

            FileWriter file_writer = new FileWriter("productsElectronic.txt");//creating the file writer object
            BufferedWriter bf = new BufferedWriter(file_writer);
            PrintWriter pw = new PrintWriter(bf);

            for (int i = 0; i < EproductsInSystem.size(); i++) {
                pw.println(EproductsInSystem.get(i).toString());
            }


            pw.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static void saveproductsCloths() {
        try {

            FileWriter file_writer = new FileWriter("productsCLoth.txt");//creating the file writer object
            BufferedWriter bf = new BufferedWriter(file_writer);
            PrintWriter pw = new PrintWriter(bf);

            for (int i = 0; i < EproductsInSystem.size(); i++) {
                pw.println(CproductsInSystem.get(i).toString());
            }


            pw.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static void loadProductsElectrnoic() {


//            try {
//
//                File file = new File("products.txt");//creating a file
//                Scanner file_reader = new Scanner(file);//creating a scanner object
//                String  d=null;//using null string to convert the file items to a string
//
//                int j=0,l=0,m=0;//initiallizing 3 diff  count var for the for loop ahead
//                while (file_reader.hasNext()) {
//                    for (int i=0; file_reader.hasNext();i++){
//
//                        d = file_reader.nextLine();//converting file items to a single string
//                    }
//
//
//                }
//                file_reader.close();
//
//                //assigning seat values to the original char array
//                //seat values are obtained by removing the unnecessary char's
//
//                //row1
//                for (int i=5; i<17;i++){
//
//                    assert d != null;
//                    row1[j]=d.charAt(i);
//                    // System.out.print(row1[j]);
//                    j++;
//
//                }
//
//
//                //row2
//                for (int i=22; i<38;i++){
//
//                    row2[l]=d.charAt(i);
//                    //System.out.print(row2[l]);
//                    l++;
//
//                }
//
//
//                //row3
//                for (int i=43; i<63;i++){
//                    row3[m]=d.charAt(i);
//                    //System.out.print(row3[m]);
//                    m++;
//
//                }
//
//
//                //loop_for(rows);
//
//
//
//            } catch (IOException ex) {
//                System.out.println(ex);
//            }System.out.println("row data loaded successfully");


    }
    public static void loadProductsCloth(){

    }

    public static void productList() {

        for (int i = 0; i < productsInSystem.size(); i++) {
            System.out.println(i + 1 + "-" + productsInSystem.get(i));
        }

    }

    public static boolean inputValidator(String input) {

        boolean isInt = false;
        try {
            // If the below method call doesn't throw an exception, we know that it's a valid integer
            int i = Integer.parseInt(input);
            isInt = true;
        } catch (NumberFormatException e) {


            isInt = false;
        }
        return isInt;
    }


}



