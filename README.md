# Workout Planner App Backend

Project URL: https://github.com/users/emrekrn/projects/5/views/1

## Microservices:
 - Composite Service
 - User Service
 - Workout service

## Other artifacts:
 - util



## Artifacts:

### Composite Service

 - Receives requests from client
 - Validate token using util artifact
 - Manage the communication between micro services


###  User Service

 - Responsible for authentication
 - Includes login and signup methods

### Workout Service

 - Workout service uses API Ninjas to get exercises.
 - Documentation for exercise API: 
   - https://api-ninjas.com/api/exercises
 - Users are able to create workouts. A Workout consist of multiple exercises
 - User can create his own exercise/s or choose exercise/s provided by the above API.


### Util

 - Methods for token (creation and validation of token)