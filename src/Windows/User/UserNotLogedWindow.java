package Windows.User;

import Main.ConnDB;
import Windows.Index;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserNotLogedWindow extends JFrame {
    JPanel northPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel upperCenterPanel = new JPanel();
    JPanel middleCenterPanel = new JPanel();
    JPanel lowerCenterPanel = new JPanel();
    JPanel southPanel = new JPanel();

    // JComboBox declaration
    JComboBox<String> select;

    // ConnDB instance
    ConnDB connDB;

    // Constructor
    public UserNotLogedWindow() {
        // Set JFrame properties
        setTitle("User Not Loged");
        setSize(400, 800);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize ConnDB
        connDB = new ConnDB();

        // Add elements to the JFrame
        addElements();
    }

    // Method to add elements to the JFrame
    public void addElements() {
        addPanels();
        addImages();
        addLabels();
        addButtons();
        addJComboBox();
    }

    // Method to add panels
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

    // Method to create panels with specific colors and default sizes
    private JPanel addCreatePanel(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    // Method to load and scale the image
    private void addImages() {
        ImageIcon imageIcon = new ImageIcon("images/Logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        // Create and add the image label to the northPanel
        JLabel logoLabel = new JLabel(scaledImageIcon);
        northPanel.add(logoLabel);
    }

    // Method to add labels
    private void addLabels() {
        // Welcome label
        JLabel label1 = new JLabel("User Not Loged");
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(label1, BorderLayout.NORTH);
    }

    // Method to add buttons
    private void addButtons() {
        // Close session button
        JButton logoutButton = new JButton("Back");
        logoutButton.addActionListener(e -> {
            dispose();
            Index index = new Index();
            index.setVisible(true);
        });

        southPanel.add(logoutButton, BorderLayout.CENTER);


    }

    // Method to add JComboBox
    public void addJComboBox() {
        List<String> eventNames = connDB.getEventNames(); // Get event names

        select = new JComboBox<>(eventNames.toArray(new String[0]));
        middleCenterPanel.add(select);

        JTextArea jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);

        select.addActionListener(e -> {
            String selectedEvent = (String) select.getSelectedItem();
            if (selectedEvent != null) {
                // Call the getEventInfo() method with the selected event name
                List<String> eventInfo = connDB.getEventInfo(selectedEvent);
                jTextArea.setText(""); // Clear the text area before adding new information
                for (String info : eventInfo) {
                    jTextArea.append(info + "\n");
                }
            }
        });

        // Set the preferred size of the JScrollPane
        jScrollPane.setPreferredSize(new Dimension(300, 200));
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        middleCenterPanel.add(jScrollPane);
    }


}
