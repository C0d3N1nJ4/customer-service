# Stage 1: Build the application
FROM maven:3.8.4-eclipse-temurin-21 AS build
WORKDIR /customer-service
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /customer-service
COPY ../target/*.jar /customer-service/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/customer-service/application.jar"]
