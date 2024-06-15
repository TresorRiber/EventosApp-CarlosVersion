package Windows.Admin;
import Main.ConnDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class LogisticsManagement extends JFrame {
    private JTextField tarifa;
    private JTextField descripcion;
    private JTextField horarios;
    private JTextField tipo;
    private JTextArea historialLogistica;
    private final List<String> logistica;
    private static ConnDB connDB;
    private  JPanel northPanel;
    private  JPanel centerPanel;
    private  JPanel upperCenterPanel;
    private  JPanel middleCenterPanel;
    private  JPanel lowerCenterPanel;
    private  JPanel southPanel;


    public LogisticsManagement() {
        logistica = new ArrayList<>();
        connDB = new ConnDB();

        setTitle("GESTION DE LOGISTICA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Initialize panels with specific colors and sizes
        northPanel = addCreatePanel(Color.BLACK, 400, 200);
        centerPanel = new JPanel(new BorderLayout());
        upperCenterPanel = addCreatePanel(Color.LIGHT_GRAY, 400, 90);
        middleCenterPanel = addCreatePanel(Color.WHITE, 400, 300);
        lowerCenterPanel = addCreatePanel(Color.WHITE, 400, 170);
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

        JPanel norte = northPanel;

        JPanel west = middleCenterPanel;
        seccionWest(west);

        JPanel south = southPanel;
        seccionSouth(south);

        JPanel centro = lowerCenterPanel;
        seccionCentro(centro);

        setSize(400, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        addImages();
        addLabels();


        // When the X is pressed, the DB is closed before closing the window
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                connDB.close();
            }
        });
    }
    private JPanel addCreatePanel(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }


    private void seccionSouth(JPanel south) {
        //Este sera el boton para añadir los eventos al historial
        JButton boton = new JButton("Add Transport");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tar;
                try {
                    tar = Integer.parseInt(tarifa.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please, insert some valid value to transporte");
                    return;
                }

                String des = descripcion.getText();
                String hor = horarios.getText();
                String tip = tipo.getText();

                if (des.isEmpty() || hor.isEmpty() || tip.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please, fill all the gaps.");
                    return;
                }

                boolean success = connDB.insertData(tar, des, hor, tip);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Transporte insertado correctamente");
                    eventoAgregado();
                } else {
                    JOptionPane.showMessageDialog(null, "Algún valor no es correcto");
                }
            }
        });
        south.add(boton);


        //Este sera el boton para borrar el historial
        JButton boton2 = new JButton("Delete Text");
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrarHistorial();
            }
        });
        south.add(boton2);


        //Este sera el boton para volver a la pagina anterior
        JButton boton3 = new JButton("Back");
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAVentanaAnterior();
            }
        });
        south.add(boton3);

    }

    private void seccionWest(JPanel west) {
        JPanel cubo = new JPanel();
        cubo.setLayout(new GridLayout(4, 1, 5, 5));
        cubo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        cubo.add(new JLabel("Tarifa (number)"));
        tarifa = new JTextField(15);
        cubo.add(tarifa);

        cubo.add(new JLabel("Descripcion"));
        descripcion = new JTextField(15);
        cubo.add(descripcion);

        cubo.add(new JLabel("Horarios"));
        horarios = new JTextField(15);
        cubo.add(horarios);

        cubo.add(new JLabel("Tipo"));
        tipo = new JTextField(15);
        cubo.add(tipo);

        west.add(cubo);
    }


    private void seccionCentro(JPanel centro) {
        historialLogistica = new JTextArea(10, 30);
        historialLogistica.setEditable(false);


        JScrollPane scrollPane = new JScrollPane(historialLogistica);
        centro.add(scrollPane);
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
        JLabel label1 = new JLabel("Logistic management");
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        upperCenterPanel.add(label1, BorderLayout.NORTH);
    }


    private void eventoAgregado() {
        String evento = String.format(" Tarifa: %s, Descripcion: %s, Horarios: %s, Tipo: %s",
                tarifa.getText(), descripcion.getText(),
                horarios.getText(), tipo.getText());


        logistica.add(evento);
        actualizarHistorial();
        limpiarCampos();


    }

    private void actualizarHistorial() {
        historialLogistica.setText("");
        for (String evento : logistica) {
            historialLogistica.append(evento + "\n");
        }
    }

    private void limpiarCampos() {
//Este metodo es para volver a poder ingresar datos despues de haber registrado un evento
        tarifa.setText("");
        descripcion.setText("");
        horarios.setText("");
        tipo.setText("");
    }

    private void BorrarHistorial(){
        logistica.clear();//Para limpiar el historial de eventos
        historialLogistica.setText("");
    }
    private void volverAVentanaAnterior() {
        AdminWindow ventana1 = new AdminWindow();
        ventana1.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        LogisticsManagement l = new LogisticsManagement();
    }

}
