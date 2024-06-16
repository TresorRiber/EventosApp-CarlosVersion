package Windows.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class BuyTicketsTest {

    private BuyTickets buyTickets;

    @BeforeEach
    public void setUp() {
        buyTickets = new BuyTickets();
        buyTickets.setVisible(true); // Make the frame visible for testing
    }

    @AfterEach
    public void tearDown() {
        buyTickets.dispose();
    }

    @Test
    public void testFrameTitle() {
        assertEquals("Buy tickets", buyTickets.getTitle());
    }

    @Test
    public void testPanelsExistence() {
        assertNotNull(buyTickets.northPanel);
        assertNotNull(buyTickets.centerPanel);
        assertNotNull(buyTickets.upperCenterPanel);
        assertNotNull(buyTickets.middleCenterPanel);
        assertNotNull(buyTickets.lowerCenterPanel);
        assertNotNull(buyTickets.southPanel);
    }


    @Test
    public void testLogoutButton() {
        JButton logoutButton = findButtonByText(buyTickets.southPanel, "Logout");
        assertNotNull(logoutButton);

        // Simulate button click
        logoutButton.doClick();
        assertFalse(buyTickets.isVisible());
    }

    @Test
    public void testReturnButton() {
        JButton returnButton = findButtonByText(buyTickets.southPanel, "Return");
        assertNotNull(returnButton);

        // Simulate button click
        returnButton.doClick();
    }

    private JButton findButtonByText(Container container, String text) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton && text.equals(((JButton) component).getText())) {
                return (JButton) component;
            }
            if (component instanceof Container) {
                JButton button = findButtonByText((Container) component, text);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }
}