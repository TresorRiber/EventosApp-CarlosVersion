package Windows.Admin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateEventsTest {
    private CreateEvents eventService;
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        eventService = new CreateEvents();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Eventos_Euskadi", "root", "Agente0");
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS evento (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), fechaInicio DATE, fechaFinalizacion DATE)");
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS evento");
        }
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testAgregarEvento() {
        assertDoesNotThrow(() -> {
            eventService.agregarEvento("Test Event", Date.valueOf("2024-01-01"), Date.valueOf("2024-01-02"));
        });
    }

    @Test
    public void testAgregarEventoFechaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            eventService.agregarEvento("Test Event", Date.valueOf("2024-01-01"), Date.valueOf("invalid-date"));
        });
    }
}
