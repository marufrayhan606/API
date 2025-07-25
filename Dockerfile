FROM gradle:8.2.0-jdk17
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean buildFatJar
CMD ["java", "-jar", "build/libs/ktor-api-1.0.jar"]
