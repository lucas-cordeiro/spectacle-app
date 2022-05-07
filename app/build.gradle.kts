plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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

    implementation(project(Modules.ds))
}