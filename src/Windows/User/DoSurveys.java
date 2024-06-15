package Windows.User;

import Classes.Encuesta;
import Classes.Usuario;
import Main.ConnDB;
import Windows.Index;
import com.mysql.jdbc.PreparedStatement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;



public class DoSurveys extends JFrame {
    // Panels declaration
    private final JPanel northPanel;
    private final JPanel centerPanel;
    private final JPanel upperCenterPanel;
    private final JPanel middleCenterPanel;
    private final JPanel lowerCenterPanel;
    private final JPanel southPanel;

    // List of surveys
    private final List<Encuesta> encuestas;
    private final List<JPanel> surveyPanels;
    private final int userId;
    ConnDB connDB;

    public DoSurveys(int userId) {
        this.userId=userId;
        // Set JFrame properties
        setTitle("Do surveys");
        setSize(400, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        connDB = new ConnDB();

        // Initialize panels
        northPanel = new JPanel();
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = new JPanel(new BorderLayout());
        middleCenterPanel = new JPanel(new GridLayout(3, 1));
        lowerCenterPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Initialize list of surveys
        encuestas = new ArrayList<>();
        surveyPanels = new ArrayList<>();
        // Add sample surveys
        encuestas.add(new Encuesta(1, "2024-05-20", "2024-05-01", "Excelente, Bien, Regular, Mal", "¿Qué te pareció el evento?"));
        encuestas.add(new Encuesta(2, "2024-05-22", "2024-05-03", "Sí, No", "¿Te gustaría participar en más eventos similares?"));
        encuestas.add(new Encuesta(3, "2024-05-24", "2024-05-05", "Conciertos, Talleres, Exposiciones, Deportes", "¿Qué actividades te gustaría ver en futuros eventos?"));


        // Add elements to the JFrame
        addElements();

        setVisible(true);
    }

    private void addElements() {
        addPanels();
        addImages();
        addLabels();
        addButtons();
        addSurveys();
    }

    private void addPanels() {
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialize panels with specific colors and sizes
        northPanel.setBackground(Color.BLACK);
        northPanel.setPreferredSize(new Dimension(400, 200));

        upperCenterPanel.setBackground(Color.LIGHT_GRAY);
        upperCenterPanel.setPreferredSize(new Dimension(400, 100));

        middleCenterPanel.setBackground(Color.WHITE);

        lowerCenterPanel.setBackground(Color.LIGHT_GRAY);
        lowerCenterPanel.setPreferredSize(new Dimension(400, 100));

        southPanel.setBackground(Color.WHITE);
        southPanel.setPreferredSize(new Dimension(400, 100));

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
        JLabel welcomeLabel = new JLabel("Do Surveys");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(welcomeLabel, BorderLayout.NORTH);
    }

    // Add buttons
    private void addButtons() {
        // Do survey button
        JButton surveyButton = new JButton("Submit Survey");
        surveyButton.addActionListener(e -> {
            submitSurveys();
        });

        // Panel for the logout button
        southPanel.add(surveyButton);

        // Button to return to UserWindow page
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> {
            // Close the current window
            dispose();
            // Open the WindowsUser window
            UserWindow windowsUser = new UserWindow();
            windowsUser.setVisible(true);
        });
        southPanel.add(returnButton); // Add the return button to the south panel
    }


    // Method to add surveys to the middle center panel
    private void addSurveys() {
        for (Encuesta encuesta : encuestas) {
            // Create panel for each survey
            JPanel surveyPanel = new JPanel(new BorderLayout());

            // Add question label
            JLabel questionLabel = new JLabel(encuesta.getPregunta());
            surveyPanel.add(questionLabel, BorderLayout.NORTH);

            // Add options
            String[] options = encuesta.getOpciones().split(", ");
            ButtonGroup buttonGroup = new ButtonGroup();
            JPanel optionsPanel = new JPanel(new GridLayout(options.length, 1));
            for (String option : options) {
                JRadioButton radioButton = new JRadioButton(option);
                buttonGroup.add(radioButton);
                optionsPanel.add(radioButton);
            }
            surveyPanel.add(optionsPanel, BorderLayout.CENTER);

            // Add survey panel to the middle center panel
            middleCenterPanel.add(surveyPanel);
            surveyPanels.add(surveyPanel); // Add to list of survey panels
        }
    }

    // Method to submit surveys to the database
    private void submitSurveys() {
        ConnDB db = new ConnDB();
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO ResultadosEncuesta (idEncuesta, idUsuario, respuesta) VALUES (?, ?, ?)";
            for (int i = 0; i < encuestas.size(); i++) {
                Encuesta encuesta = encuestas.get(i);
                JPanel surveyPanel = surveyPanels.get(i);
                ButtonGroup buttonGroup = getButtonGroup(surveyPanel);
                String selectedOption = getSelectedOption(buttonGroup);

                if (selectedOption != null) {
                    try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
                        preparedStatement.setInt(1, encuesta.getIdEncuesta());
                        preparedStatement.setInt(2, userId);
                        preparedStatement.setString(3, selectedOption);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Surveys submitted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error submitting surveys: " + ex.getMessage());
        }
    }
    private String getSelectedOption(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }




    private ButtonGroup getButtonGroup(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JPanel) {
                for (Component subComponent : ((JPanel) component).getComponents()) {
                    if (subComponent instanceof JRadioButton) {
                        return (ButtonGroup) ((JRadioButton) subComponent).getModel().getGroup();
                    }
                }
            }
        }
        return null;
    }

}
