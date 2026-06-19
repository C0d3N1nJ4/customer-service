# Multi-stage build: assumes JAR is built locally
# To build: export PATH=/usr/lib/jvm/msopenjdk-25-amd64/bin:$PATH && export JAVA_HOME=/usr/lib/jvm/msopenjdk-25-amd64 && mvn clean package -DskipTests

# Runtime stage with Java 25
FROM eclipse-temurin:25-jdk-alpine
WORKDIR /customer-service
COPY target/customer-0.0.1-SNAPSHOT.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]
