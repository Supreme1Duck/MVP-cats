package com.example.mvpcats.di.component

import com.example.mvpcats.di.module.ActivityModule
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
}