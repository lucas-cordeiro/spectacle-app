private object ProjectVersions {
    const val kotlin = "1.6.10"
    const val googleServices = "4.3.4"
    const val gradle = "7.1.2"
    const val gradlePluginVersion = "0.42.0"
    const val dokka = "1.6.21"
}

object ProjectPlugins {
    const val gradle = "com.android.tools.build:gradle:${ProjectVersions.gradle}"
    const val gradlePluginVersion = "com.github.ben-manes:gradle-versions-plugin:${ProjectVersions.gradlePluginVersion}"
    const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${ProjectVersions.dokka}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${ProjectVersions.kotlin}"
    const val googleServices = "com.google.gms:google-services:${ProjectVersions.googleServices}"
}

object ProjectLibs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${ProjectVersions.kotlin}"
}

object AndroidSDK {
    const val minSDK = 21
    const val targetSDK = 32
    const val compileSDK = 32
}

object SpectableSettings {
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val debuggable = false
}