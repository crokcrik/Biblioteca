# Fase 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Fase 2: Run
FROM eclipse-temurin:21-jdk-jammy
# Rimuoviamo "Biblioteca/" dal percorso perché siamo già dentro quella cartella
COPY --from=build /target/Biblioteca-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]