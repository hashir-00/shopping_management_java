import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class LoginGUI extends JFrame {
    private JPanel errorMsgPanel;
    public LoginGUI() {
        setTitle("Login");
        setSize(250, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Added some spacing between components

        JPanel loginPanel = new JPanel(new GridLayout(2, 1));
        JPanel errorMsgPanel=new JPanel(new GridLayout(1,2));
        loginPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Added padding
        errorMsgPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Added padding


        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(username);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(password);

        add(loginPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();


        add(buttonPanel, BorderLayout.SOUTH);
        JButton loginButton = new JButton("Login");
        buttonPanel.add(loginButton);
    loginButton.addActionListener(
        e -> {
          String enteredUsername = username.getText();
          char[] enteredPassword = password.getPassword();
          User user = new User(enteredUsername, new String(enteredPassword));
          if (user.checkUsername_password(
              enteredUsername.toUpperCase(), new String(enteredPassword))) {
            Main.startConsole(user);
              dispose();
          } else {
              showErrorMessage("Sorry, your username or password is incorrect");
          }
        });
    }
    private void showErrorMessage(String message) {
        if (errorMsgPanel == null) {
            errorMsgPanel = new JPanel(new BorderLayout());
            errorMsgPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            add(errorMsgPanel, BorderLayout.NORTH);
        } else {
            errorMsgPanel.removeAll(); // Clear previous error messages
        }

        JLabel errorLabel = new JLabel(message);
        errorMsgPanel.add(errorLabel, BorderLayout.CENTER);
        revalidate();
    }

}