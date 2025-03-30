# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target folder into the container
COPY target/_03_HAK_KIMHENG_KPS_SPRING_HOMEWORK003-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port the app will run on (default for Spring Boot is 8080)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]