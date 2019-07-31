package com.mehmettas.cent

import android.app.Application
import android.content.Context
import com.mehmettas.cent.di.appModule
import org.koin.android.ext.android.startKoin

class CoreApp: Application() {

    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        configureDependencyInjection()
    }

    // Place where we start the koin
    private fun configureDependencyInjection() {
        startKoin(this, appModule)
    }


}