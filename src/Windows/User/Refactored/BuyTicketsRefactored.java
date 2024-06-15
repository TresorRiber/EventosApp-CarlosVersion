package Windows.User.Refactored;

import Main.ConnDB;
import Windows.Index;
import Windows.User.UserWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class BuyTicketsRefactored extends JFrame {
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

    private JComboBox<String> select;

    private ConnDB connDB;

    public BuyTicketsRefactored() {
        setTitle("Buy tickets");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        connDB = new ConnDB();

        addElements();
    }

    private void addElements() {
        initializePanels();
        addPanelsToFrame();
        addImages();
        addLabels();
        addButtons();
        addEventSelectionComboBox();
    }

    private void initializePanels() {
        northPanel = createPanel(Color.BLACK, NORTH_PANEL_HEIGHT);
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = createPanel(Color.LIGHT_GRAY, UPPER_CENTER_PANEL_HEIGHT);
        middleCenterPanel = createPanel(Color.WHITE, MIDDLE_CENTER_PANEL_HEIGHT);
        lowerCenterPanel = createPanel(Color.WHITE, LOWER_CENTER_PANEL_HEIGHT);
        southPanel = createPanel(Color.WHITE, SOUTH_PANEL_HEIGHT);
    }

    private void addPanelsToFrame() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        centerPanel.add(upperCenterPanel, BorderLayout.NORTH);
        centerPanel.add(middleCenterPanel, BorderLayout.CENTER);
        centerPanel.add(lowerCenterPanel, BorderLayout.SOUTH);
    }

    private JPanel createPanel(Color color, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, height));
        return panel;
    }

    private void addImages() {
        ImageIcon imageIcon = new ImageIcon("images/Logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        JLabel logoLabel = new JLabel(scaledImageIcon);
        northPanel.add(logoLabel);
    }

    private void addLabels() {
        JLabel titleLabel = new JLabel("Buy tickets");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(titleLabel, BorderLayout.NORTH);
    }

    private void addButtons() {
        JButton logoutButton = createButton("Logout", e -> {
            dispose();
            new Index().setVisible(true);
        });
        southPanel.add(logoutButton, BorderLayout.CENTER);

        JButton buyButton = createButton("Buy now", e -> showEventDetails());
        lowerCenterPanel.add(buyButton);

        JButton returnButton = createButton("Return", e -> {
            dispose();
            new UserWindow().setVisible(true);
        });
        southPanel.add(returnButton);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    private void addEventSelectionComboBox() {
        List<String> eventNames = connDB.getEventNames();
        select = new JComboBox<>(eventNames.toArray(new String[0]));
        middleCenterPanel.add(select);

        JTextArea eventInfoTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(eventInfoTextArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        middleCenterPanel.add(scrollPane);

        select.addActionListener(e -> updateEventInfo(eventInfoTextArea));
    }

    private void updateEventInfo(JTextArea textArea) {
        String selectedEvent = (String) select.getSelectedItem();
        if (selectedEvent != null) {
            List<String> eventInfo = connDB.getEventInfo(selectedEvent);
            textArea.setText(String.join("\n", eventInfo));
        }
    }

    private void showEventDetails() {
        String selectedEvent = (String) select.getSelectedItem();
        if (selectedEvent != null) {
            List<String> eventInfo = connDB.getEventInfo(selectedEvent);
            String message = String.join("\n", eventInfo);
            JOptionPane.showMessageDialog(this, message, "Event Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
