package org.kodekittu.trippy.model;

import org.kodekittu.trippy.model.enums.SurveyStatus;

import java.util.*;

public class SurveyResponse {

    private String surveyId;
    private String userId;

    // current question while survey is in progress
    private String currentQuestionId;

    private SurveyStatus status = SurveyStatus.IN_PROGRESS;

    // questionId -> Answer
    private Map<String, Answer> answers = new LinkedHashMap<>();

    public SurveyResponse(String surveyId,
                          String userId) {

        this.surveyId = surveyId;
        this.userId = userId;
    }

    public void addAnswer(Answer answer) {
        answers.put(answer.getQuestionId(), answer);
    }

    public Map<String, Answer> getAnswers() {
        return answers;
    }

    public String getCurrentQuestionId() {
        return currentQuestionId;
    }

    public void setCurrentQuestionId(String currentQuestionId) {
        this.currentQuestionId = currentQuestionId;
    }

    public SurveyStatus getStatus() {
        return status;
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public String getUserId() {
        return userId;
    }
}
