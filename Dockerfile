# Use an official OpenJDK runtime as a parent image
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY --from=build /app/target/UserModule-0.0.1-SNAPSHOT.jar /app/UserModule.jar

# Expose the port your application runs on
EXPOSE 8080

# Set the default command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
