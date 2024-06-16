package Windows.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MakeCommentsTest {
    private MakeComments makeComments;

    @AfterEach
    public void tearDown() {
        makeComments.dispose();
    }

    @Test
    public void testFrameTitle() {
        assertEquals("Make Comments", makeComments.getTitle());
    }

}