# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /target/customer-service-0.0.1-SNAPSHOT.jar /app/customer-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/customer-service.jar"]
