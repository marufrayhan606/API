# Copy everything
COPY . /app/.

# Make gradlew executable
RUN chmod +x gradlew

# Run the build
RUN --mount=type=cache,id=s/5b65b6ed-ca52-496f-8dde-8641c283aa46-/root/gradle,target=/root/.gradle ./gradlew buildFatJar
