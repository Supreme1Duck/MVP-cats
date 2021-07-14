package com.example.mvpcats.di.module

import com.example.mvpcats.CatsApplication
import com.example.mvpcats.di.scope.ApplicationContext
import com.example.mvpcats.di.scope.DatabaseInfo
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.model.repository.CatsRepository
import com.example.mvpcats.presenter.CatsPresenter
import com.example.mvpcats.presenter.FavouriteCatsPresenter
import com.example.mvpcats.ui.MainContract
import com.example.mvpcats.ui.ScrollingActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val catsApplication: CatsApplication) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplication() = catsApplication

    @Singleton
    @Provides
    fun provideContext() = catsApplication

    @Provides
    fun providePresenter(): CatsPresenter {
        return CatsPresenter()
    }

    @Provides
    @Singleton
    fun provideFavouritesPresenter(): FavouriteCatsPresenter {
        return FavouriteCatsPresenter()
    }

    @Provides
    fun provideRepository(): CatsRepository {
        return CatsRepository()
    }
}