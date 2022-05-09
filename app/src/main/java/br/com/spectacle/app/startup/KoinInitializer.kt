package br.com.spectacle.app.startup

import android.content.Context
import androidx.startup.Initializer
import br.com.spectacle.app.BuildConfig
import br.com.spectacle.app.core.data.di.networkModule
import br.com.spectacle.app.di.applicationModule
import br.com.spectacle.app.feature.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinInitializer: Initializer<KoinApplication> {

    override fun create(context: Context): KoinApplication {
        val modules = mutableListOf(
            networkModule,
            applicationModule,
            loginModule,
        )

        return startKoin {
            androidContext(context)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(modules)
            allowOverride(true)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}