package Windows.Admin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class LogisticsManagementTest {

    private LogisticsManagement logisticsManagement;

    @BeforeEach
    public void setUp() {
        logisticsManagement = new LogisticsManagement();
        logisticsManagement.setVisible(true); // shows the frame to test
    }

    @AfterEach
    public void tearDown() {
        logisticsManagement.dispose();
    }

    @Test
    public void shouldHaveCorrectTitle() {
        assertEquals("GESTION DE LOGISTICA", logisticsManagement.getTitle());
    }

    @Test
    public void shouldHaveAllFormFields() {
        JTextField tarifaField = findComponentByName(logisticsManagement, "tarifaField");
        JTextField descripcionField = findComponentByName(logisticsManagement, "descripcionField");
        JTextField horariosField = findComponentByName(logisticsManagement, "horariosField");
        JTextField tipoField = findComponentByName(logisticsManagement, "tipoField");

        assertNotNull(tarifaField);
        assertNotNull(descripcionField);
        assertNotNull(horariosField);
        assertNotNull(tipoField);
    }

    @Test
    public void shouldHaveAllButtons() {
        JButton addButton = findButtonByText(logisticsManagement, "Add Transport");
        JButton deleteButton = findButtonByText(logisticsManagement, "Delete Text");
        JButton backButton = findButtonByText(logisticsManagement, "Back");

        assertNotNull(addButton);
        assertNotNull(deleteButton);
        assertNotNull(backButton);
    }
    private <T extends JComponent> T findComponentByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (name.equals(component.getName())) {
                return (T) component;
            }
            if (component instanceof Container) {
                T result = findComponentByName((Container) component, name);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private JButton findButtonByText(Container container, String text) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton && text.equals(((JButton) component).getText())) {
                return (JButton) component;
            }
            if (component instanceof Container) {
                JButton result = findButtonByText((Container) component, text);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}