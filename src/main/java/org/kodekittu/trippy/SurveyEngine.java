package org.kodekittu.trippy;

import org.kodekittu.trippy.model.*;
import org.kodekittu.trippy.model.enums.QuestionType;
import org.kodekittu.trippy.model.enums.SurveyStatus;
import org.kodekittu.trippy.service.ItineraryService;
import org.kodekittu.trippy.service.SurveyService;
import org.kodekittu.trippy.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SurveyEngine {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final SurveyService surveyService = new SurveyService();

    public void startSurvey(String userId, String surveyId) {

        Survey survey = surveyService.getSurvey(surveyId);

        if (surveyService.isExpired(survey)) {
            System.out.println("Survey expired");
            return;
        }

        SurveyResponse response = userService.getResponse(userId, surveyId);

        if (response == null) {
            response = new SurveyResponse(surveyId, userId);
            response.setCurrentQuestionId(survey.getFirstQuestionId());
        }

        runSurvey(survey, response);
    }

    private void runSurvey(Survey survey, SurveyResponse response) {

        while (response.getCurrentQuestionId() != null) {

            Question q = survey.getQuestion(response.getCurrentQuestionId());

            System.out.println("\nQ: " + q.getQuestion());

            for (Option opt : q.getOptions()) {
                System.out.println(opt.getId() + " - " + opt.getValue());
            }

            System.out.println("Type SKIP | PREVIEW | BACK");

            if (q.getType() == QuestionType.MULTI_SELECT) {
                System.out.println("Enter comma separated values (e.g. A1,A2)");
            }

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("BACK")) {
                moveBack(response);
                continue;
            }

            if (input.equalsIgnoreCase("PREVIEW")) {
                preview(response);
                continue;
            }

            Answer answer = new Answer(q.getId());

            if (input.equalsIgnoreCase("SKIP")) {
                answer.setSkipped(true);
            }
            else if (q.getType() == QuestionType.MULTI_SELECT) {
                answer.setSelectedOptions(Arrays.asList(input.split(",")));
            }
            else {
                answer.getSelectedOptions().add(input);
            }

            response.addAnswer(answer);

            String next = q.getNextQuestion(input.split(",")[0]);
            response.setCurrentQuestionId(next);

            userService.saveProgress(response);
        }

        response.setStatus(SurveyStatus.COMPLETED);
        userService.saveProgress(response);

        ItineraryService itineraryService = new ItineraryService();

        itineraryService.showItinerary(survey, response);
    }

    private void moveBack(SurveyResponse response) {

        List<String> keys = new ArrayList<>(response.getAnswers().keySet());

        if (keys.size() <= 1) return;

        String lastQ = keys.remove(keys.size() - 1);

        response.getAnswers().remove(lastQ);

        String prev = keys.get(keys.size() - 1);

        response.setCurrentQuestionId(prev);

        System.out.println("Moved back to: " + prev);
    }

    private void preview(SurveyResponse response) {

        System.out.println("\n--- PREVIEW ---");

        for (Answer a : response.getAnswers().values()) {
            System.out.println("QID: " + a.getQuestionId()
                    + " Answer: " + a.getSelectedOptions()
                    + " Text: " + a.getTextAnswer()
                    + " Skipped: " + a.isSkipped());
        }

        System.out.println("---------------\n");
    }
}