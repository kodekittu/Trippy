package org.kodekittu.trippy.model;

import java.util.*;

public class Answer {

    private String questionId;

    // Used for SINGLE_SELECT and MULTI_SELECT
    private List<String> selectedOptions = new ArrayList<>();

    // Used only for TEXT question
    private String textAnswer;

    // true if user skipped this question
    private boolean skipped;

    public Answer(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public List<String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }
}