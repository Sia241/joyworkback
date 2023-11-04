# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the WAR file from your build target into the container
COPY target/joyWorkBackend-0.0.1-SNAPSHOT.war backend-app.war

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the WAR file
CMD ["java", "-jar", "backend-app.war"]
