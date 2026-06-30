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
}
