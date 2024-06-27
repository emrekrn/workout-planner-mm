package com.emrecan.workoutplanner.microservices.workout.service;

import com.emrecan.workoutplanner.microservices.workout.ApplicationContants;
import com.emrecan.workoutplanner.microservices.workout.persistence.ExerciseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ExerciseServiceImpl  implements ExerciseService{

    private static final Logger log = LoggerFactory.getLogger(ExerciseServiceImpl.class);

    RestTemplate restTemplate;
    ObjectMapper objectMapper;

    public ExerciseServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ExerciseDto> getExercisesFromApiNinjas(
        String exerciseName,
        String exerciseType,
        String muscleGroup,
        String difficulty
    ) {

        HttpEntity<String> entity = getHttpEntity();
        String url = buildUrlForExercises(exerciseName, exerciseType, muscleGroup, difficulty);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        log.info(response.getBody());
        return stringToJson(response.getBody());
    }

    /**
     * Get HttpEntity for Request header
     * @return
     */
    private HttpEntity<String> getHttpEntity () {
        HttpHeaders headers = new HttpHeaders();
        addFieldToHeader(headers, "X-Api-Key", ApplicationContants.API_NINJAS_API_KEY);
        return new HttpEntity<>(headers);
    }

    /**
     * Add field to request header
     * @param header HttpHeader
     * @param headerKey Key of the header
     * @param headerValue Value of the header
     */
    private void addFieldToHeader (HttpHeaders header, String headerKey, String headerValue) {
        header.set(headerKey, headerValue);
    }

    /**
     * Get available exercises from API Ninjas based in url parameters
     * @param exerciseName Exercise name
     * @param exerciseType Exercise type
     * @param muscleGroup Muscle group
     * @param difficulty Difficulty of the exercise
     * @return Final url
     */
    private String buildUrlForExercises (
        String exerciseName,
        String exerciseType,
        String muscleGroup,
        String difficulty
    ) {
        String baseUrl = ApplicationContants.API_NINJAS_BASE_URL;
        StringBuilder url = new StringBuilder(baseUrl);

        boolean isFirstParam = true;

        isFirstParam = appendParam(url, "name", exerciseName, isFirstParam);
        isFirstParam = appendParam(url, "type", exerciseType, isFirstParam);
        isFirstParam = appendParam(url, "muscle", muscleGroup, isFirstParam);
        appendParam(url, "difficulty", difficulty, isFirstParam);

        return url.toString();
    }

    /**
     * Append the url. If parameter value is null, do not append it
     * @param urlBuilder Url
     * @param paramName Name of the url parameter
     * @param paramValue Value of the url parameter
     * @param isFirstParam Check if the parameter is the first Url parameter
     * @return appended url parameter
     */
    private boolean appendParam(StringBuilder urlBuilder, String paramName, String paramValue, boolean isFirstParam) {
        if (paramValue != null && !paramValue.isEmpty()) {
            if (isFirstParam) {
                urlBuilder.append("?");
                isFirstParam = false;
            }
            else {
                urlBuilder.append("&");
            }
            urlBuilder.append(paramName).append("=").append(paramValue);
        }
        return isFirstParam;
    }


    // TODO Put the following method in a utils artifact, since it going also going to be used by other services.
    /**
     * Converts response body to list
     * @param jsonString Response body as string
     * @return response body as List
     * @param <T> Type for the list
     */
    private <T> List<T> stringToJson(String jsonString){
        try {
            return objectMapper.readValue(jsonString, new TypeReference<>() {});
        }
        catch (JsonProcessingException e) {
            return Collections.emptyList();
        }
    }
}
