package com.emrecan.workoutplanner.microservices.workout.service;

import com.emrecan.workoutplanner.microservices.workout.ApplicationContants;
import com.emrecan.workoutplanner.microservices.workout.persistence.ExerciseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExerciseServiceImpl  implements ExerciseService{

    private static final Logger log = LoggerFactory.getLogger(ExerciseServiceImpl.class);

    RestTemplate restTemplate;

    public ExerciseServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ExerciseDto> getExercisesFromApiNinjas() {
        String url = "https://api.api-ninjas.com/v1/exercises?muscle=biceps";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", ApplicationContants.API_NINJAS_API_KEY);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        log.info(response.getBody());

        return null;
    }
}
