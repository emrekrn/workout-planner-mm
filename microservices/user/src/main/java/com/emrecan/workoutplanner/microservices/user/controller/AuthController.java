package com.emrecan.workoutplanner.microservices.user.controller;

import com.emrecan.workoutplanner.microservices.user.exceptions.UserAlreadyExistsException;
import com.emrecan.workoutplanner.util.JwtTokenUtil;
import com.emrecan.workoutplanner.microservices.user.persistence.LoginRequest;
import com.emrecan.workoutplanner.microservices.user.persistence.UserDto;
import com.emrecan.workoutplanner.microservices.user.service.UserService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
@Log
public class AuthController {

    UserService userService;
    JwtTokenUtil jwtTokenUtil;

    public AuthController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        if (userService.isValidUser(loginRequest.getUsername(), loginRequest.getPassword())) {
            String token = jwtTokenUtil.createToken(loginRequest.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto createdUserDto = userService.createUser(userDto);
            log.info("Created user : " + createdUserDto);
            String token = jwtTokenUtil.createToken(createdUserDto.getUsername());
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException e) {
            log.warning(e.getMessage());
            String response = "The email or the username already exists";
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
