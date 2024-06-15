package Windows.Admin.Refactored;

import Windows.Admin.CreateEvents;
import Windows.Admin.LogisticsManagement;
import Windows.Admin.UserManagement;
import Windows.Index;
import Windows.User.Refactored.PanelUtils;

import javax.swing.*;
import java.awt.*;

public class AdminWindowRefactored extends JFrame {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 800;
    private static final int PANEL_WIDTH = 400;
    private static final int NORTH_PANEL_HEIGHT = 200;
    private static final int UPPER_CENTER_PANEL_HEIGHT = 100;
    private static final int MIDDLE_CENTER_PANEL_HEIGHT = 100;
    private static final int LOWER_CENTER_PANEL_HEIGHT = 100;
    private static final int SOUTH_PANEL_HEIGHT = 100;

    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel upperCenterPanel;
    private JPanel middleCenterPanel;
    private JPanel lowerCenterPanel;
    private JPanel southPanel;

    public AdminWindowRefactored() {
        setTitle("Admin Registered");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initializePanels();
        addElements();
    }

    private void initializePanels() {
        northPanel = PanelUtils.createPanel(Color.BLACK, PANEL_WIDTH, NORTH_PANEL_HEIGHT);
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = PanelUtils.createPanel(Color.LIGHT_GRAY, PANEL_WIDTH, UPPER_CENTER_PANEL_HEIGHT);
        middleCenterPanel = PanelUtils.createPanel(Color.WHITE, PANEL_WIDTH, MIDDLE_CENTER_PANEL_HEIGHT);
        lowerCenterPanel = PanelUtils.createPanel(Color.WHITE, PANEL_WIDTH, LOWER_CENTER_PANEL_HEIGHT);
        southPanel = PanelUtils.createPanel(Color.WHITE, PANEL_WIDTH, SOUTH_PANEL_HEIGHT);

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        centerPanel.add(upperCenterPanel, BorderLayout.NORTH);
        centerPanel.add(middleCenterPanel, BorderLayout.CENTER);
        centerPanel.add(lowerCenterPanel, BorderLayout.SOUTH);
    }

    private void addElements() {
        addImage();
        addWelcomeLabel();
        addButtons();
    }

    private void addImage() {
        ImageIcon imageIcon = new ImageIcon("images/Logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledImageIcon);
        northPanel.add(logoLabel);
    }

    private void addWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("Welcome to your Admin page!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(welcomeLabel, BorderLayout.NORTH);
    }

    private void addButtons() {
        Dimension buttonSize = new Dimension(300, 50);

        JButton createEventsButton = createButton("Create Events", buttonSize, e -> {
            dispose();
            new CreateEvents().setVisible(true);
        });
        JButton logisticManagementButton = createButton("Logistic Management", buttonSize, e -> {
            dispose();
            new LogisticsManagement().setVisible(true);
        });
        JButton userManagementButton = createButton("User Management", buttonSize, e -> {
            dispose();
            new UserManagement().setVisible(true);
        });

        middleCenterPanel.setLayout(new GridLayout(3, 1));
        middleCenterPanel.add(PanelUtils.createCenteredPanel(createEventsButton, buttonSize));
        middleCenterPanel.add(PanelUtils.createCenteredPanel(logisticManagementButton, buttonSize));
        middleCenterPanel.add(PanelUtils.createCenteredPanel(userManagementButton, buttonSize));

        JButton logoutButton = createButton("Logout", buttonSize, e -> {
            dispose();
            new Index().setVisible(true);
        });
        southPanel.add(PanelUtils.createCenteredPanel(logoutButton, buttonSize), BorderLayout.CENTER);
    }

    private JButton createButton(String text, Dimension preferredSize, java.awt.event.ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setPreferredSize(preferredSize);
        return button;
    }
}
