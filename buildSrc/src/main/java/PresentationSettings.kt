object PresentationVersions {
    const val compose = "1.2.0-alpha02"
    const val composeConstraintLayout = "1.0.0"
    const val composeActivity = "1.4.0"
    const val composeViewModel = "2.4.0"
    const val composeNavigation = "2.5.0-alpha01"
    const val accompanist = "0.24.0-alpha"
    const val coil = "2.0.0-rc02"
    const val lottie = "4.0.0"
    const val splash = "1.0.0-beta02"
}

object PresentationLibs {
    const val compose = "androidx.compose.ui:ui:${PresentationVersions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${PresentationVersions.compose}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${PresentationVersions.compose}"
    const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${PresentationVersions.composeConstraintLayout}"
    const val composeMaterial = "androidx.compose.material:material:${PresentationVersions.compose}"
    const val composeIcons = "androidx.compose.material:material-icons-core:${PresentationVersions.compose}"
    const val composeIconsExtended = "androidx.compose.material:material-icons-extended:${PresentationVersions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${PresentationVersions.composeActivity}"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${PresentationVersions.composeViewModel}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${PresentationVersions.composeNavigation}"

    const val splash = "androidx.core:core-splashscreen:${PresentationVersions.splash}"

    const val accompanistInsets = "com.google.accompanist:accompanist-insets:${PresentationVersions.accompanist}"
    const val accompanistSwipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${PresentationVersions.accompanist}"
    const val accompanistNavigation = "com.google.accompanist:accompanist-navigation-animation:${PresentationVersions.accompanist}"

    const val coil = "io.coil-kt:coil-compose:${PresentationVersions.coil}"

    const val lottie = "com.airbnb.android:lottie:${PresentationVersions.lottie}"
    const val lottieCompose = "com.airbnb.android:lottie-compose:${PresentationVersions.lottie}"
}