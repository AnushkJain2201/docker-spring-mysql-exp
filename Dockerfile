# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory for Maven
WORKDIR /app

# Copy the Maven pom.xml and download dependencies
COPY pom.xml /app
RUN mvn dependency:go-offline

# Copy the source code and package the application
COPY src /app/src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Set the working directory for the application
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/UserModule-0.0.1-SNAPSHOT.jar /app/UserModule.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "UserModule.jar"]
