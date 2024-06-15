package Windows.Admin;

import Main.ConnDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class UserManagement extends JFrame {
    private JTextField idUsuario;
    private static ConnDB connDB;

    // Panels declaration
    private  JPanel northPanel;
    private  JPanel centerPanel;
    private  JPanel upperCenterPanel;
    private  JPanel middleCenterPanel;
    private  JPanel lowerCenterPanel;
    private  JPanel southPanel;

    public UserManagement() {
        // Initialize panels
        northPanel = new JPanel();
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = new JPanel(new BorderLayout());
        middleCenterPanel = new JPanel(new GridLayout(3, 1));
        lowerCenterPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        initComponents();
        configurarLayout();
        setSize(400, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        connDB = new ConnDB();
        addImages();
    }

    private void initComponents() {
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


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

        JPanel center = centerPanel;
        seccion_center(center);

        JPanel south = southPanel;
        seccion_south(south);

    }
    private JPanel addCreatePanel(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }
    private void configurarLayout() {
        // Ajustes de diseño
        UIManager.put("TextField.margin", new Insets(5, 5, 5, 5));
    }

    private void seccion_south(JPanel south) {
        // Este sera el boton para refrescar la página
        JButton boton = new JButton("Refresh");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescar();
            }
        });
        south.add(boton);

        // Este sera el boton para borrar de la base de datos
        JButton boton2 = new JButton("Delete");
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String all = idUsuario.getText();
                boolean found = connDB.allUsers().remove(all);
                if (found) {
                    JOptionPane.showMessageDialog(null, "User deleted");
                    connDB.deleteUsuario(all);
                } else {
                    JOptionPane.showMessageDialog(null, "User not exist");
                }
            }
        });
        south.add(boton2);

        // Este sera el boton para volver a la pagina anterior
        JButton boton3 = new JButton("Back");
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAVentanaAnterior();
            }
        });
        south.add(boton3);
    }

    private void seccion_center(JPanel center) {
        upperCenterPanel.add(new JLabel("User Management"), BorderLayout.NORTH);

        // Panel for user ID input
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(new JLabel("User ID to DELETE:"));
        idUsuario = new JTextField("", 20);
        idPanel.add(idUsuario);

        middleCenterPanel.add(idPanel);
    }
    private void addImages() {
        ImageIcon imageIcon = new ImageIcon("images/Logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        // Create and add the image label to the northPanel
        JLabel logoLabel = new JLabel(scaledImageIcon);
        northPanel.add(logoLabel);
    }

    private void refrescar() {
        // This method is to delete all the text from the JTextFields
        idUsuario.setText("");
    }

    private void volverAVentanaAnterior() {
        AdminWindow ventana1 = new AdminWindow();
        ventana1.setVisible(true);
        dispose();
    }


}
