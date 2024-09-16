package com.example.sequeniatest.app

import android.app.Application
import com.example.sequeniatest.di.presentationModule
import com.example.sequeniatest.di.dataModule
import com.example.sequeniatest.di.domainModule
import com.example.sequeniatest.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(
                presentationModule,
                domainModule,
                dataModule
            ))
        }
    }
}