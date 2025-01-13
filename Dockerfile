# Use an official Maven image with OpenJDK as a parent image for the build stage
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the local pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Run Maven to build the JAR file
RUN mvn clean package -DskipTests

# Use a lighter base image for the final stage
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file from the build stage to the final image
COPY --from=build /app/target/UserModule-0.0.1-SNAPSHOT.jar /app/UserModule.jar

# Expose the port your application runs on
EXPOSE 8080

# Set the default command to run the application
ENTRYPOINT ["java", "-jar", "UserModule.jar"]
