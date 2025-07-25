plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    mavenCentral()
}


kotlin {
    jvmToolchain(22) // Align Kotlin with Java 22
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22)) // ✅ match Kotlin
    }
}



dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-core:2.3.5")
    implementation("io.ktor:ktor-server-netty:2.3.5")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
    implementation("ch.qos.logback:logback-classic:1.4.14") // logging
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

}

application {
    mainClass.set("MainKt")
}


tasks.test {
    useJUnitPlatform()
}