package id.cavannus.thetaleofwayang

import android.app.Application
import id.cavannus.thetaleofwayang.core.di.databaseModule
import id.cavannus.thetaleofwayang.core.di.networkModule
import id.cavannus.thetaleofwayang.core.di.repositoryModule
import id.cavannus.thetaleofwayang.di.useCaseModule
import id.cavannus.thetaleofwayang.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}