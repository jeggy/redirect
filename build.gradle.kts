val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.2.20"
    id("io.ktor.plugin") version "3.3.2"
    id("com.google.cloud.tools.jib") version "3.5.1"
}

group = "fo.damkjaer"
version = "0.0.1"

application {
    mainClass = "fo.damkjaer.ApplicationKt"
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-cio")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-core")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

jib {
    from {
        // Use a lightweight Java 21 Alpine image as base
        image = "eclipse-temurin:21-jre-alpine"
    }
    to {
        // dynamic image name: uses your github secret user + project name
        // Or hardcode it: "docker.io/myuser/my-ktor-app"
        image = "docker.io/${System.getenv("DOCKER_USERNAME")}/ktor-app"
        tags = setOf("latest", version.toString())

        // Credentials are strictly read from Environment Variables for security
        auth {
            username = System.getenv("DOCKER_USERNAME")
            password = System.getenv("DOCKER_PASSWORD")
        }
    }
    container {
        ports = listOf("8080")
        creationTime = "USE_CURRENT_TIMESTAMP"
        // formatting for better logs
        jvmFlags = listOf("-Djava.security.egd=file:/dev/./urandom")
    }
}
