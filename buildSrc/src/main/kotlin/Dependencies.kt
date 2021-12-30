import org.gradle.api.JavaVersion

object Application {
    const val minSdk = 22
    const val targetSdk = 31
    const val compileSdk = 31
    const val jvmTarget = "1.8"
    const val versionCode = 2
    const val versionName = "1.1.0"

    val targetCompat = JavaVersion.VERSION_1_8
    val sourceCompat = JavaVersion.VERSION_1_8
}

object Versions {
    object Essential {
        const val Kotlin = "1.5.30"
        const val Ktx = "1.7.0"
        const val Coroutines = "1.3.9"
        const val Gradle = "7.0.3"
    }

    object AndroidX {
        const val AppCompat = "1.3.1"
        const val ConstraintLayout = "2.1.2"
        const val Preference = "1.1.1"
        const val RecyclerView = "1.2.1"
        const val NavigationFragment = "2.3.5"
        const val NavigationUI = "2.3.5"
        const val LifecycleExtensions = "2.2.0"
        const val LegacySupport = "1.0.0"
        const val Browser = "1.4.0"
        const val Biometric = "1.1.0"
        const val Work = "2.7.1"
    }

    object UI {
        const val MaterialComponents = "1.5.0-rc01"
        const val Coil = "1.3.2"
    }

    object DI {
        const val Koin = "3.1.2"
    }

    object Test {
        const val Junit = "4.13.2"
    }

    object AndroidTest {
        const val JunitExt = "1.1.3"
        const val Espresso = "3.4.0"
    }
}

object Dependencies {
    val essential = listOf(
        "androidx.core:core-ktx:${Versions.Essential.Ktx}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Essential.Coroutines}"
    )

    val androidx = listOf(
        "androidx.appcompat:appcompat:${Versions.AndroidX.AppCompat}",
        "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.ConstraintLayout}",
        "androidx.preference:preference-ktx:${Versions.AndroidX.Preference}",
        "androidx.recyclerview:recyclerview:${Versions.AndroidX.RecyclerView}",
        "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.NavigationFragment}",
        "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.NavigationUI}",
        "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.LifecycleExtensions}",
        "androidx.legacy:legacy-support-v4:${Versions.AndroidX.LegacySupport}",
        "androidx.browser:browser:${Versions.AndroidX.Browser}",
        "androidx.biometric:biometric:${Versions.AndroidX.Biometric}",
        "androidx.work:work-runtime-ktx:${Versions.AndroidX.Work}"
    )

    val ui = listOf(
        "com.google.android.material:material:${Versions.UI.MaterialComponents}",
        "io.coil-kt:coil:${Versions.UI.Coil}"
    )

    val di = listOf(
        "io.insert-koin:koin-android:${Versions.DI.Koin}"
    )

    val test = listOf(
        "junit:junit:${Versions.Test.Junit}"
    )

    val androidtest = listOf(
        "androidx.test.ext:junit:${Versions.AndroidTest.JunitExt}",
        "androidx.test.espresso:espresso-core:${Versions.AndroidTest.Espresso}"
    )
}