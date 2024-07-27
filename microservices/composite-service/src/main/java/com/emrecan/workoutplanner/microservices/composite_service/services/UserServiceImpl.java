package com.emrecan.workoutplanner.microservices.composite_service.services;

import com.emrecan.workoutplanner.exceptions.UserConflictException;
import com.emrecan.workoutplanner.exceptions.UserNotFoundException;
import com.emrecan.workoutplanner.microservices.composite_service.dtos.LoginRequest;
import com.emrecan.workoutplanner.microservices.composite_service.dtos.UserDto;
import com.emrecan.workoutplanner.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    RestTemplate restTemplate;
    JwtTokenUtil jwtTokenUtil;

    private final String userServiceUrl;
    private final String workoutServiceUrl;

    public UserServiceImpl(
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
    public String createUser(UserDto userDto) throws UserConflictException {
        String requestUrl = userServiceUrl + "/signup";
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, userDto, String.class);

            return response.getBody();
        }
        catch (HttpClientErrorException e) {
            throw new UserConflictException("Email or username already exists");
        }
    }

    @Override
    public UserDto getUserByUsername(String username) throws UserNotFoundException {
        try {
            String requestUrl = userServiceUrl + "/user/" + username;
            return restTemplate.getForObject(requestUrl, UserDto.class);
        }
        catch (HttpClientErrorException e) {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public UserDto updateUser(String username, UserDto userDto) throws UserNotFoundException {
        try {
            String requestUrl = userServiceUrl + "/user/update/" + username;
            HttpEntity<UserDto> requestEntity = new HttpEntity<>(userDto);

            ResponseEntity<UserDto> response = restTemplate.exchange(requestUrl, HttpMethod.PUT, requestEntity, UserDto.class);

            return response.getBody();
        }
        catch (HttpClientErrorException e) {
            log.error("Error while updating user. Reason: {}", e.getMessage());
            throw new UserNotFoundException("User sevice did not return an updated UserDto");
        }
    }

    @Override
    public void deleteUserByUsername(String username) throws UserNotFoundException {
        try {
            String requestUrl = userServiceUrl + "/user/" + username;
            restTemplate.delete(requestUrl);
        }
        catch (HttpClientErrorException e) {
            log.error("Error while deleting user. Reason: {}", e.getMessage());
            throw new UserNotFoundException("User could be deleted");
        }
    }


    @Override
    public boolean isTokenValid (String token) {
        if (token != null && !token.isEmpty()) {
            return jwtTokenUtil.validateToken(token);
        }
        return false;
    }
}
