allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.Essential.Gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Essential.Kotlin}")
        classpath ("com.github.ben-manes:gradle-versions-plugin:0.38.0")
    }
    repositories {
        gradlePluginPortal()
        google()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}