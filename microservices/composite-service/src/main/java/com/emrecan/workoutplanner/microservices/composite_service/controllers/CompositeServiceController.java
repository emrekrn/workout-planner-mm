package com.emrecan.workoutplanner.microservices.composite_service.controllers;

import com.emrecan.workoutplanner.microservices.composite_service.dtos.LoginRequest;
import com.emrecan.workoutplanner.microservices.composite_service.dtos.UserDto;
import com.emrecan.workoutplanner.microservices.composite_service.services.CompositeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CompositeServiceController {

    CompositeService compositeService;

    public CompositeServiceController(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(compositeService.loginUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compositeService.createUser(userDto));
    }

    @GetMapping("/get-workouts")
    public ResponseEntity<?> getWorkouts(@RequestHeader("Authorization") String token) {
        if (!compositeService.isTokenValid(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
            return ResponseEntity.status(HttpStatus.OK).body("Valid token");
    }


}
