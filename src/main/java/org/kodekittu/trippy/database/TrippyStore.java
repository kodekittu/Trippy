package org.kodekittu.trippy.database;

import org.kodekittu.trippy.model.Survey;
import org.kodekittu.trippy.model.SurveyResponse;
import org.kodekittu.trippy.model.User;

import java.util.HashMap;
import java.util.Map;

/*
 Assumption:
 Everything is stored in memory.
 Key for responses = userId + "_" + surveyId
*/
public class TrippyStore {

    private TrippyStore() {
    }

    public static Map<String, Survey> surveys = new HashMap<>();

    public static Map<String, User> users = new HashMap<>();

    public static Map<String, SurveyResponse> responses = new HashMap<>();

    public static String getResponseKey(String userId, String surveyId) {
        return userId + "_" + surveyId;
    }
}