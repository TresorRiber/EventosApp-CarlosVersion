package Windows.User;

import Main.ConnDB;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MakeComments extends JFrame {
    private JTextField commentsField;
    private JTextField idUsuarioField;
    private JTextField idEventoField;
    private JTextField ratingField;
    private JTextArea commentsHistory;
    private final List<String> commentsList;
    private ConnDB connDB;
    private  JPanel northPanel;
    private  JPanel centerPanel;
    private  JPanel upperCenterPanel;
    private  JPanel middleCenterPanel;
    private  JPanel lowerCenterPanel;
    private  JPanel southPanel;

    public MakeComments() {
        // Set JFrame properties
        setTitle("Make comments");
        setSize(400, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        connDB = new ConnDB();

        commentsList = new ArrayList<>();


        northPanel = new JPanel();
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = new JPanel(new BorderLayout());
        middleCenterPanel = new JPanel(new GridLayout(3, 1));
        lowerCenterPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialize panels with specific colors and sizes
        northPanel = addCreatePanel(Color.BLACK, 400, 200);
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = addCreatePanel(Color.LIGHT_GRAY, 400, 90);
        middleCenterPanel = addCreatePanel(Color.WHITE, 400, 160);
        lowerCenterPanel = addCreatePanel(Color.WHITE, 400, 160);
        southPanel = addCreatePanel(Color.WHITE, 400, 150);

        // Add panels to the mainPanel
        setContentPane(mainPanel);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Add upper and lower center panels to the center panel
        centerPanel.add(upperCenterPanel, BorderLayout.NORTH);
        centerPanel.add(middleCenterPanel, BorderLayout.CENTER);
        centerPanel.add(lowerCenterPanel, BorderLayout.SOUTH);

        addImages();
        addLabels();
        // West panel
        JPanel westPanel = middleCenterPanel;
        addWestSection(westPanel);


        // Center panel
        JPanel center = lowerCenterPanel;
        addCenterSection(center);

        // South panel
        JPanel south = southPanel;
        addSouthSection(south);

        setVisible(true);
    }
    private JPanel addCreatePanel(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }


    private void addSouthSection(JPanel south) {
        // Button to save comments
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveComment();
            }
        });
        southPanel.add(saveButton);

        // Button to clear comment history
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearHistory();
                clearFields();
            }
        });
        southPanel.add(clearButton);

        // Button to go back to previous page
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToPreviousWindow();
            }
        });
        southPanel.add(backButton);
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
        commentsHistory = new JTextArea(8,30);
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
                JOptionPane.showMessageDialog(this, "Error al guardar el comentario.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Comment insert correctly");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de usuario, ID de evento y calificación deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void addImages() {
        ImageIcon imageIcon = new ImageIcon("images/Logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        // Create and add the image label to the northPanel
        JLabel logoLabel = new JLabel(scaledImageIcon);
        northPanel.add(logoLabel);
    }
    private void addLabels() {
        // Welcome label
        JLabel label1 = new JLabel("Make comments");
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(label1, BorderLayout.NORTH);
    }

    private void updateHistory() {
        String history = commentsField.getText();
        commentsHistory.setText(history);
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
        UserWindow previousWindow = new UserWindow();
        previousWindow.setVisible(true);
        dispose();
    }

}
