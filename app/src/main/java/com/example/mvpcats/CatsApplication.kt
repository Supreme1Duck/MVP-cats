package com.example.mvpcats

import android.app.Application
import com.example.mvpcats.di.component.ApplicationComponent
import com.example.mvpcats.di.component.DaggerApplicationComponent
import com.example.mvpcats.di.module.ApplicationModule


class CatsApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: CatsApplication private set
    }
}