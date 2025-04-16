# Stage 1: Build the project with Maven
FROM openjdk:19-jdk AS build
WORKDIR /app

# Copy Maven wrapper scripts and .mvn directory
COPY mvnw . 
COPY .mvn .mvn  # Ensure .mvn is copied
COPY pom.xml .
COPY src src

# Set execution permission for the Maven wrapper
RUN chmod +x ./mvnw

# Build the project using the Maven wrapper (skip tests)
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final Docker image using OpenJDK 19
FROM openjdk:19-jdk
VOLUME /tmp

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080
