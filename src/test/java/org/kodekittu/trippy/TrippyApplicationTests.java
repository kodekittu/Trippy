package org.kodekittu.trippy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kodekittu.trippy.database.TrippyStore;
import org.kodekittu.trippy.model.SurveyResponse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TrippyApplicationTests {

    @BeforeEach
    void clearStore() {
        TrippyStore.surveys.clear();
        TrippyStore.users.clear();
        TrippyStore.responses.clear();
    }

    @Test
    void showSurveysPrintsMessageWhenNoSurveysExist() {
        String output = captureOutput(TrippyApplication::showSurveys);

        assertTrue(output.contains("no survey present"));
    }

    @Test
    void showMySurveysPrintsMessageWhenUserHasNoResponses() {
        String output = captureOutput(() -> TrippyApplication.showMySurveys("U1"));

        assertTrue(output.contains("no survey present"));
    }

    @Test
    void showMySurveysPrintsMessageWhenResponseSurveyIsMissing() {
        SurveyResponse response = new SurveyResponse("UNKNOWN_SURVEY", "U1");
        TrippyStore.responses.put(TrippyStore.getResponseKey("U1", "UNKNOWN_SURVEY"), response);

        String output = captureOutput(() -> TrippyApplication.showMySurveys("U1"));

        assertTrue(output.contains("survey details not found"));
    }

    @Test
    void showMySurveysPrintsMessageWhenUserIdIsMissing() {
        String output = captureOutput(() -> TrippyApplication.showMySurveys(null));

        assertTrue(output.contains("user id is required"));
    }

    private String captureOutput(Runnable runnable) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));
        try {
            runnable.run();
        } finally {
            System.setOut(originalOut);
        }

        return output.toString();
    }
}
