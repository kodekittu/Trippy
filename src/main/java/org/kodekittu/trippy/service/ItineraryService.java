package org.kodekittu.trippy.service;

import org.kodekittu.trippy.model.*;

import java.util.List;

public class ItineraryService {

    public void showItinerary(Survey survey, SurveyResponse response) {

        String country = "";
        String city = "";
        String style = "";
        String budget = "";
        String duration = "";
        List<String> activities = null;

        for (Answer answer : response.getAnswers().values()) {

            switch (answer.getQuestionId()) {

                case "Q1":
                case "Q_INDIA_REGION":
                    country = getValue(survey, answer);
                    break;

                case "Q_JAPAN":
                case "Q_VIETNAM":
                case "Q_INDIA":
                case "Q_SWISS":
                case "Q_NORTH_EAST_PLACE":
                case "Q_NORTH_PLACE":
                case "Q_SOUTH_PLACE":
                    city = getValue(survey, answer);
                    break;

                case "Q_TRAVEL_STYLE":
                    style = getValue(survey, answer);
                    break;

                case "Q_BUDGET":
                    budget = getValue(survey, answer);
                    break;

                case "Q_DURATION":
                    duration = getValue(survey, answer);
                    break;

                case "Q_ACTIVITY":
                    activities = answer.getSelectedOptions();
                    break;
            }
        }

        System.out.println("\n========== YOUR TRIP ==========");

        System.out.println("Country : " + country);
        System.out.println("City    : " + city);
        System.out.println("Duration: " + duration);
        System.out.println("Budget  : " + budget);
        System.out.println("Travel Style : " + style);

        System.out.println("\nRecommended Activities");

        if (activities != null) {
            for (String a : activities) {
                System.out.println("- " + a);
            }
        }

        System.out.println("\nSuggested Itinerary");

        if ("3".equals(duration)) {

            System.out.println("Day 1 : Explore city");
            System.out.println("Day 2 : Local attractions");
            System.out.println("Day 3 : Shopping & Departure");

        } else if ("5".equals(duration)) {

            System.out.println("Day 1 : City Tour");
            System.out.println("Day 2 : Famous Attractions");
            System.out.println("Day 3 : Food Tour");
            System.out.println("Day 4 : Adventure");
            System.out.println("Day 5 : Shopping");

        } else {

            System.out.println("Custom itinerary for " + duration);
        }

        System.out.println("===============================\n");
    }

    private String getValue(Survey survey, Answer answer) {

        if (answer.getSelectedOptions().isEmpty()) {
            return "";
        }

        String optionId = answer.getSelectedOptions().get(0);

        Question question = survey.getQuestion(answer.getQuestionId());

        for (Option option : question.getOptions()) {
            if (option.getId().equals(optionId)) {
                return option.getValue();
            }
        }

        return optionId;
    }
}
