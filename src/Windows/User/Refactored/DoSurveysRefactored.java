package Windows.User.Refactored;

import Classes.Encuesta;
import Main.ConnDB;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DoSurveysRefactored extends JFrame {
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

    public DoSurveysRefactored(int userId) {
        this.userId = userId;
        connDB = new ConnDB();

        // Set JFrame properties
        setTitle("Do Surveys");
        setSize(400, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize panels
        northPanel = createPanel(Color.BLACK, new Dimension(400, 200));
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = createPanel(Color.LIGHT_GRAY, new Dimension(400, 100));
        middleCenterPanel = createPanel(Color.WHITE, null);
        lowerCenterPanel = createPanel(Color.LIGHT_GRAY, new Dimension(400, 100));
        southPanel = createPanel(Color.WHITE, new Dimension(400, 100));

        // Initialize list of surveys and panels
        encuestas = new ArrayList<>();
        surveyPanels = new ArrayList<>();
        initializeSurveys();

        // Add elements to the JFrame
        addElements();

        setVisible(true);
    }

    private JPanel createPanel(Color color, Dimension dimension) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        if (dimension != null) {
            panel.setPreferredSize(dimension);
        }
        return panel;
    }

    private void initializeSurveys() {
        encuestas.add(new Encuesta(1, "2024-05-20", "2024-05-01", "Excelente, Bien, Regular, Mal", "¿Qué te pareció el evento?"));
        encuestas.add(new Encuesta(2, "2024-05-22", "2024-05-03", "Sí, No", "¿Te gustaría participar en más eventos similares?"));
        encuestas.add(new Encuesta(3, "2024-05-24", "2024-05-05", "Conciertos, Talleres, Exposiciones, Deportes", "¿Qué actividades te gustaría ver en futuros eventos?"));
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

    private void addImages() {
        ImageIcon imageIcon = new ImageIcon("images/Logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledImageIcon);
        northPanel.add(logoLabel);
    }

    private void addLabels() {
        JLabel welcomeLabel = new JLabel("Do Surveys");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(welcomeLabel, BorderLayout.NORTH);
    }

    private void addButtons() {
        // Submit survey button
        JButton surveyButton = new JButton("Submit Survey");
        surveyButton.addActionListener(e -> submitSurveys());
        southPanel.add(surveyButton);

        // Return button
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> returnToUserWindow());
        southPanel.add(returnButton);
    }

    private void addSurveys() {
        for (Encuesta encuesta : encuestas) {
            JPanel surveyPanel = new JPanel(new BorderLayout());

            JLabel questionLabel = new JLabel(encuesta.getPregunta());
            surveyPanel.add(questionLabel, BorderLayout.NORTH);

            String[] options = encuesta.getOpciones().split(", ");
            ButtonGroup buttonGroup = new ButtonGroup();
            JPanel optionsPanel = new JPanel(new GridLayout(options.length, 1));
            for (String option : options) {
                JRadioButton radioButton = new JRadioButton(option);
                buttonGroup.add(radioButton);
                optionsPanel.add(radioButton);
            }
            surveyPanel.add(optionsPanel, BorderLayout.CENTER);

            middleCenterPanel.add(surveyPanel);
            surveyPanels.add(surveyPanel);
        }
    }

    private void submitSurveys() {
        try (Connection connection = connDB.getConnection()) {
            String sql = "INSERT INTO ResultadosEncuesta (idEncuesta, idUsuario, respuesta) VALUES (?, ?, ?)";
            for (int i = 0; i < encuestas.size(); i++) {
                Encuesta encuesta = encuestas.get(i);
                JPanel surveyPanel = surveyPanels.get(i);
                ButtonGroup buttonGroup = getButtonGroup(surveyPanel);
                String selectedOption = getSelectedOption(buttonGroup);

                if (selectedOption != null) {
                    try (var preparedStatement = connection.prepareStatement(sql)) {
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

    private void returnToUserWindow() {
        dispose();
        UserWindowRefactored windowsUser = new UserWindowRefactored();
        windowsUser.setVisible(true);
    }
}
