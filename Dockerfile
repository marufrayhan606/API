FROM gradle:8.2.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew buildFatJar

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
