package com.example.wb_7_3

import android.app.Application
import android.content.Context
import com.example.wb_7_3.di.AppComponent
import com.example.wb_7_3.di.DaggerAppComponent
import com.example.wb_7_3.di.DataModule
import com.facebook.drawee.backends.pipeline.Fresco

class CatApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().dataModule(DataModule(context = this)).build()
        Fresco.initialize(this)
    }
}

val Context.appComponent: AppComponent
    get() = when(this){
        is CatApp -> appComponent
        else -> applicationContext.appComponent
    }