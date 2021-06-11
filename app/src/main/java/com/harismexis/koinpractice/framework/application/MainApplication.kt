package com.harismexis.koinpractice.framework.application

import android.app.Application
import com.harismexis.koinpractice.framework.di.appModule
import com.harismexis.koinpractice.framework.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(appModule, viewModelModule))
        }
    }

}