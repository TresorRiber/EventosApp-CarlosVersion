package Windows.User;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class BuyTicketsTest {

    @org.junit.jupiter.api.Test
    void addElements() {
    }

    @org.junit.jupiter.api.Test
    void addPanels() {
    }

    @Test
    void testAddElements() {
    }

    @Test
    void testAddPanels() {
    }

    @Test
    public void testAddJComboBox() {
        // Creamos una instancia de BuyTickets
        BuyTickets buyTickets = new BuyTickets();

        // Verificamos que JComboBox no sea nulo
        assertNotNull(buyTickets.select);

        // Verificamos que JComboBox esté vacío inicialmente
        assertEquals(0, buyTickets.select.getItemCount());

        // Llamamos al método addJComboBox() que queremos probar
        buyTickets.addJComboBox();

        // Verificamos que JComboBox se haya inicializado correctamente y tenga los elementos esperados
        assertNotNull(buyTickets.select);
        assertEquals(5, buyTickets.select.getItemCount()); // Suponiendo que hay 3 elementos en la lista de nombres de eventos
    }
}