FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY build/libs/*.jar /app/workout.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "workout.jar"]