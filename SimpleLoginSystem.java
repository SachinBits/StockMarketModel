package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleLoginSystem {

    // Pre-defined username and password
    private static final String PREDEFINED_USERNAME = "Joanna";
    private static final String PREDEFINED_PASSWORD = "Password123";

    public static void main(String[] args) {
        // Open the login window
        SwingUtilities.invokeLater(SimpleLoginSystem::createLoginWindow);
    }

    public static void createLoginWindow() {
        // Create the main login frame
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username and Password fields
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        // Action Listener for Login Button
        loginButton.addActionListener(e -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = new String(passwordField.getPassword());

            // Check if the entered credentials match the pre-defined ones
            if (enteredUsername.equals(PREDEFINED_USERNAME) && enteredPassword.equals(PREDEFINED_PASSWORD)) {


                JOptionPane.showMessageDialog(loginFrame, "Correct Password!");
                loginFrame.dispose();
                Stocks start=new Stocks();  
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Wrong Username/Password.");
            }
        });

        // Arrange components
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty space
        panel.add(loginButton);

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }
}
