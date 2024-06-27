plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.9.0"
}

repositories {
    maven("https://jitpack.io")
    mavenCentral()
    mavenLocal()
    google()
}

//kotlin {
//    jvmToolchain(17)
//}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}