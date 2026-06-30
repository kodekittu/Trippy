package org.kodekittu.trippy.model;

import java.time.LocalDate;
import java.util.*;

public class Survey {

    private String id;
    private String name;

    private String firstQuestionId;

    private LocalDate expiryDate;

    // questionId -> Question
    private Map<String, Question> questions = new HashMap<>();

    public Survey(String id,
                  String name,
                  LocalDate expiryDate) {

        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
    }

    public void addQuestion(Question question) {
        questions.put(question.getId(), question);
    }

    public Question getQuestion(String questionId) {
        return questions.get(questionId);
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Question> getQuestions() {
        return questions;
    }

    public String getFirstQuestionId() {
        return firstQuestionId;
    }

    public void setFirstQuestionId(String firstQuestionId) {
        this.firstQuestionId = firstQuestionId;
    }
}
