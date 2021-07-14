package com.example.mvpcats

import android.app.Application
import com.example.mvpcats.di.component.ApplicationComponent
import com.example.mvpcats.di.component.DaggerApplicationComponent
import com.example.mvpcats.di.module.ApplicationModule
import com.example.mvpcats.di.module.DatabaseModule
import com.example.mvpcats.di.module.FavouriteCatsActivityModule
import com.example.mvpcats.di.module.ScrollingActivityModule

class CatsApplication : Application() {

    companion object{
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .databaseModule(DatabaseModule(this))
            .scrollingActivityModule(ScrollingActivityModule())
            .favouriteCatsActivityModule(FavouriteCatsActivityModule())
            .build()
        applicationComponent.inject(this)
    }
}