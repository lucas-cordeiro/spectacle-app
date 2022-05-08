import org.gradle.api.Project

object CoreVersions {

    //Kotlin
    const val coroutines = "1.5.0"

    //Android
    const val androidX = "1.8.0-alpha03"
    const val appCompat = "1.4.1"
    const val material = "1.6.0-alpha02"
    const val fragmentX = "1.4.1"
    const val lifecycle = "2.4.0"

    //DependencyInjection
    const val koinCore = "3.1.5"

    //Others
    const val logcat = "0.1"
    const val startup = "1.1.0"
    const val airbnbDeepLink = "5.4.3"
    const val firebaseBom = "30.0.0"
    const val firebaseKTX = "20.1.0"
    const val servicesKTX = "1.4.3"
}

object CoreLibs {

    //Kotlin
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${CoreVersions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${CoreVersions.coroutines}"
    const val coroutinesGoogleServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${CoreVersions.coroutines}"

    //Android
    const val androidX = "androidx.core:core-ktx:${CoreVersions.androidX}"
    const val appCompat = "androidx.appcompat:appcompat:${CoreVersions.appCompat}"
    const val material = "com.google.android.material:material:${CoreVersions.material}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${CoreVersions.fragmentX}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${CoreVersions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${CoreVersions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${CoreVersions.lifecycle}"

    //DependencyInjection
    const val koinCore = "io.insert-koin:koin-core:${CoreVersions.koinCore}"
    const val koinAndroid = "io.insert-koin:koin-android:${CoreVersions.koinCore}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${CoreVersions.koinCore}"

    //Others
    const val logcat = "com.squareup.logcat:logcat:${CoreVersions.logcat}"
    const val startup = "androidx.startup:startup-runtime:${CoreVersions.startup}"
    const val airbnbDeeplink = "com.airbnb:deeplinkdispatch:${CoreVersions.airbnbDeepLink}"
    const val airbnbAnnotationProcessor = "com.airbnb:deeplinkdispatch-processor:${CoreVersions.airbnbDeepLink}"
    const val firebaseBom = "com.google.firebase:firebase-bom:${CoreVersions.firebaseBom}"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
    const val firebaseKTX = "com.google.firebase:firebase-common-ktx:${CoreVersions.firebaseKTX}"
    const val servicesKTX = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${CoreVersions.servicesKTX}"
}