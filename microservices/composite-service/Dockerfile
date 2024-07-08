FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY build/libs/*.jar /app/composite-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "composite-service.jar"]