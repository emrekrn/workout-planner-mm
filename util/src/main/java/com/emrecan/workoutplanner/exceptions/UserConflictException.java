package com.emrecan.workoutplanner.exceptions;

public class UserConflictException extends Exception {
    public UserConflictException(String message) {
        super(message);
    }
}
