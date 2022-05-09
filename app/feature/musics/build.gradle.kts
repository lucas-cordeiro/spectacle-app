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
    implementation(project(Modules.ds))
    implementation(project(Modules.data))
    implementation(project(Modules.domain))

    implementation(CoreLibs.koinAndroid)
    implementation(CoreLibs.koinCompose)
}