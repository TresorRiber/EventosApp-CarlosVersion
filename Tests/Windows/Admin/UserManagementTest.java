package Windows.Admin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserManagementTest {
    private UserManagement userManagement;

    @AfterEach
    public void tearDown() {
        userManagement.dispose();
    }

    @Test
    public void testFrameTitle() {
        assertEquals("User Management", userManagement.getTitle());
    }


}