package com.example.mvpcats.di.module

import android.app.Application
import com.example.mvpcats.CatsApplication
import com.example.mvpcats.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val catsApplication: CatsApplication) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application{
        return catsApplication
    }
}