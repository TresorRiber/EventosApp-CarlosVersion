package Windows.User;

import Windows.Index;
import Windows.User.Refactored.BuyTicketsRefactored;

import javax.swing.*;
import java.awt.*;

public class UserWindow extends JFrame {
    // Panels declaration
    JPanel northPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel upperCenterPanel = new JPanel();
    JPanel middleCenterPanel = new JPanel();
    JPanel lowerCenterPanel = new JPanel();
    JPanel southPanel = new JPanel();

    public UserWindow() {
        // Set JFrame properties
        setTitle("User Registered");
        setSize(400, 800);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add elements to the JFrame
        addElements();
    }

    public void addElements() {
        addPanels();
        addImages();
        addLabels();
        addButtons();
    }

    public void addPanels() {
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialize panels with specific colors and sizes
        northPanel = addCreatePanel(Color.BLACK, 400, 200);
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = addCreatePanel(Color.LIGHT_GRAY, 400, 100);
        middleCenterPanel = addCreatePanel(Color.WHITE, 400, 100);
        lowerCenterPanel = addCreatePanel(Color.WHITE, 400, 100);
        southPanel = addCreatePanel(Color.WHITE, 400, 100);

        // Add panels to the mainPanel
        setContentPane(mainPanel);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Add upper and lower center panels to the center panel
        centerPanel.add(upperCenterPanel, BorderLayout.NORTH);
        centerPanel.add(middleCenterPanel, BorderLayout.CENTER);
        centerPanel.add(lowerCenterPanel, BorderLayout.SOUTH);
    }

    // To create panels with specific colors and default sizes
    private JPanel addCreatePanel(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    // Load and scale the image
    private void addImages() {
        ImageIcon imageIcon = new ImageIcon("images/Logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        // Create and add the image label to the northPanel
        JLabel logoLabel = new JLabel(scaledImageIcon);
        northPanel.add(logoLabel);
    }

    // Add labels
    private void addLabels() {
        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to your user page!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(welcomeLabel, BorderLayout.NORTH);
    }

    // Add buttons
    private void addButtons() {
        JButton btn1 = new JButton("Buy tickets");
        JButton btn2 = new JButton("Comments");
        JButton btn3 = new JButton("Surveys");

        Dimension btnP = new Dimension(300, 50);
        btn1.setPreferredSize(btnP);
        btn2.setPreferredSize(btnP);
        btn3.setPreferredSize(btnP);

        // Create panels for buttons with center alignment
        JPanel btn1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btn1Panel.add(btn1);

        JPanel btn2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btn2Panel.add(btn2);

        JPanel btn3Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btn3Panel.add(btn3);

        // Add button panels to the middleCenterPanel
        middleCenterPanel.setLayout(new GridLayout(3, 1));
        middleCenterPanel.add(btn1Panel);
        middleCenterPanel.add(btn2Panel);
        middleCenterPanel.add(btn3Panel);

        // Close session button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            Index index = new Index();
            index.setVisible(true);
        });

        // btn1 open BuyTickets window
        btn1.addActionListener(e -> {
            dispose();
            BuyTicketsRefactored buyTicket = new BuyTicketsRefactored();
            buyTicket.setVisible(true);
        });

        // btn2 open MakeComments window
        btn2.addActionListener(e -> {
            dispose();
            MakeComments makeComments = new MakeComments();
            makeComments.setVisible(true);
        });

        // btn3 open DoSurveys window
        btn3.addActionListener(e -> {
            dispose();
            DoSurveys doSurveys = new DoSurveys(1);
        });

        // Panel for the logout button
        JPanel logoutButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoutButtonPanel.add(logoutButton);
        southPanel.add(logoutButtonPanel, BorderLayout.CENTER);
    }
}
