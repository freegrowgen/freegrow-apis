# Step 1: Use an OpenJDK base image for the build stage
FROM openjdk:19-jdk AS build
WORKDIR /app

# Copy the Maven wrapper and necessary files
COPY mvnw .
COPY .mvn .mvn/
COPY pom.xml .
COPY src src

# Set execution permission for the Maven wrapper
RUN chmod +x ./mvnw

# Build the project and skip tests
RUN ./mvnw clean package -DskipTests

# Step 2: Create the final Docker image using OpenJDK 19
FROM openjdk:19-jdk
VOLUME /tmp

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port the app will run on
EXPOSE 8080
