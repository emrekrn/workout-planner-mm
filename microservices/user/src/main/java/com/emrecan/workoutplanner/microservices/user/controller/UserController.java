package com.emrecan.workoutplanner.microservices.user.controller;

import com.emrecan.workoutplanner.exceptions.UserNotFoundException;
import com.emrecan.workoutplanner.microservices.user.persistence.UserDto;
import com.emrecan.workoutplanner.microservices.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername (@PathVariable String username) throws UserNotFoundException {
        UserDto userDto = userService.getUserByUsername(username);

        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestParam String username, @RequestBody UserDto userDto) throws UserNotFoundException {

        UserDto updatedUser = userService.updateUser(username, userDto);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) throws UserNotFoundException {
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
