import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public AuthenticationGUI() {
        super("Authentication");

        // Create components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        messageLabel = new JLabel();

        // Set layout using JPanel
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        add(panel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);

        // Set button actions
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (Authentication.authenticateUser(username, password)) {
                    messageLabel.setText("Login successful!");
                } else {
                    messageLabel.setText("Invalid credentials. Please try again.");
                }

                // Clear password field after use
                passwordField.setText("");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (username.isEmpty() || password.isEmpty()) {
                    messageLabel.setText("Please enter both username and password.");
                    return;
                }

                Authentication.registerUser(username, password);
                messageLabel.setText("User registered successfully!");

                // Clear fields after registration
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        // Set frame properties
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AuthenticationGUI();
            }
        });
    }
}
