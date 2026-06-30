package org.kodekittu.trippy.service;

import org.kodekittu.trippy.database.TrippyStore;
import org.kodekittu.trippy.model.Survey;

import java.time.LocalDate;
import java.util.Scanner;

public class AdminService {

    private Scanner sc = new Scanner(System.in);

    public void createSurvey() {

        System.out.println("Survey Id:");
        String id = sc.nextLine();

        System.out.println("Survey Name:");
        String name = sc.nextLine();

        Survey survey = new Survey(
                id,
                name,
                LocalDate.now().plusDays(30)
        );

        TrippyStore.surveys.put(id, survey);

        System.out.println("Survey created");
    }

    public void listSurveys() {

        System.out.println("\n-- Surveys --");

        for (Survey s : TrippyStore.surveys.values()) {
            System.out.println(s.getId() + " - " + s.getName());
        }
    }
}
