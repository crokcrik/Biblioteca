# Fase 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build
# Copiamo tutto il contenuto della cartella Biblioteca
COPY Biblioteca/ .
RUN mvn clean package -DskipTests

# Fase 2: Run
FROM eclipse-temurin:21-jdk-jammy
# Il JAR si troverà dentro target/ dopo la build
COPY --from=build /target/Biblioteca-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]