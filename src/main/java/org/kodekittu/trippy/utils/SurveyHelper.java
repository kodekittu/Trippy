package org.kodekittu.trippy.utils;

import org.kodekittu.trippy.database.TrippyStore;
import org.kodekittu.trippy.model.Option;
import org.kodekittu.trippy.model.Question;
import org.kodekittu.trippy.model.Survey;
import org.kodekittu.trippy.model.enums.QuestionType;

import java.time.LocalDate;

public final class SurveyHelper {

    private SurveyHelper() {
    }

    public static void loadSurveys() {

        Survey survey = new Survey(
                "S1",
                "Travel Planner Survey",
                LocalDate.now().plusDays(30)
        );

        addCountryQuestion(survey);
        addJapanQuestions(survey);
        addVietnamQuestions(survey);
        addIndiaQuestions(survey);
        addSwitzerlandQuestions(survey);
        addTravelStyleQuestion(survey);
        addBudgetQuestion(survey);
        addDurationQuestion(survey);
        addActivityQuestion(survey);

        survey.setFirstQuestionId("Q1");

        TrippyStore.surveys.put(survey.getId(), survey);

        Survey indiaCityTripSurvey = new Survey(
                "S2",
                "Indian City Trip",
                LocalDate.now().plusDays(30)
        );

        addIndiaRegionQuestion(indiaCityTripSurvey);
        addNorthEastPlaces(indiaCityTripSurvey);
        addNorthPlaces(indiaCityTripSurvey);
        addSouthPlaces(indiaCityTripSurvey);
        addTravelStyleQuestion(indiaCityTripSurvey);
        addBudgetQuestion(indiaCityTripSurvey);
        addDurationQuestion(indiaCityTripSurvey);
        addActivityQuestion(indiaCityTripSurvey);

        indiaCityTripSurvey.setFirstQuestionId("Q_INDIA_REGION");

        TrippyStore.surveys.put(indiaCityTripSurvey.getId(), indiaCityTripSurvey);
    }

    private static void addCountryQuestion(Survey survey) {

        Question q = new Question(
                "Q1",
                "Select Destination Country",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("JP", "Japan"));
        q.addOption(new Option("VN", "Vietnam"));
        q.addOption(new Option("IN", "India"));
        q.addOption(new Option("SW", "Switzerland"));

        q.addBranch("JP", "Q_JAPAN");
        q.addBranch("VN", "Q_VIETNAM");
        q.addBranch("IN", "Q_INDIA");
        q.addBranch("SW", "Q_SWISS");

        survey.addQuestion(q);
    }

    private static void addJapanQuestions(Survey survey) {

        Question q = new Question(
                "Q_JAPAN",
                "Choose City",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("TOKYO", "Tokyo"));
        q.addOption(new Option("KYOTO", "Kyoto"));
        q.addOption(new Option("OSAKA", "Osaka"));

        q.addBranch("TOKYO", "Q_TRAVEL_STYLE");
        q.addBranch("KYOTO", "Q_TRAVEL_STYLE");
        q.addBranch("OSAKA", "Q_TRAVEL_STYLE");

        survey.addQuestion(q);
    }

    private static void addVietnamQuestions(Survey survey) {

        Question q = new Question(
                "Q_VIETNAM",
                "Choose City",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("HANOI", "Hanoi"));
        q.addOption(new Option("HCM", "Ho Chi Minh City"));
        q.addOption(new Option("DANANG", "Da Nang"));

        q.addBranch("HANOI", "Q_TRAVEL_STYLE");
        q.addBranch("HCM", "Q_TRAVEL_STYLE");
        q.addBranch("DANANG", "Q_TRAVEL_STYLE");

        survey.addQuestion(q);
    }

    private static void addIndiaQuestions(Survey survey) {

        Question q = new Question(
                "Q_INDIA",
                "Choose City",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("DELHI", "Delhi"));
        q.addOption(new Option("JAIPUR", "Jaipur"));
        q.addOption(new Option("GOA", "Goa"));

        q.addBranch("DELHI", "Q_TRAVEL_STYLE");
        q.addBranch("JAIPUR", "Q_TRAVEL_STYLE");
        q.addBranch("GOA", "Q_TRAVEL_STYLE");

        survey.addQuestion(q);
    }

    private static void addSwitzerlandQuestions(Survey survey) {

        Question q = new Question(
                "Q_SWISS",
                "Choose City",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("ZURICH", "Zurich"));
        q.addOption(new Option("INTERLAKEN", "Interlaken"));
        q.addOption(new Option("LUCERNE", "Lucerne"));

        q.addBranch("ZURICH", "Q_TRAVEL_STYLE");
        q.addBranch("INTERLAKEN", "Q_TRAVEL_STYLE");
        q.addBranch("LUCERNE", "Q_TRAVEL_STYLE");

        survey.addQuestion(q);
    }

    private static void addTravelStyleQuestion(Survey survey) {

        Question q = new Question(
                "Q_TRAVEL_STYLE",
                "Travel Style",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("SOLO", "Solo"));
        q.addOption(new Option("COUPLE", "Couple"));
        q.addOption(new Option("FRIENDS", "Friends"));
        q.addOption(new Option("FAMILY", "Family"));

        q.setDefaultNextQuestionId("Q_BUDGET");

        survey.addQuestion(q);
    }

    private static void addBudgetQuestion(Survey survey) {

        Question q = new Question(
                "Q_BUDGET",
                "Select Budget",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("LOW", "Budget"));
        q.addOption(new Option("MID", "Standard"));
        q.addOption(new Option("HIGH", "Luxury"));

        q.setDefaultNextQuestionId("Q_DURATION");

        survey.addQuestion(q);
    }

    private static void addDurationQuestion(Survey survey) {

        Question q = new Question(
                "Q_DURATION",
                "Trip Duration",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("3", "3 Days"));
        q.addOption(new Option("5", "5 Days"));
        q.addOption(new Option("7", "7 Days"));
        q.addOption(new Option("10", "10 Days"));

        q.setDefaultNextQuestionId("Q_ACTIVITY");

        survey.addQuestion(q);
    }

    private static void addActivityQuestion(Survey survey) {

        Question q = new Question(
                "Q_ACTIVITY",
                "Preferred Activities",
                QuestionType.MULTI_SELECT
        );

        q.addOption(new Option("FOOD", "Food"));
        q.addOption(new Option("SHOPPING", "Shopping"));
        q.addOption(new Option("ADVENTURE", "Adventure"));
        q.addOption(new Option("NATURE", "Nature"));
        q.addOption(new Option("BEACH", "Beaches"));
        q.addOption(new Option("NIGHT", "Nightlife"));

        survey.addQuestion(q);
    }

    private static void addIndiaRegionQuestion(Survey survey) {

        Question q = new Question(
                "Q_INDIA_REGION",
                "Which part of India do you want to visit?",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("NORTH_EAST", "North East"));
        q.addOption(new Option("NORTH", "North"));
        q.addOption(new Option("SOUTH", "South"));

        q.addBranch("NORTH_EAST", "Q_NORTH_EAST_PLACE");
        q.addBranch("NORTH", "Q_NORTH_PLACE");
        q.addBranch("SOUTH", "Q_SOUTH_PLACE");

        survey.addQuestion(q);
    }

    private static void addNorthEastPlaces(Survey survey) {

        Question q = new Question(
                "Q_NORTH_EAST_PLACE",
                "Choose a North East place",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("ASSAM", "Assam"));
        q.addOption(new Option("MEGHALAYA", "Meghalaya"));
        q.addOption(new Option("SIKKIM", "Sikkim"));

        q.addBranch("ASSAM", "Q_TRAVEL_STYLE");
        q.addBranch("MEGHALAYA", "Q_TRAVEL_STYLE");
        q.addBranch("SIKKIM", "Q_TRAVEL_STYLE");

        survey.addQuestion(q);
    }

    private static void addNorthPlaces(Survey survey) {

        Question q = new Question(
                "Q_NORTH_PLACE",
                "Choose a North India place",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("HARYANA", "Haryana"));
        q.addOption(new Option("PUNJAB", "Punjab"));
        q.addOption(new Option("JAMMU", "Jammu"));

        q.addBranch("HARYANA", "Q_TRAVEL_STYLE");
        q.addBranch("PUNJAB", "Q_TRAVEL_STYLE");
        q.addBranch("JAMMU", "Q_TRAVEL_STYLE");

        survey.addQuestion(q);
    }

    private static void addSouthPlaces(Survey survey) {

        Question q = new Question(
                "Q_SOUTH_PLACE",
                "Choose a South India city",
                QuestionType.SINGLE_SELECT
        );

        q.addOption(new Option("BENGALURU", "Bengaluru"));
        q.addOption(new Option("CHENNAI", "Chennai"));
        q.addOption(new Option("KOCHI", "Kochi"));
        q.addOption(new Option("HYDERABAD", "Hyderabad"));

        q.addBranch("BENGALURU", "Q_TRAVEL_STYLE");
        q.addBranch("CHENNAI", "Q_TRAVEL_STYLE");
        q.addBranch("KOCHI", "Q_TRAVEL_STYLE");
        q.addBranch("HYDERABAD", "Q_TRAVEL_STYLE");

        survey.addQuestion(q);
    }
}
