package org.kodekittu.trippy.service;

import org.kodekittu.trippy.database.TrippyStore;
import org.kodekittu.trippy.model.SurveyResponse;
import org.kodekittu.trippy.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public User createUser(String id, String name) {
        User user = new User(id, name);
        TrippyStore.users.put(id, user);
        return user;
    }

    public User getUser(String userId) {
        return TrippyStore.users.get(userId);
    }

    public void saveProgress(SurveyResponse response) {
        String key = TrippyStore.getResponseKey(
                response.getUserId(),
                response.getSurveyId()
        );
        TrippyStore.responses.put(key, response);
    }

    public SurveyResponse getResponse(String userId, String surveyId) {
        return TrippyStore.responses.get(
                TrippyStore.getResponseKey(userId, surveyId)
        );
    }

    public List<SurveyResponse> getAllResponsesForUser(String userId) {
        List<SurveyResponse> list = new ArrayList<>();

        for (SurveyResponse r : TrippyStore.responses.values()) {
            if (r.getUserId().equals(userId)) {
                list.add(r);
            }
        }
        return list;
    }
}
