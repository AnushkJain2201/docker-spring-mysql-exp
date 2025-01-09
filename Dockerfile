# Use official OpenJDK image as base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/UserModule-0.0.1-SNAPSHOT.jar /app/UserModule.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "UserModule.jar"]
