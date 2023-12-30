import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class GUI extends JFrame {
    private JComboBox<String> productTypeDropdown;
    private JTable productTable;
    private JTextArea productDetailsTextArea;
    private JButton addToCartButton;
    private JButton viewShoppingCartButton;

    private DefaultTableModel tableModel;
    private ArrayList<Product> displayedProducts;
    private ShoppingCart shoppingCart;
    private JPanel productDetailsPanel;

    public GUI() {
        setTitle("Shopping GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        productTypeDropdown = new JComboBox<>(new String[]{"All", "Electronics", "Clothes"});
        productTable = new JTable();
        productDetailsTextArea = new JTextArea();
        addToCartButton = new JButton("Add to Cart");
        viewShoppingCartButton = new JButton("Shopping Cart");

        // Set layout
        setLayout(new BorderLayout());

        // Panel for product type dropdown and labels
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel productTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        productTypePanel.add(new JLabel("Select Product Type:"));
        productTypePanel.add(productTypeDropdown);

        topPanel.add(productTypePanel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(viewShoppingCartButton);

        topPanel.add(buttonPanel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.NORTH);

        // Panel for Add to Cart button at the bottom center
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(addToCartButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Initialize table model
        tableModel = new DefaultTableModel();
        productTable.setModel(tableModel);

        // Set up table header
        tableModel.addColumn("Type");
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Stocks Available");
        tableModel.addColumn("Price");
        tableModel.addColumn("Reduced Availability");

        // Set up table
        JScrollPane tableScrollPane = new JScrollPane(productTable);
        tableScrollPane.setPreferredSize(new Dimension(getWidth(), 150)); // Adjust the height as needed
        topPanel.add(tableScrollPane, BorderLayout.SOUTH);

        // Initialize the product details panel
        productDetailsPanel = new JPanel(new BorderLayout());
        productDetailsPanel.setBorder(BorderFactory.createTitledBorder("Product Details"));
        productDetailsTextArea.setEditable(false);
        JScrollPane productDetailsScrollPane = new JScrollPane(productDetailsTextArea);
        productDetailsPanel.add(productDetailsScrollPane, BorderLayout.CENTER);

        // Set the preferred size of the product details panel
        productDetailsPanel.setPreferredSize(new Dimension(getWidth(), 100)); // Adjust the height as needed

        // Create a Box container for the product details panel
        Box box = Box.createVerticalBox();
        box.add(productDetailsPanel);

        // Add the Box container above the Add to Cart button
        add(box, BorderLayout.CENTER);

        // Add event listeners
        productTypeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = productTypeDropdown.getSelectedItem().toString();

                if ("All".equals(selectedType)) {
                    updateDisplayedProducts();
                } else if ("Electronics".equals(selectedType)) {
                    updateDisplayedProductsByType(selectedType);
                } else if ("Clothes".equals(selectedType)) {
                    updateDisplayedProductsByType(selectedType);
                }

                updateTable();
            }
        });
        productTable.getSelectionModel().addListSelectionListener(e -> displaySelectedProductDetails());

        addToCartButton.addActionListener(e -> addToShoppingCart());
        viewShoppingCartButton.addActionListener(e -> showShoppingCartDialog());

        // Initialize shopping cart
        shoppingCart = new ShoppingCart();
    }

    //display products
    void updateDisplayedProducts() {
        String selectedType = productTypeDropdown.getSelectedItem().toString();

        if ("All".equals(selectedType)) {
            displayedProducts = new ArrayList<>(Main.productsInSystem);
        } else if ("Electronics".equals(selectedType)) {
            displayedProducts = new ArrayList<>(Main.EproductsInSystem);
        } else if ("Clothes".equals(selectedType)) {
            displayedProducts = new ArrayList<>(Main.CproductsInSystem);
        }

        // Sort products alphabetically
        displayedProducts.sort(Comparator.comparing(Product::getProductName));

        updateTable();
    }

    void updateTable() {
        tableModel.setRowCount(0); // Clear existing rows

        for (Product product : displayedProducts) {
            Object[] rowData = {
                    product.getType(),
                    product.getProductId(),
                    product.getProductName(),
                    product.getStocksAvailable(),
                    product.getProductPrice(),
                    product.getStocksAvailable() < 3
            };

            tableModel.addRow(rowData);
        }

        // Add cell renderer to color rows with reduced availability in red
        productTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (tableModel.getValueAt(row, 4).equals(true)) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(table.getForeground());
                }

                return c;
            }
        });

        productTable.repaint();
    }

    private void updateDisplayedProductsByType(String selectedType) {
        if ("All".equals(selectedType)) {
            displayedProducts = new ArrayList<>(Main.productsInSystem);
        } else if ("Electronics".equals(selectedType)) {
            displayedProducts = new ArrayList<>(Main.EproductsInSystem);
        } else if ("Clothes".equals(selectedType)) {
            displayedProducts = new ArrayList<>(Main.CproductsInSystem);
        }

        // Sort products alphabetically
        displayedProducts.sort(Comparator.comparing(Product::getProductName));
        updateTable();
    }

    void initializeTable() {
        // Initialize table model
        tableModel = new DefaultTableModel();
        productTable.setModel(tableModel);

        // Set up table header
        tableModel.addColumn("Type");
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Stocks Available");
        tableModel.addColumn("Price");
        tableModel.addColumn("Reduced Availability");
    }

    private void displaySelectedProductDetails() {
        int selectedRow = productTable.getSelectedRow();

        if (selectedRow >= 0 && selectedRow < displayedProducts.size()) {
            Product selectedProduct = displayedProducts.get(selectedRow);


            productDetailsTextArea.setText(selectedProduct.productDetails());
        }
    }

    private void addToShoppingCart() {
        int selectedRow = productTable.getSelectedRow();

        if (selectedRow >= 0 && selectedRow < displayedProducts.size()) {
            Product selectedProduct = displayedProducts.get(selectedRow);
            shoppingCart.addItem(selectedProduct);
            JOptionPane.showMessageDialog(this, "Product added to the shopping cart.");

            // Update the shopping cart button text to reflect the number of items in the cart
            updateShoppingCartButtonText();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product.");
        }
    }


    private void showShoppingCartDialog() {
        // Create a new JFrame for the shopping cart
        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setSize(400, 300);

        // Create a JPanel for the shopping cart content
        JPanel cartPanel = new JPanel(new BorderLayout());

        // Create a JTable to display the shopping cart items
        JTable cartTable = new JTable();
        DefaultTableModel cartTableModel = new DefaultTableModel();
        cartTable.setModel(cartTableModel);

        // Set up table header
        cartTableModel.addColumn("Product ID");
        cartTableModel.addColumn("Product Name");
        cartTableModel.addColumn("Price");

        // Populate the table with shopping cart items
        for (Product product : shoppingCart.getCartItems()) {
            Object[] rowData = {
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductPrice()
            };
            cartTableModel.addRow(rowData);
        }

        JScrollPane cartTableScrollPane = new JScrollPane(cartTable);
        cartPanel.add(cartTableScrollPane, BorderLayout.CENTER);

    // Display the final price
    JTextField finalPriceLabel =
        new JTextField("Final Price: $" + shoppingCart.calculateTotalPrice());
        cartPanel.add(finalPriceLabel,  BorderLayout.PAGE_END);

        // Create a JButton to close the shopping cart dialog
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartFrame.dispose();
            }
        });

        cartPanel.add(closeButton, BorderLayout.SOUTH);

        // Add the cartPanel to the cartFrame
        cartFrame.add(cartPanel);

        // Set the visibility of the shopping cart dialog
        cartFrame.setVisible(true);
    }


    private void updateShoppingCartButtonText() {
        // Update the text of the shopping cart button to show the number of items in the cart
        viewShoppingCartButton.setText("Shopping Cart (" + shoppingCart.getCartItems().size() + ")");
    }
}
