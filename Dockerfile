FROM openjdk:21-jdk-slim
LABEL authors="Naiomi"
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY ./target/customer-0.0.1-SNAPSHOT.jar /app/customer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]