package com.example.mvpcats.di.component

import com.example.mvpcats.CatsApplication
import com.example.mvpcats.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: CatsApplication)
}