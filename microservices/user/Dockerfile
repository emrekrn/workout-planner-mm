FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY build/libs/*.jar /app/user.jar
ENTRYPOINT ["java", "-jar", "user.jar"]