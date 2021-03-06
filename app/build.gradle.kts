plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    compileSdk = AndroidSDK.compileSDK

    defaultConfig {
        minSdk = AndroidSDK.minSDK
        targetSdk = AndroidSDK.targetSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        applicationId = "br.com.spectacle.app"
        versionCode = SpectableSettings.versionCode
        versionName = SpectableSettings.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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
    implementation(platform(CoreLibs.firebaseBom))
    implementation(CoreLibs.firebaseAnalytics)
    implementation(CoreLibs.firebaseAuth)
    implementation(CoreLibs.firebaseKTX)
    implementation(CoreLibs.servicesKTX)

    implementation(CoreLibs.koinAndroid)
    implementation(CoreLibs.koinCompose)

    implementation(project(Modules.ds))
    implementation(project(Modules.domain))
    implementation(project(Modules.data))

    implementation(project(Features.login))
    implementation(project(Features.movies))
    implementation(project(Features.musics))

    implementation(PresentationLibs.splash)
    implementation(CoreLibs.logcat)
    implementation(CoreLibs.startup)
}