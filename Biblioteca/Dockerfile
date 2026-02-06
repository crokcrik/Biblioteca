# Fase 1: Build (usiamo Maven per compilare il codice)
FROM maven:3.9.9-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Fase 2: Run (usiamo solo Java per far girare il JAR creato sopra)
FROM eclipse-temurin:21-jdk-jammy
COPY --from=build /target/Biblioteca-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]