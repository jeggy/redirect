import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform") version "2.2.20"
}

group = "fo.damkjaer"
version = "0.0.1"

kotlin {
    linuxX64 {
        binaries {
            executable {
                baseName = rootProject.name
                entryPoint = "main"
            }
        }
    }
    macosArm64 {
        binaries {
            executable {
                baseName = rootProject.name
                entryPoint = "main"
            }
        }
    }
    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation("io.ktor:ktor-server-core:3.3.2")
            implementation("io.ktor:ktor-server-cio:3.3.2") // CIO engine works on JVM, Linux, and macOS
        }
    }
}