package Windows.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DoSurveysTest{

    private DoSurveys doSurveys;

    @AfterEach
    public void tearDown() {
        doSurveys.dispose();
    }

    @Test
    public void testFrameTitle() {
        assertEquals("Do Surveys", doSurveys.getTitle());
    }

    @Test
    public void testSurveysAdded() {
        assertEquals(3, doSurveys.surveyPanels.size());
    }


}