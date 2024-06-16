package Windows.Admin;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class CreateEvents extends JFrame {
    // Database connection parameters
    static String url = "jdbc:mysql://localhost:3306/Eventos_Euskadi";
    static String username = "root";
    static String password = "Agente0";

    JPanel northPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel upperCenterPanel = new JPanel();
    JPanel middleCenterPanel = new JPanel();
    JPanel lowerCenterPanel = new JPanel();
    JPanel southPanel = new JPanel();

    private JTextField nombreField;
    private JTextField fechaInicioField;
    private JTextField fechaFinalizacionField;
    private JButton agregarButton;
    private JButton returnButton;

    public CreateEvents() {
        // Set JFrame properties
        setTitle("Admin Registered");
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
        addEventForm();
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
        lowerCenterPanel = addCreatePanel(Color.PINK, 400, 100);
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
        JLabel welcomeLabel = new JLabel("Create Events");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(welcomeLabel, BorderLayout.NORTH);
    }

    // Add form elements to capture event details
    private void addEventForm() {
        JLabel nombreLabel = new JLabel("Event Name:");
        nombreField = new JTextField(20);

        JLabel fechaInicioLabel = new JLabel("Start Date (yyyy-mm-dd):");
        fechaInicioField = new JTextField(20);

        JLabel fechaFinalizacionLabel = new JLabel("End Date (yyyy-mm-dd):");
        fechaFinalizacionField = new JTextField(20);

        // Add form elements to the middleCenterPanel
        middleCenterPanel.setLayout(new GridLayout(6, 1));
        middleCenterPanel.add(nombreLabel);
        middleCenterPanel.add(nombreField);
        middleCenterPanel.add(fechaInicioLabel);
        middleCenterPanel.add(fechaInicioField);
        middleCenterPanel.add(fechaFinalizacionLabel);
        middleCenterPanel.add(fechaFinalizacionField);
    }

    // Add buttons
    private void addButtons() {
        agregarButton = new JButton("Add Event");
        returnButton = new JButton("Back");
        agregarButton.setPreferredSize(new Dimension(150, 30));
        returnButton.setPreferredSize(new Dimension(150,30));
        agregarButton.addActionListener(e -> agregarEventoDesdeGUI());
        returnButton.addActionListener(e -> volverAVentanaAnterior());
        // Panel for the add button with center alignment
        JPanel agregarButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        agregarButtonPanel.add(agregarButton);
        agregarButtonPanel.add(returnButton);

        lowerCenterPanel.setLayout(new BorderLayout());
        lowerCenterPanel.add(agregarButtonPanel, BorderLayout.CENTER);
    }

    /**
     * Method to add an event to the database.
     * @param nombre Name of the event.
     * @param fechaInicio Start date of the event.
     * @param fechaFinalizacion End date of the event.
     */
    public void agregarEvento(String nombre, Date fechaInicio, Date fechaFinalizacion) {
        String sql = "INSERT INTO evento (nombre, fechaInicio, fechaFinalizacion) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombre);
            statement.setDate(2, fechaInicio);
            statement.setDate(3, fechaFinalizacion);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Evento agregado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar el evento: " + e.getMessage());
        }
    }

    /**
     * Method to capture data from Graphical User Interface and add the event.
     */
    private void agregarEventoDesdeGUI() {
        try {
            String nombre = nombreField.getText();
            Date fechaInicio = Date.valueOf(fechaInicioField.getText());
            Date fechaFinalizacion = Date.valueOf(fechaFinalizacionField.getText());
            agregarEvento(nombre, fechaInicio, fechaFinalizacion);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Por favor, use el formato yyyy-mm-dd.");
        }
    }
    private void volverAVentanaAnterior() {
        AdminWindow ventana1 = new AdminWindow();
        ventana1.setVisible(true);
        dispose();
    }
}