package Main;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnDB {
    // Database connection parameters
    static String url = "jdbc:mysql://localhost:3306/Eventos_Euskadi";
    //static final String url = "jdbc:oracle:thin:@10.14.4.144:1521:ORCLCDB";
    static final String jdbc_driver = "com.mysql.jdbc.Driver";
    //static final String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
    static final String username = "root";
    static final String password = "Agente0";
    private Connection connection;
    public ConnDB(){
        try{
            Class.forName(jdbc_driver);
            System.out.println("Connecting to database...");
            connection=DriverManager.getConnection(url,username,password);
            System.out.println("Connected.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        try {
            connection.close();
            System.out.println("Disconnected from db.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


    // Method getEventNames
    public List<String> getEventNames() {
        List<String> eventNames = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // SQL query
            String sql = "SELECT nombre FROM evento";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                // Loop through the result set and add the names to the list
                while (resultSet.next()) {
                    String eventName = resultSet.getString("nombre");
                    eventNames.add(eventName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventNames;
    }

    // Method to showEventInfo
    public List<String> getEventInfo(String eventName) {
        List<String> eventInfo = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // SQL query to retrieve information about the specified event
            String sql = "SELECT nombre, fechaInicio, fechaFinalizacion, tipoEvento, estado FROM evento WHERE nombre = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, eventName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Loop through the result set and add the information to the list
                    while (resultSet.next()) {
                        String name = resultSet.getString("nombre");
                        String fechaInicio = resultSet.getString("fechaInicio");
                        String fechaFinal = resultSet.getString("fechaFinalizacion");
                        String tipoEvento = resultSet.getString("tipoEvento");
                        String estado = resultSet.getString("estado");

                        // Concatenate event details with line breaks
                        String eventDetails = "Name: " + name + "\n" +
                                "Start date: " + fechaInicio + "\n" +
                                "End date: " + fechaFinal + "\n" +
                                "Event type: " + tipoEvento + "\n" +
                                "State: " + estado;

                        eventInfo.add(eventDetails);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventInfo;
    }

    //Method that insert comments
    public boolean insertComment(String comment, int idUsuario, int idEvento, int rating) {
        String query = "INSERT INTO Comentario (texto, puntuacion, idUsuario, idEvento) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, comment);
            pstmt.setInt(2, rating);
            pstmt.setInt(3, idUsuario);
            pstmt.setInt(4, idEvento);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> allUsers(){
        List<String> users = new ArrayList<>();
        try{
            String sql ="SELECT IDUSUARIO, NOMBRE, APELLIDO, EMAIL FROM USUARIO where IDUSUARIO is not null";
            PreparedStatement select = connection.prepareStatement(sql);
            ResultSet result = select.executeQuery(sql);
            while(result.next()){
                users.add(String.valueOf(result.getInt("IDUSUARIO")));
                users.add(result.getString("NOMBRE"));
                users.add(result.getString("APELLIDO"));
                users.add(result.getString("EMAIL"));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();


        }
        return users;
    }


    //Method to delete a user
    public boolean deleteUsuario(String idUsuario){
        try {
            String sql ="DELETE FROM USUARIO WHERE IDUSUARIO = ?";
            PreparedStatement query = connection.prepareStatement(sql);
            query.setInt(1, Integer.parseInt(idUsuario));
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //Method to add data to the TRANSPORTE table
    public boolean insertData(int tar, String des, String hor, String tip){
        String sql = "INSERT INTO transporte (tarifa, descripcion, horarios, tipo)" +
            "VALUES (?, ?, ?, ?)";
        try (PreparedStatement query = connection.prepareStatement(sql)){
            query.setInt(1, tar);
            query.setString(2, des);
            query.setString(3, hor);
            query.setString(4, tip);
            query.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting data: "+ e.getMessage());
            return false;
        }

    }


    public static void main(String[] args) {
        ConnDB c = new ConnDB();
    }

}
