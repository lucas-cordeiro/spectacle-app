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

    //Firebase
    implementation(platform(CoreLibs.firebaseBom))
    implementation(CoreLibs.firebaseAuth)
    implementation(CoreLibs.firebaseKTX)
    implementation(CoreLibs.servicesKTX)

    //Coroutine
    implementation(CoreLibs.coroutines)

    //Data Libs
    api(DataLibs.ktor)
    implementation(DataLibs.ktorGson)
    implementation(DataLibs.ktorLog)
    implementation(DataLibs.ktorAuth)

    implementation(CoreLibs.koinAndroid)
    implementation(CoreLibs.logcat)
}