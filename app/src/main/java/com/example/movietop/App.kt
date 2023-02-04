package com.example.movietop

import android.app.Application
import android.content.Context
import com.example.movietop.di.AppComponent
import com.example.movietop.di.DaggerAppComponent

class App : Application() {
    val applicationComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> applicationComponent
        else -> this.applicationContext.appComponent
    }