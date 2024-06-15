package Windows.User.Refactored;

import Windows.Index;
import Windows.User.BuyTickets;
import Windows.User.DoSurveys;
import Windows.User.MakeComments;

import javax.swing.*;
import java.awt.*;

public class UserWindowRefactored extends JFrame {
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

    public UserWindowRefactored() {
        setTitle("User Registered");
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
        JLabel welcomeLabel = new JLabel("Welcome to your user page!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(welcomeLabel, BorderLayout.NORTH);
    }

    private void addButtons() {
        Dimension buttonSize = new Dimension(300, 50);

        JButton buyTicketsButton = createButton("Buy Tickets", buttonSize, e -> {
            dispose();
            new BuyTickets().setVisible(true);
        });
        JButton commentsButton = createButton("Comments", buttonSize, e -> {
            dispose();
            new MakeComments().setVisible(true);
        });
        JButton surveysButton = createButton("Surveys", buttonSize, e -> {
            dispose();
            new DoSurveys(1).setVisible(true);
        });

        middleCenterPanel.setLayout(new GridLayout(3, 1));
        middleCenterPanel.add(PanelUtils.createCenteredPanel(buyTicketsButton, buttonSize));
        middleCenterPanel.add(PanelUtils.createCenteredPanel(commentsButton, buttonSize));
        middleCenterPanel.add(PanelUtils.createCenteredPanel(surveysButton, buttonSize));

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
