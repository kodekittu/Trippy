package org.kodekittu.trippy.service;

import org.kodekittu.trippy.database.TrippyStore;
import org.kodekittu.trippy.model.Survey;

import java.util.ArrayList;
import java.util.List;

public class SurveyService {

    public List<Survey> getAllSurveys() {
        return new ArrayList<>(TrippyStore.surveys.values());
    }

    public Survey getSurvey(String surveyId) {
        return TrippyStore.surveys.get(surveyId);
    }

    public boolean isExpired(Survey survey) {
        if (survey == null) return true;
        return survey.isExpired();
    }
}
