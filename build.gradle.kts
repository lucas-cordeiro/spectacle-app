import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(ProjectPlugins.kotlin)
        classpath(ProjectPlugins.gradle)
        classpath(ProjectPlugins.gradlePluginVersion)
        classpath(ProjectPlugins.googleServices)
        classpath(ProjectPlugins.dokka)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}