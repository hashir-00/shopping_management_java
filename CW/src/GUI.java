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

        // Add components to the frame
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Select Product Type:"));
        topPanel.add(productTypeDropdown);
        topPanel.add(addToCartButton);
        topPanel.add(viewShoppingCartButton);
        add(topPanel, BorderLayout.NORTH);

        // Initialize table model
        tableModel = new DefaultTableModel();
        productTable.setModel(tableModel);

        // Set up table header
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Stocks Available");
        tableModel.addColumn("Price");
        tableModel.addColumn("Reduced Availability");

        // Set up table
        JScrollPane tableScrollPane = new JScrollPane(productTable);
        add(tableScrollPane, BorderLayout.CENTER);

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

        viewShoppingCartButton.addActionListener(e -> showShoppingCart());

        // Initialize shopping cart
        shoppingCart = new ShoppingCart();
    }

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

        updateTable(); // Add this line to update the table after changing the displayed products
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

    // ... (existing code)




    private void displaySelectedProductDetails() {
        int selectedRow = productTable.getSelectedRow();

        if (selectedRow >= 0 && selectedRow < displayedProducts.size()) {
            Product selectedProduct = displayedProducts.get(selectedRow);
            productDetailsTextArea.setText(selectedProduct.toString());
        }
    }

    private void addToShoppingCart() {
        int selectedRow = productTable.getSelectedRow();

        if (selectedRow >= 0 && selectedRow < displayedProducts.size()) {
            Product selectedProduct = displayedProducts.get(selectedRow);
            shoppingCart.addItem(selectedProduct);
            JOptionPane.showMessageDialog(this, "Product added to the shopping cart.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product.");
        }
    }

    private void showShoppingCart() {
        JOptionPane.showMessageDialog(this, "Shopping Cart:\n" + shoppingCart.toString());
    }


}
