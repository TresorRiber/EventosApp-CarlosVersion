package Windows.User.Refactored;

import Main.ConnDB;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MakeCommentsRefactored extends JFrame {
    private JTextField commentsField;
    private JTextField idUsuarioField;
    private JTextField idEventoField;
    private JTextField ratingField;
    private JTextArea commentsHistory;
    private final List<String> commentsList;
    private final ConnDB connDB;
    private final JPanel northPanel;
    private final JPanel centerPanel;
    private final JPanel upperCenterPanel;
    private final JPanel middleCenterPanel;
    private final JPanel lowerCenterPanel;
    private final JPanel southPanel;

    public MakeCommentsRefactored() {
        // Set JFrame properties
        setTitle("Make Comments");
        setSize(400, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        connDB = new ConnDB();

        commentsList = new ArrayList<>();

        // Initialize panels
        northPanel = createPanel(Color.BLACK, new Dimension(400, 200));
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = createPanel(Color.LIGHT_GRAY, new Dimension(400, 90));
        middleCenterPanel = createPanel(Color.WHITE, new Dimension(400, 160));
        lowerCenterPanel = createPanel(Color.WHITE, new Dimension(400, 160));
        southPanel = createPanel(Color.WHITE, new Dimension(400, 150));

        // Add panels to the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Add upper and lower center panels to the center panel
        centerPanel.add(upperCenterPanel, BorderLayout.NORTH);
        centerPanel.add(middleCenterPanel, BorderLayout.CENTER);
        centerPanel.add(lowerCenterPanel, BorderLayout.SOUTH);

        // Add components to panels
        addImages();
        addLabels();
        addWestSection(middleCenterPanel);
        addCenterSection(lowerCenterPanel);
        addSouthSection(southPanel);

        setVisible(true);
    }

    private JPanel createPanel(Color color, Dimension dimension) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(dimension);
        return panel;
    }

    private void addSouthSection(JPanel south) {
        // Button to save comments
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveComment());
        south.add(saveButton);

        // Button to clear comment history
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            clearHistory();
            clearFields();
        });
        south.add(clearButton);

        // Button to go back to previous page
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> goToPreviousWindow());
        south.add(backButton);
    }

    private void addWestSection(JPanel westPanel) {
        JPanel gridPanel = new JPanel(new GridLayout(4, 1, 4, 4));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        gridPanel.add(new JLabel("Leave your comments:"));
        commentsField = new JTextField();
        gridPanel.add(commentsField);

        gridPanel.add(new JLabel("Insert User Id:"));
        idUsuarioField = new JTextField();
        gridPanel.add(idUsuarioField);

        gridPanel.add(new JLabel("Insert Event Id:"));
        idEventoField = new JTextField();
        gridPanel.add(idEventoField);

        gridPanel.add(new JLabel("Rating (0 & 10):"));
        ratingField = new JTextField();
        gridPanel.add(ratingField);

        westPanel.add(gridPanel, BorderLayout.NORTH);
    }

    private void addCenterSection(JPanel center) {
        commentsHistory = new JTextArea(8, 30);
        commentsHistory.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(commentsHistory);
        center.add(scrollPane);
    }

    private void saveComment() {
        String comment = commentsField.getText().trim();
        String idUsuarioText = idUsuarioField.getText().trim();
        String idEventoText = idEventoField.getText().trim();
        String ratingText = ratingField.getText().trim();
        if (comment.isEmpty() || idUsuarioText.isEmpty() || idEventoText.isEmpty() || ratingText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, fill all the gaps.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idUsuario = Integer.parseInt(idUsuarioText);
            int idEvento = Integer.parseInt(idEventoText);
            int rating = Integer.parseInt(ratingText);

            // Verificar si la conexión a la base de datos y la inserción son exitosas
            boolean success = connDB.insertComment(comment, idUsuario, idEvento, rating);
            if (success) {
                commentsList.add(comment);
                updateHistory();
                JOptionPane.showMessageDialog(this, "Comment inserted correctly", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error saving comment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "User ID, Event ID, and Rating must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        JLabel label1 = new JLabel("Make Comments");
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(label1, BorderLayout.NORTH);
    }

    private void updateHistory() {
        commentsHistory.setText("");
        for (String comment : commentsList) {
            commentsHistory.append(comment + "\n");
        }
    }

    private void clearFields() {
        commentsField.setText("");
        idUsuarioField.setText("");
        idEventoField.setText("");
        ratingField.setText("");
    }

    private void clearHistory() {
        commentsList.clear();
        commentsHistory.setText("");
    }

    private void goToPreviousWindow() {
        UserWindowRefactored previousWindow = new UserWindowRefactored();
        previousWindow.setVisible(true);
        dispose();
    }
}
