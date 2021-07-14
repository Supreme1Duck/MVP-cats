package com.example.mvpcats.di.component

import com.example.mvpcats.CatsApplication
import com.example.mvpcats.di.module.ApplicationModule
import com.example.mvpcats.di.module.DatabaseModule
import com.example.mvpcats.di.module.FavouriteCatsActivityModule
import com.example.mvpcats.di.module.ScrollingActivityModule
import com.example.mvpcats.di.scope.ActivityContext
import com.example.mvpcats.di.scope.ApplicationContext
import com.example.mvpcats.di.scope.DatabaseInfo
import com.example.mvpcats.model.database.CatsDatabase
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.model.repository.CatsRepository
import com.example.mvpcats.presenter.CatsPresenter
import com.example.mvpcats.presenter.FavouriteCatsPresenter
import com.example.mvpcats.ui.FavouriteCatsActivity
import com.example.mvpcats.ui.ScrollingActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DatabaseModule::class, ScrollingActivityModule::class, FavouriteCatsActivityModule::class])
interface ApplicationComponent {

    fun inject(application: CatsApplication)

    fun inject(scrollingActivity: ScrollingActivity)

    fun inject(favouriteCatsActivity: FavouriteCatsActivity)

    fun inject(catsDatabase: CatsDatabase)

    fun inject(catsPresenter: CatsPresenter)

    fun inject(catsPresenter: FavouriteCatsPresenter)

    fun inject(catsRepository: CatsRepository)
}