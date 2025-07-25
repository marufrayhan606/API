FROM gradle:8.2.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean buildFatJar

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/build/libs/ktor-api-1.0.jar
CMD ["java", "-jar", "ktor-api-1.0.jar"]
