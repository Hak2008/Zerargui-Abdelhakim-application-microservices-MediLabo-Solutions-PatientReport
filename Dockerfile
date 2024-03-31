# base image containing Java 17
FROM openjdk:17-jdk-slim

# working directory in container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/PatientReport-0.0.1-SNAPSHOT.jar /app/app.jar

# Port on which the application is running
EXPOSE 8082

# Command to execute when the container starts
CMD ["java", "-jar", "-Dserver.address=0.0.0.0", "-Dserver.port=8082", "app.jar"]
