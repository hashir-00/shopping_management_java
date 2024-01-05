import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Main implements ShoppingManager {



  // File paths for electronic and clothing products
  static final File fileElectronic = new File("productsElectronic.txt"); // creating a file
  static final File fileCloth = new File("productsCLoth.txt"); // creating a file

  //array list for all products
  static ArrayList<Product> productsInSystem = new ArrayList<>();

  // array lists to save to the file seperately
  static ArrayList<Product> EproductsInSystem = new ArrayList<>();
  static ArrayList<Product> CproductsInSystem = new ArrayList<>();

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in); // initiate a scanner
    boolean x = true, z = true; // for while loop

    int counter = 0; // items inside the array
    int countAdded = 0; // items added
    String productId, prodcutName, brand, color, type;
    String size, productPrice, stocksAvailable, warrantyPeriod;

    // load the file data to the arrays
    loadProductsElectrnoic();
    loadProductsCloth();

    while (z) {
      System.out.println("welcome,\n1.User\n2.Manager");
      String input = scan.nextLine();
      if (inputValidator(input)) {
        if (Integer.parseInt(input) == 1) {

          System.out.println("Are you a new user?\nY\nN");
          String new_user = scan.nextLine();
          if (new_user.toUpperCase().equals("Y")) {
            System.out.println("Please enter Your User Name and password");
            System.out.print("username:");
            String username = scan.nextLine();
            System.out.print("password:");
            String password = scan.nextLine();

            User user = new User(username, password);
            user.setUsers(username, password);
            System.out.println(
                "A new user with username:"
                    + user.getUsername().toUpperCase()
                    + " has been created");

          } else if (new_user.toUpperCase().equals("N")) {
           startLoginConsole();

           /* System.out.println("Please enter Your User Name and password");
            System.out.print("username:");
            String username = scan.nextLine();
            System.out.print("password:");
            String password = scan.nextLine();*/






          } else {
            System.out.println("please enter a valid answer");
          }
          // open console

        }





















        else if (Integer.parseInt(input) == 2) {
          while (x) {
            for (int i = 0; i < productsInSystem.size(); i++) {
              if (productsInSystem.get(i) != null)
                counter++; // counter to make sure arrays is not exceeding limit
            }

            displayConsole();

            System.out.print("\nselect option:");
            String consoleInput = scan.next(); // input
            scan.nextLine(); // remove the duplication of newline
            if (inputValidator(consoleInput)) {
              int option = Integer.parseInt(consoleInput);
              if (option == 1) {

                if (counter < 50) {
                  boolean y = true;

                  while (y) {
                    boolean m = true, n = true, l = true;
                    AddProductDisplayConsole();
                    System.out.print("\nselect option:");
                    consoleInput = scan.next(); // input
                    scan.nextLine(); // remove the duplication of newline

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

                        while (m) {
                          System.out.println("Enter stocks available:");
                          stocksAvailable = scan.nextLine();
                          if (inputValidator(stocksAvailable)) {
                            while (n) {
                              System.out.println("Enter product price:");
                              productPrice = scan.nextLine();
                              if (inputValidator(productPrice)) {
                                while (l) {
                                  System.out.println("Enter product warrantyPeriod(weeks):");
                                  warrantyPeriod = scan.nextLine();
                                  if (inputValidator(warrantyPeriod)) { //
                                    type = "Electronic";

                                    Electronics electronicsProduct =
                                        new Electronics(
                                            type,
                                            productId,
                                            prodcutName,
                                            brand,
                                            Integer.parseInt(stocksAvailable),
                                            Integer.parseInt(productPrice),
                                            Integer.parseInt(warrantyPeriod));
                                    countAdded++;

                                    EproductsInSystem.add(electronicsProduct);

                                    System.out.println("Product added successfully");
                                    m = n = l = false;
                                  } else {
                                    System.out.println("enter a valid input");
                                  }
                                }
                              } else {
                                System.out.println("enter a valid input");
                              }
                            }
                            //
                          } else {
                            System.out.println("enter a valid input");
                          }
                        }

                      } else if (subOption == 2) {

                        System.out.println("\nClothing Products\n");
                        System.out.print("Enter productID:");
                        productId = scan.nextLine();
                        System.out.println("Enter product name:");
                        prodcutName = scan.nextLine();
                        System.out.println("Enter product color:");
                        color = scan.nextLine();
                        while (m) {
                          System.out.println("Enter stocks available:");
                          stocksAvailable = scan.nextLine();
                          if (inputValidator(stocksAvailable)) {
                            while (n) {
                              System.out.println("Enter product price:");
                              productPrice = scan.nextLine();
                              if (inputValidator(productPrice)) {
                                while (l) {
                                  System.out.println("Enter product Size:");
                                  size = scan.nextLine();
                                  if (inputValidator(size)) { //
                                    type = "Cloth";

                                    Clothing clothProduct =
                                        new Clothing(
                                            type,
                                            productId,
                                            prodcutName,
                                            Integer.parseInt(stocksAvailable),
                                            Integer.parseInt(productPrice),
                                            color,
                                            Integer.parseInt(size));
                                    countAdded++;

                                    CproductsInSystem.add(clothProduct);
                                    System.out.println("Product added successfully");
                                    m = n = l = false;
                                  } else {
                                    System.out.println("enter a valid input");
                                  }
                                }
                              } else {
                                System.out.println("enter a valid input");
                              }
                            }
                            //
                          } else {
                            System.out.println("enter a valid input");
                          }
                        }

                      } else if (subOption == 3) {

                        y = false;
                      } else if (Integer.parseInt(consoleInput) > 3
                          || Integer.parseInt(consoleInput) < 1) {
                        System.out.println("enter a valid input in range");
                      }
                    } else {
                      System.out.println("enter a valid intseger");
                    }
                  }

                } else {
                  System.out.println(
                      "Sorry,The product Capacity has reached" + "\nRemove an item to proceed");
                }

              } else if (Integer.parseInt(consoleInput) == 2) {

                if (counter == 0) {
                  System.out.println("no items to remove");

                } else {
                  productList();
                  System.out.print("\nproduct index to remove:");

                  consoleInput = scan.next();
                  if (inputValidator(consoleInput)
                      && Integer.parseInt(consoleInput) - 1 < productsInSystem.size()
                      && Integer.parseInt(consoleInput) != 0) {
                    Product temp = productsInSystem.get(Integer.parseInt(consoleInput) - 1);

                    System.out.println(
                        temp.getType() + " product with id:" + temp.getProductId() + " removed");
                    if (temp.getType().equals("Electronic")) {
                      removeE(temp.toString()); // removing it from the file
                    } else if (temp.getType().equals("Cloth")) {
                      removeC(temp.toString());

                    } else {
                      System.out.println("no product available");
                    }

                    productsInSystem.remove(
                        Integer.parseInt(consoleInput) - 1); // removing it from the array

                  } else if (inputValidator(consoleInput) && Integer.parseInt(consoleInput) == 0) {
                    System.out.println("please enter a existing value");
                  } else {
                    System.out.println("enter a valid input");
                  }
                }

              } else if (Integer.parseInt(consoleInput) == 3) {
                if (productsInSystem.size() == 0) {
                  System.out.println("No products to show");
                } else {
                  productList();
                }

              } else if (Integer.parseInt(consoleInput) == 4) {
                if (countAdded == 0) {
                  System.out.println("no Products to save");
                } else {
                  countAdded = 0;

                  saveProductsElectronics();
                  saveproductsCloths();
                  System.out.println("Successfully saved");
                }
              } else if (Integer.parseInt(consoleInput) == 5) {
                // open login gui
            startLoginConsole();

                  break;

              } else if ((Integer.parseInt(consoleInput) == 6)) {
                System.out.println("Exiting");
                x = false;
                z = false;

              } else if (Integer.parseInt(consoleInput) > 6) {
                System.out.println("Enter a number in the valid range");

              } else {
                System.out.println("Enter a valid input ");
              }
            } else {
              System.out.println("enter a valid integer");
            }
          }

        } else {
          System.out.println("enter a valid range");
        }
      } else {
        System.out.println("enter a valid Integer input");
      }
    }
  }

  public static void displayConsole() {
    System.out.println(
        "\n\n\n"
            + "WESTMINSTER SHOPPING MANAGER CONSOLE"
            + "\n\nselect an option:-"
            + "\n1.Add a PRODUCT"
            + "\n2.Remove a PRODCUT"
            + "\n3.View all PRODUCTS"
            + "\n4.Save all PRODUCTS"
            + "\n5.OPEN CONSOLE"
            + "\n6.Exit");
  }

  public static void AddProductDisplayConsole() {
    System.out.println(
        "\n\n1.Add an Electronic Product " + "\n2.Add a Clothing Product" + "\n3.Exit to main ");
  }

  public static void saveProductsElectronics() {

    try {

      FileWriter file_writer =
          new FileWriter("productsElectronic.txt", true); // creating the file writer object

      PrintWriter pw = new PrintWriter(file_writer);

      for (int i = 0; i < EproductsInSystem.size(); i++) {
        productsInSystem.add(EproductsInSystem.get(i));
        pw.println(EproductsInSystem.get(i).toString());
      }

      EproductsInSystem.clear();

      pw.close();

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void saveproductsCloths() {
    try {

      FileWriter file_writer =
          new FileWriter("productsCLoth.txt", true); // creating the file writer object
      BufferedWriter bf = new BufferedWriter(file_writer);
      PrintWriter pw = new PrintWriter(bf);

      for (int i = 0; i < CproductsInSystem.size(); i++) {
        productsInSystem.add(CproductsInSystem.get(i));
        pw.println(CproductsInSystem.get(i).toString());
      }
      CproductsInSystem.clear();

      pw.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void loadProductsElectrnoic() {

    try {
      Scanner sc = new Scanner(fileElectronic);

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] details = line.split(":");
        String type = details[0];
        String id = details[1];
        String name = details[2];
        int stocks = Integer.parseInt(details[3]);
        int price = Integer.parseInt(details[4]);
        String brand = details[5];
        int period = Integer.parseInt(details[6]);

        Electronics p = new Electronics(type, id, name, brand, stocks, price, period);

        productsInSystem.add(p);
      }

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static void loadProductsCloth() {

    try {
      Scanner sc = new Scanner(fileCloth);

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] details = line.split(":");
        String type = details[0];
        String id = details[1];
        String name = details[2];

        int stocks = Integer.parseInt(details[3]);
        int price = Integer.parseInt(details[4]);
        String color = details[5];
        int size = Integer.parseInt(details[6]);

        Clothing p = new Clothing(type, id, name, stocks, price, color, size);

        productsInSystem.add(p);
      }

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
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

  public static void removeE(String text) {

    try {
      List<String> lines = new ArrayList<>();

      // Read all lines from the file
      try (BufferedReader reader = new BufferedReader(new FileReader(fileElectronic))) {
        String line;
        while ((line = reader.readLine()) != null) {
          if (!line.equals(text)) {
            lines.add(line);
          }
        }
      }

      // Write the updated lines back to the file
      try (FileWriter writer = new FileWriter(fileElectronic)) {
        for (String line : lines) {
          writer.write(line + "\n");
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
      // Handle the exception according to your requirements
    }
  }

  public static void removeC(String text) {

    try {
      List<String> lines = new ArrayList<>();

      // Read all lines from the file
      try (BufferedReader reader = new BufferedReader(new FileReader(fileCloth))) {
        String line;
        while ((line = reader.readLine()) != null) {
          if (!line.equals(text)) {
            lines.add(line);
          }
        }
      }

      // Write the updated lines back to the file
      try (FileWriter writer = new FileWriter(fileCloth)) {
        for (String line : lines) {
          writer.write(line + "\n");
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void startConsole(User user) {


    for (int i = 0; i < productsInSystem.size(); i++) {
      if (productsInSystem.get(i).type.equals("Cloth")) {
        CproductsInSystem.add(productsInSystem.get(i));
      } else if (productsInSystem.get(i).type.equals("Electronic")) {
        EproductsInSystem.add(productsInSystem.get(i));
      }
    }
    SwingUtilities.invokeLater(
        () -> {
          GUI shoppingGUI = new GUI(user);
          shoppingGUI.initializeTable();
          shoppingGUI.updateDisplayedProducts();
          shoppingGUI.updateTable(); // Initialize table with data
          shoppingGUI.setVisible(true);
        });

  }
  public static void startLoginConsole(){
    SwingUtilities.invokeLater(
            () -> {
              LoginGUI login=new LoginGUI();
              login.setVisible(true);

            });
  }
}

