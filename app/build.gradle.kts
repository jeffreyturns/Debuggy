import java.io.File
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31

    buildFeatures {
        viewBinding = true
    }

    fun getCountBuild(): Int {
        val versionProps = Properties()
        val versionPropsFile = File("version.properties")
        if (versionPropsFile.exists())
            versionProps.load(FileInputStream(versionPropsFile))
        val code = (versionProps["VERSION_CODE"] ?: "0").toString().toInt() + 1
        versionProps["VERSION_CODE"] = code.toString()
        versionProps.store(versionPropsFile.writer(), null)
        return code
    }

    defaultConfig {
        buildConfigField("long", "BUILD_TIME", "${System.currentTimeMillis()}L")
        applicationId = "com.jeffrey.debuggy"
        minSdk = 22
        targetSdk = 31
        versionCode = 1
        versionName = "1.0." + getCountBuild()

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("androidx.preference:preference-ktx:1.1.1")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.browser:browser:1.3.0")

    implementation("com.google.android.material:material:1.5.0-alpha03")

    implementation("io.coil-kt:coil:1.3.2")
    implementation ("io.insert-koin:koin-android:3.1.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}