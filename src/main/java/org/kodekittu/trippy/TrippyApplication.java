package org.kodekittu.trippy;

import org.kodekittu.trippy.model.Survey;
import org.kodekittu.trippy.model.enums.SurveyStatus;
import org.kodekittu.trippy.service.*;
import org.kodekittu.trippy.utils.SurveyHelper;
import org.kodekittu.trippy.model.SurveyResponse;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class TrippyApplication {

  //  public static void main(String[] args) {
   //     SpringApplication.run(TrippyApplication.class, args);
  //  }

    private static final Scanner scanner = new Scanner(System.in);

    private static final SurveyService surveyService = new SurveyService();
    private static final SurveyEngine engine = new SurveyEngine();
    private static final UserService userService = new UserService();
    private static final AnalyticsService analytics = new AnalyticsService();
    private static final AdminService admin = new AdminService();
    private static final ItineraryService itineraryService = new ItineraryService();
    private static final Logger logger = Logger.getLogger(TrippyApplication.class.getName());

    public static void main(String[] args) {

        SurveyHelper.loadSurveys();

        System.out.println("Enter User Id:");
        String userId = scanner.nextLine();

        System.out.println("Enter Name:");
        String name = scanner.nextLine();

        userService.createUser(userId, name);

        while (true) {

            System.out.println("\n--- MENU ---");
            System.out.println("1. Show Surveys");
            System.out.println("2. Start Survey");
            System.out.println("3. My Surveys");
            System.out.println("4. Analytics");
            System.out.println("5. Admin");
            System.out.println("6. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    showSurveys();
                    break;

                case 2:
                    System.out.println("Enter Survey Id:");
                    String sid = scanner.nextLine();
                    engine.startSurvey(userId, sid);
                    break;

                case 3:
                    showMySurveys(userId);
                    break;

                case 4:
                    System.out.println("1. Country");
                    System.out.println("2. City");
                    System.out.println("3. Completion Rate");

                    int c = Integer.parseInt(scanner.nextLine());

                    if(c == 1) analytics.showCountryStats();
                    else if(c == 2) analytics.showCityStats();
                    else analytics.showCompletionRate();

                    break;

                case 5:
                    System.out.println("1. Create Survey");
                    System.out.println("2. List Surveys");

                    int a = Integer.parseInt(scanner.nextLine());

                    if(a == 1) admin.createSurvey();
                    else admin.listSurveys();

                    break;

                default:
                    return;
            }
        }
    }

    static void showSurveys() {
        List<Survey> surveys = surveyService.getAllSurveys();

        if (surveys.isEmpty()) {
            System.out.println("no survey present");
            return;
        }

        for (Survey survey : surveys) {
            if (survey == null) {
                logger.warning("Skipping null survey entry");
                System.out.println("survey details not found");
                continue;
            }

            System.out.println(survey.getId() + " - " + survey.getName());
        }
    }

    static void showMySurveys(String userId) {
        if (userId == null || userId.isBlank()) {
            logger.warning("User id is missing while checking surveys");
            System.out.println("user id is required");
            return;
        }

        List<SurveyResponse> responses = userService.getAllResponsesForUser(userId);

        if (responses.isEmpty()) {
            System.out.println("no survey present");
            return;
        }

        for (SurveyResponse response : responses) {
            if (response == null) {
                logger.warning("Skipping null survey response for user id: " + userId);
                System.out.println("survey response details not found");
                continue;
            }

            Survey survey = surveyService.getSurvey(response.getSurveyId());

            if (survey == null) {
                logger.warning("Survey details not found for survey id: " + response.getSurveyId());
                System.out.println("survey details not found");
                continue;
            }

            System.out.println("--------------------------------");
            System.out.println("Survey : " + survey.getName());
            System.out.println("Status : " + response.getStatus());

            if (response.getStatus() == SurveyStatus.COMPLETED) {
                itineraryService.showItinerary(survey, response);
            }

            System.out.println("--------------------------------");
        }
    }

}
