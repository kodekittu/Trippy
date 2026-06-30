package org.kodekittu.trippy.service;

import org.kodekittu.trippy.database.TrippyStore;
import org.kodekittu.trippy.model.Answer;
import org.kodekittu.trippy.model.SurveyResponse;
import org.kodekittu.trippy.model.enums.SurveyStatus;

import java.util.*;

public class AnalyticsService {

    public void showCountryStats() {

        Map<String, Integer> map = new HashMap<>();

        for (SurveyResponse r : TrippyStore.responses.values()) {

            Answer a = r.getAnswers().get("Q1");
            if (a == null || a.isSkipped()) continue;

            String country = a.getSelectedOptions().isEmpty()
                    ? "NA"
                    : a.getSelectedOptions().get(0);

            map.put(country, map.getOrDefault(country, 0) + 1);
        }

        System.out.println("\n-- Country Stats --");
        for (String k : map.keySet()) {
            System.out.println(k + " : " + map.get(k));
        }
    }

    public void showCityStats() {

        Map<String, Integer> map = new HashMap<>();

        for (SurveyResponse r : TrippyStore.responses.values()) {

            for (Answer a : r.getAnswers().values()) {

                if (a.getQuestionId().startsWith("Q_") &&
                        !a.isSkipped() &&
                        !a.getSelectedOptions().isEmpty()) {

                    String city = a.getSelectedOptions().get(0);
                    map.put(city, map.getOrDefault(city, 0) + 1);
                }
            }
        }

        System.out.println("\n-- City Stats --");
        for (String k : map.keySet()) {
            System.out.println(k + " : " + map.get(k));
        }
    }

    public void showCompletionRate() {

        int total = TrippyStore.responses.size();
        int completed = 0;

        for (SurveyResponse r : TrippyStore.responses.values()) {
            if (r.getStatus() == SurveyStatus.COMPLETED) {
                completed++;
            }
        }

        System.out.println("\nCompletion Rate: " +
                (total == 0 ? 0 : (completed * 100 / total)) + "%");
    }
}
