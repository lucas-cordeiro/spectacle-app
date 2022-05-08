package br.com.spectacle.app

import android.app.Application
import logcat.AndroidLogcatLogger
import logcat.LogPriority

class SpectacleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.DEBUG)
    }
}