import org.gradle.api.JavaVersion

object Application {
    const val minSdk = 22
    const val targetSdk = 34
    const val compileSdk = 34
    const val jvmTarget = "17"
    const val versionCode = 3
    const val versionName = "2.0.0"

    val targetCompat = JavaVersion.VERSION_17
    val sourceCompat = JavaVersion.VERSION_17
}

object Versions {
    object Essential {
        const val Kotlin = "2.0.0"
        const val Ktx = "1.13.1"
        const val Coroutines = "1.6.0"
        const val Gradle = "8.2.2"
    }

    object AndroidX {
        const val AppCompat = "1.6.0-alpha01"
        const val ConstraintLayout = "2.1.3"
        const val Preference = "1.2.0"
        const val RecyclerView = "1.3.0-alpha01"
        const val NavigationFragment = "2.5.0-alpha03"
        const val NavigationUI = "2.5.0-alpha03"
        const val LifecycleExtensions = "2.2.0"
        const val Browser = "1.4.0"
        const val Biometric = "1.2.0-alpha04"
        const val Work = "2.8.0-alpha01"
    }

    object UI {
        const val MaterialComponents = "1.12.0"
        const val Coil = "2.0.0-rc01"
    }

    object DI {
        const val Koin = "3.2.0-beta-1"
    }

    object Test {
        const val Junit = "4.13.2"
    }

    object AndroidTest {
        const val JunitExt = "1.1.4-alpha04"
        const val Espresso = "3.5.0-alpha04"
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