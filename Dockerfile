# Use a Maven base image to build the JAR
FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build the JAR
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar
COPY .env .env
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]