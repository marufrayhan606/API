# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Make gradlew executable AFTER the copy
RUN chmod +x gradlew

# Run the build with cache mount
RUN --mount=type=cache,id=s/gradle-5b65b6ed-ca52-496f-8dde-8641c283aa46-/root/gradle,target=/root/.gradle ./gradlew buildFatJar
