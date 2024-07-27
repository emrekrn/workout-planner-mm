package com.emrecan.workoutplanner.microservices.composite_service.controllers;

import com.emrecan.workoutplanner.exceptions.InvalidTokenException;
import com.emrecan.workoutplanner.exceptions.MissingTokenException;
import com.emrecan.workoutplanner.exceptions.UserConflictException;
import com.emrecan.workoutplanner.exceptions.UserNotFoundException;
import com.emrecan.workoutplanner.microservices.composite_service.dtos.LoginRequest;
import com.emrecan.workoutplanner.microservices.composite_service.dtos.UserDto;
import com.emrecan.workoutplanner.microservices.composite_service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto) throws UserConflictException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String token, @PathVariable String username) throws UserNotFoundException, InvalidTokenException, MissingTokenException {
        if (isTokenValid(token)) {
            UserDto userDto = userService.getUserByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(userDto);
        }
        else {
            throw new InvalidTokenException("Token token '" + token + "' is invalid.");
        }
    }

    @PutMapping("/user/{username}")
    public ResponseEntity<UserDto> updateUser(@RequestHeader("Authorization") String token, @PathVariable String username, @RequestBody UserDto userDto) throws UserNotFoundException, InvalidTokenException, MissingTokenException {
        if (isTokenValid(token)) {
            UserDto updatedUserDto = userService.updateUser(username, userDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUserDto);
        }
        else {
            throw new InvalidTokenException("Token token '" + token + "' is invalid.");
        }

    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token, @PathVariable String username) throws UserNotFoundException, InvalidTokenException, MissingTokenException {
        if (isTokenValid(token)) {
            userService.deleteUserByUsername(username);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            throw new InvalidTokenException("Token token '" + token + "' is invalid.");
        }
    }



    private boolean isTokenValid(String token) throws InvalidTokenException, MissingTokenException {
        if (token == null) {
            throw new MissingTokenException("Token is missing");
        }
        else return userService.isTokenValid(token);
    }


}
