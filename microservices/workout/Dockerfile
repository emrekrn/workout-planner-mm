FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY build/libs/*.jar /app/user.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user.jar"]