package Windows.Admin.Refactored;

import Main.ConnDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
public class UserManagementRefactored extends JFrame {
    private JTextField idUsuario;
    private JTextArea userInfoArea;
    private JList<String> userList;
    private DefaultListModel<String> listModel;
    private static ConnDB connDB;

    // Panels declaration
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel upperCenterPanel;
    private JPanel middleCenterPanel;
    private JPanel lowerCenterPanel;
    private JPanel southPanel;

    public UserManagementRefactored() {
        // Initialize ConnDB
        connDB = new ConnDB();

        initComponents();
        configurarLayout();
        setSize(400, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        loadUserList();
    }

    private void initComponents() {
        setTitle("User Management");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialize panels with specific colors and sizes
        northPanel = addCreatePanel(Color.BLACK, 400, 200);
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = addCreatePanel(Color.LIGHT_GRAY, 400, 100);
        middleCenterPanel = addCreatePanel(Color.WHITE, 400, 100);
        lowerCenterPanel = addCreatePanel(Color.WHITE, 400, 200);
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

        // Configure center and south panels
        configureCenterPanel();
        configureSouthPanel();

        addImages();
        addLabels();
    }

    private JPanel addCreatePanel(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    private void configurarLayout() {
        UIManager.put("TextField.margin", new Insets(5, 5, 5, 5));
    }

    private void configureCenterPanel() {
        // Panel for user ID input
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(new JLabel("User ID to DELETE:"));
        idUsuario = new JTextField("", 20);
        idPanel.add(idUsuario);
        middleCenterPanel.add(idPanel);

        // Panel for user info display
        userInfoArea = new JTextArea(20, 20);
        userInfoArea.setEditable(false);

        // Add user list
        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(userList);
        lowerCenterPanel.add(listScrollPane);
    }

    private void configureSouthPanel() {
        addSouthButton("Refresh", e -> refrescar());
        addSouthButton("Delete", e -> deleteUser());
        addSouthButton("Back", e -> volverAVentanaAnterior());
    }

    private void addSouthButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        southPanel.add(button);
    }

    private void addImages() {
        ImageIcon imageIcon = new ImageIcon("images/Logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledImageIcon);
        northPanel.add(logoLabel);
    }

    private void addLabels() {
        JLabel label1 = new JLabel("User management");
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(label1, BorderLayout.NORTH);
    }

    private void refrescar() {
        idUsuario.setText("");
        loadUserList();
    }

    private void deleteUser() {
        String userId = idUsuario.getText();
        boolean found = connDB.allUsers().contains(userId);
        if (found) {
            JOptionPane.showMessageDialog(null, "User deleted");
            connDB.deleteUsuario(userId);
            loadUserList();
        } else {
            JOptionPane.showMessageDialog(null, "User not exist");
        }
    }

    private void volverAVentanaAnterior() {
        AdminWindowRefactored ventana1 = new AdminWindowRefactored();
        ventana1.setVisible(true);
        dispose();
    }

    private void loadUserList() {
        listModel.clear();
        List<String> users = connDB.allUsers();
        for (String user : users) {
            listModel.addElement(user);
        }
    }
}
