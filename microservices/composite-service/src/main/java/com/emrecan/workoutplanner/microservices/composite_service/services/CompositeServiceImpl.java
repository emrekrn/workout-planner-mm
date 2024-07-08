package com.emrecan.workoutplanner.microservices.composite_service.services;

import com.emrecan.workoutplanner.microservices.composite_service.dtos.LoginRequest;
import com.emrecan.workoutplanner.microservices.composite_service.dtos.UserDto;
import com.emrecan.workoutplanner.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompositeServiceImpl implements CompositeService {

    RestTemplate restTemplate;
    JwtTokenUtil jwtTokenUtil;

    private final String userServiceUrl;
    private final String workoutServiceUrl;

    public CompositeServiceImpl (
            RestTemplate restTemplate,
            JwtTokenUtil jwtTokenUtil,
            @Value("${app.user-service.host}") String userServiceHost,
            @Value("${app.user-service.port}") int userServicePort,
            @Value("${app.workout-service.host}") String workoutServiceHost,
            @Value("${app.workout-service.port}") int workoutServicePort

    ) {
        this.restTemplate = restTemplate;
        this.jwtTokenUtil = jwtTokenUtil;

        this.userServiceUrl = "http://" + userServiceHost + ":" + userServicePort;
        this.workoutServiceUrl = "http://" + workoutServiceHost + ":" + workoutServicePort;

    }

    @Override
    public String loginUser(LoginRequest loginRequest) {
        String requestUrl = userServiceUrl + "/login";

        return restTemplate.postForObject(requestUrl, loginRequest, String.class);
    }

    @Override
    public String createUser(UserDto userDto) {
        String requestUrl = userServiceUrl + "/signup";

        return restTemplate.postForObject(requestUrl, userDto, String.class);
    }


    @Override
    public boolean isTokenValid (String token) {
        if (token != null && !token.isEmpty()) {
            return jwtTokenUtil.validateToken(token);
        }
        return false;
    }
}
