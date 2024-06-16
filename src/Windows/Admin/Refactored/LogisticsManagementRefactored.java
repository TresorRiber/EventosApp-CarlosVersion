package Windows.Admin.Refactored;

import Main.ConnDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class LogisticsManagementRefactored extends JFrame {
    private JTextField tarifaField;
    private JTextField descripcionField;
    private JTextField horariosField;
    private JTextField tipoField;
    private JTextArea historialLogisticaArea;
    private final List<String> logistica;
    private static ConnDB connDB;

    public LogisticsManagementRefactored() {
        logistica = new ArrayList<>();
        connDB = new ConnDB();

        setTitle("GESTION DE LOGISTICA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and add panels
        addPanels();

        setSize(400, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        // Add window listener to close DB connection
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                connDB.close();
            }
        });
    }

    private void addPanels() {
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Initialize and add panels
        JPanel northPanel = createPanel(Color.BLACK, 400, 200);
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel upperCenterPanel = createPanel(Color.LIGHT_GRAY, 400, 90);
        JPanel middleCenterPanel = createPanel(Color.WHITE, 400, 300);
        JPanel lowerCenterPanel = createPanel(Color.WHITE, 400, 170);
        JPanel southPanel = createPanel(Color.WHITE, 400, 150);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        centerPanel.add(upperCenterPanel, BorderLayout.NORTH);
        centerPanel.add(middleCenterPanel, BorderLayout.CENTER);
        centerPanel.add(lowerCenterPanel, BorderLayout.SOUTH);

        // Add components to panels
        addImages(northPanel);
        addLabels(upperCenterPanel);
        addFormComponents(middleCenterPanel);
        addActionButtons(southPanel);
        addHistorialArea(lowerCenterPanel);
    }

    private JPanel createPanel(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    private void addImages(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon("images/Logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledImageIcon);
        panel.add(logoLabel);
    }

    private void addLabels(JPanel panel) {
        JLabel label = new JLabel("Logistic management");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);
    }

    private void addFormComponents(JPanel panel) {
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        formPanel.add(new JLabel("Fee (number):"));
        tarifaField = new JTextField(15);
        formPanel.add(tarifaField);

        formPanel.add(new JLabel("Description:"));
        descripcionField = new JTextField(15);
        formPanel.add(descripcionField);

        formPanel.add(new JLabel("Schedules:"));
        horariosField = new JTextField(15);
        formPanel.add(horariosField);

        formPanel.add(new JLabel("Type:"));
        tipoField = new JTextField(15);
        formPanel.add(tipoField);

        panel.add(formPanel);
    }

    private void addHistorialArea(JPanel panel) {
        historialLogisticaArea = new JTextArea(10, 30);
        historialLogisticaArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historialLogisticaArea);
        panel.add(scrollPane);
    }

    private void addActionButtons(JPanel panel) {
        JButton addButton = new JButton("Add Transport");
        addButton.addActionListener(e -> addTransport());
        panel.add(addButton);

        JButton deleteButton = new JButton("Delete Text");
        deleteButton.addActionListener(e -> clearHistorial());
        panel.add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> goBack());
        panel.add(backButton);
    }

    private void addTransport() {
        int tarifa;
        try {
            tarifa = Integer.parseInt(tarifaField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please, insert a valid fee.");
            return;
        }

        String descripcion = descripcionField.getText();
        String horarios = horariosField.getText();
        String tipo = tipoField.getText();

        if (descripcion.isEmpty() || horarios.isEmpty() || tipo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please, fill all the fields.");
            return;
        }

        boolean success = connDB.insertData(tarifa, descripcion, horarios, tipo);
        if (success) {
            JOptionPane.showMessageDialog(null, "Transport inserted.");
            addEventToHistorial(tarifa, descripcion, horarios, tipo);
        } else {
            JOptionPane.showMessageDialog(null, "Error inserting data.");
        }
    }

    private void addEventToHistorial(int tarifa, String descripcion, String horarios, String tipo) {
        String event = String.format("Fee: %d, Description: %s, Schedules: %s, Type: %s",
                tarifa, descripcion, horarios, tipo);
        logistica.add(event);
        updateHistorial();
        clearFields();
    }

    private void updateHistorial() {
        historialLogisticaArea.setText("");
        for (String event : logistica) {
            historialLogisticaArea.append(event + "\n");
        }
    }

    private void clearFields() {
        tarifaField.setText("");
        descripcionField.setText("");
        horariosField.setText("");
        tipoField.setText("");
    }

    private void clearHistorial() {
        logistica.clear();
        historialLogisticaArea.setText("");
    }

    private void goBack() {
        AdminWindowRefactored adminWindow = new AdminWindowRefactored();
        adminWindow.setVisible(true);
        dispose();
    }
}
