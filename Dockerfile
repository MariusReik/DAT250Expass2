# --- STAGE 1: Build the application ---
FROM gradle:8.5-jdk17 AS builder
WORKDIR /home/gradle/project

# Copy only what's needed to build (faster builds)
COPY build.gradle.kts settings.gradle.kts gradlew gradlew.bat ./
COPY gradle gradle
COPY src src

# Build the Spring Boot jar
RUN ./gradlew clean bootJar --no-daemon

# --- STAGE 2: Run the application ---
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the jar from the build image
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# Create non-root user
RUN useradd -m appuser
USER appuser

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
