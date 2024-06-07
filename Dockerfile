# Construcción
FROM maven:3.8.4-openjdk-17-slim as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
# Ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/challenge.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/challenge.jar", "--spring.profiles.active=prod"]