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
    jvmToolchain(17) // Align Kotlin with Java 22
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // ✅ match Kotlin
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

tasks.jar {
    archiveBaseName.set("ktor-api")  // 👈 Your custom app name
    archiveVersion.set("1.0")
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
}

tasks.register<Jar>("buildFatJar") {
    group = "build"
    archiveBaseName.set("ktor-api")
    archiveVersion.set("1.0")
    manifest {
        attributes["Main-Class"] = "MainKt"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE  // 👈 This line resolves the module-info conflict

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().map {
            if (it.isDirectory) it else zipTree(it)
        }
    })
}
tasks.test {
    useJUnitPlatform()
}
