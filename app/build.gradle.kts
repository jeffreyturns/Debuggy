plugins {
    id("com.android.application")
    kotlin("android")
    id ("com.github.ben-manes.versions")
}

android {
    compileSdk = Application.compileSdk

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        buildConfigField("long", "BUILD_TIME", "${System.currentTimeMillis()}L")

        applicationId = "com.jeffrey.debuggy"
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        versionCode = Application.versionCode
        versionName = Application.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            versionNameSuffix = ".release"
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("debug") {
            versionNameSuffix = ".debug"
            isDebuggable = true
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = Application.sourceCompat
        targetCompatibility = Application.targetCompat
    }
    kotlinOptions {
        jvmTarget = Application.jvmTarget
    }
}

dependencies {
    Dependencies.essential.forEach(::implementation)
    Dependencies.androidx.forEach(::implementation)
    Dependencies.ui.forEach(::implementation)
    Dependencies.di.forEach(::implementation)

    Dependencies.test.forEach(::testImplementation)
    Dependencies.androidtest.forEach(::androidTestImplementation)
}