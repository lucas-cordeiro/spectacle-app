plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = AndroidSDK.compileSDK

    defaultConfig {
        minSdk = AndroidSDK.minSDK
        targetSdk = AndroidSDK.targetSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Modules.javaVersion
        targetCompatibility = Modules.javaVersion
    }
    kotlinOptions {
        jvmTarget = Modules.javaVersion.toString()
    }
    buildFeatures {
        compose = Modules.compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = PresentationVersions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Modules.domain))

    //Android Libs
    api(CoreLibs.androidX)
    api(CoreLibs.appCompat)
    api(CoreLibs.material)
    api(CoreLibs.fragmentKtx)
    api(CoreLibs.lifecycleRuntime)
    api(CoreLibs.lifecycleViewModel)
    api(CoreLibs.lifecycleExtensions)
    api(CoreLibs.lifecycleLiveData)

    //Kotlin
    implementation(CoreLibs.coroutines)

    //Compose
    api(PresentationLibs.compose)
    api(PresentationLibs.composeTooling)
    api(PresentationLibs.composeFoundation)
    api(PresentationLibs.composeConstraintLayout)
    api(PresentationLibs.composeMaterial)
    api(PresentationLibs.composeIcons)
    api(PresentationLibs.composeIconsExtended)
    api(PresentationLibs.composeActivity)
    api(PresentationLibs.composeViewModel)
    api(PresentationLibs.composeNavigation)

    //Compose Utils
    api(PresentationLibs.accompanistInsets)
    api(PresentationLibs.accompanistSwipeRefresh)
    api(PresentationLibs.accompanistNavigation)
    api(PresentationLibs.coil)

    // Lottie
    api(PresentationLibs.lottie)
    api(PresentationLibs.lottieCompose)

    //Others Libs
    implementation(CoreLibs.logcat)
}