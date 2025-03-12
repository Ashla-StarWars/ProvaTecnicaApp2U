package enric.domenech.app2u

import android.app.Application

import enric.domenech.app2u.di.koin.appModule
import enric.domenech.app2u.di.koin.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}