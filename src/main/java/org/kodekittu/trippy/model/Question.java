package org.kodekittu.trippy.model;

import org.kodekittu.trippy.model.enums.QuestionType;

import java.util.*;

public class Question {

    private String id;
    private String question;

    private QuestionType type;

    private List<Option> options = new ArrayList<>();

    // optionId -> nextQuestionId
    // Assumption:
    // If mapping is absent, survey follows default flow.
    private Map<String, String> branching = new HashMap<>();

    private String defaultNextQuestionId;

    public Question(String id,
                    String question,
                    QuestionType type) {

        this.id = id;
        this.question = question;
        this.type = type;
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public void addBranch(String optionId, String nextQuestionId) {
        branching.put(optionId, nextQuestionId);
    }

    public String getNextQuestion(String optionId) {

        if(branching.containsKey(optionId)) {
            return branching.get(optionId);
        }

        return defaultNextQuestionId;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public QuestionType getType() {
        return type;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setDefaultNextQuestionId(String id) {
        this.defaultNextQuestionId = id;
    }
}
