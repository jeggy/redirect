import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform") version "2.2.20"
    id("io.ktor.plugin") version "3.3.2"
}

group = "fo.damkjaer"
version = "0.0.1"

kotlin {
    linuxX64("linux") {
        binaries {
            executable {
                baseName = rootProject.name
                entryPoint = "main"
            }
        }
    }
    macosArm64("macos") {
        binaries {
            executable {
                baseName = rootProject.name
                entryPoint = "main"
            }
        }
    }
    jvm("jvm") {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation("io.ktor:ktor-server-core")
            implementation("io.ktor:ktor-server-cio") // CIO engine works on JVM, Linux, and macOS
        }
    }
}