package com.emrecan.workoutplanner.microservices.workout.controller;

import com.emrecan.workoutplanner.microservices.workout.persistence.WorkoutDto;
import com.emrecan.workoutplanner.microservices.workout.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutDto>> getWorkoutsByUserId(@PathVariable String userId) {
        List<WorkoutDto> workouts = workoutService.getAllWorkoutsByUserId(userId);
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto) {
        WorkoutDto workout = workoutService.createWorkout(workoutDto);
        return new ResponseEntity<>(workout, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<WorkoutDto> updateWorkout(@RequestBody WorkoutDto workoutDto) {
        Optional<WorkoutDto> updatedWorkoutDto = workoutService.updateWorkout(workoutDto);

        return updatedWorkoutDto
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/")
    public HttpStatus deleteWorkout(String workoutId) {
        boolean isDeleted = workoutService.deleteWorkout(workoutId);

        if (isDeleted) {
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
