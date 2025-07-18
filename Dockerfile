# Use lightweight Java 21 image
FROM openjdk:21-slim

# Set the JAR location (argument will be replaced by actual JAR)
ARG JAR_FILE=target/*.jar

# Copy JAR into container
COPY ${JAR_FILE} app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "/app.jar"]
