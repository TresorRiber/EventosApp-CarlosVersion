package Windows;

import Main.ConnDB;
import Windows.Admin.AdminWindow;
import Windows.User.UserNotLogedWindow;
import Windows.User.UserWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index extends JFrame {
    // User data
    private Map<String, String> users;
    private static ConnDB connDB;

    // UI components
    private JTextField userField;
    private JPasswordField passField;

    // Panels declaration
    JPanel northPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel upperCenterPanel = new JPanel(new GridLayout(2, 1));
    JPanel middleCenterPanel = new JPanel(new GridBagLayout());
    JPanel lowerCenterPanel = new JPanel();
    JPanel southPanel = new JPanel();

    public Index() {
        // Set JFrame properties
        setTitle("Index");
        setSize(400, 800);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize user data
        users = new HashMap<>();
        users.put("user1", "123456");
        users.put("user2", "123456");
        users.put("admin1", "123456");
        users.put("admin2", "123456");

        connDB=new ConnDB();

        // Add elements to the JFrame
        addElements();
    }

    public void addElements() {
        addPanels();
        addImages();
        addLabels();
        addLoginForm();
        addButtons();
    }

    public void addPanels() {
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialize panels with specific colors and sizes
        northPanel = createPanel(Color.BLACK, 400, 200);
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = createPanel(Color.LIGHT_GRAY, 400, 150);
        middleCenterPanel = createPanel(Color.WHITE, 400, 200);
        lowerCenterPanel = createPanel(Color.WHITE, 400, 100);
        southPanel = createPanel(Color.WHITE, 400, 100);

        // Add panels to the mainPanel
        setContentPane(mainPanel);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Add upper, middle, and lower center panels to the center panel
        centerPanel.add(upperCenterPanel, BorderLayout.NORTH);
        centerPanel.add(middleCenterPanel, BorderLayout.CENTER);
        centerPanel.add(lowerCenterPanel, BorderLayout.SOUTH);
    }

    // Create panels with specific colors and default sizes
    private JPanel createPanel(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    private void addImages() {
        // Load and scale the image
        ImageIcon imageIcon = new ImageIcon("images/Logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        // Create and add the image label to the northPanel
        JLabel logoLabel = new JLabel(scaledImageIcon);
        northPanel.add(logoLabel);
    }

    private void addLabels() {
        // Create and add login label
        JLabel login = new JLabel("Login");
        login.setFont(new Font("Arial", Font.BOLD, 32));
        login.setForeground(Color.black);
        login.setPreferredSize(new Dimension(300, 50));
        login.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(login);

        // Create and add "Sign in to continue" label
        JButton withoutRegistration = new JButton("Without registration");
        withoutRegistration.setBackground(Color.green);
        withoutRegistration.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(withoutRegistration);

        withoutRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserNotLogedWindow user= new UserNotLogedWindow();
                user.setVisible(true);
            }
        });
    }

    public void addLoginForm() {
        // Set layout for the middle center panel
        middleCenterPanel.setLayout(new GridBagLayout());

        // Set constraints for GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Add "USERNAME:" label
        JLabel loginLabel = new JLabel("USERNAME:");
        middleCenterPanel.add(loginLabel, gbc);

        // Add username text field
        gbc.gridy = 1;
        userField = new JTextField(25);
        middleCenterPanel.add(userField, gbc);

        // Add "PASSWORD:" label
        gbc.gridy = 2;
        JLabel passLabel = new JLabel("PASSWORD:");
        middleCenterPanel.add(passLabel, gbc);

        // Add password field
        gbc.gridy = 3;
        passField = new JPasswordField(25);
        middleCenterPanel.add(passField, gbc);
    }

    public void addButtons() {
        // Create login and register buttons
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                // Validate the entered data
                if (users.containsKey(username) && users.get(username).equals(password)) {
                    // Valid data
                    // Redirect to the appropriate page
                    if (username.equals("user1") || username.equals("user2")) {
                        openUserPage();
                    } else if (username.equals("admin1") || username.equals("admin2")) {
                        openAdminPage();
                    } else {
                        showError("User not found");
                    }
                } else {
                    // Show error message if the data is incorrect
                    JOptionPane.showMessageDialog(Index.this, "Incorrect username or password", "Authentication Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add buttons to the panel
        lowerCenterPanel.add(loginButton);
        lowerCenterPanel.add(registerButton);
    }

    // Functions to redirect to the pages according to the user
    private void openUserPage() {
        UserWindow userWindow = new UserWindow();
        userWindow.setVisible(true);
        dispose();
    }

    private void openAdminPage() {
        AdminWindow adminWindow = new AdminWindow();
        adminWindow.setVisible(true);
        dispose();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(Index.this, message, "Authentication Error", JOptionPane.ERROR_MESSAGE);
    }
}
