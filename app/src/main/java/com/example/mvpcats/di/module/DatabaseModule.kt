package com.example.mvpcats.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvpcats.CatsApplication
import com.example.mvpcats.di.scope.ActivityContext
import com.example.mvpcats.di.scope.DatabaseInfo
import com.example.mvpcats.di.scope.ApplicationContext
import com.example.mvpcats.model.database.CatsDao
import com.example.mvpcats.model.database.CatsDatabase
import com.example.mvpcats.model.entity.CatsModel
import com.example.mvpcats.model.repository.CatsRepository
import com.example.mvpcats.presenter.CatsPresenter
import com.example.mvpcats.ui.ScrollingActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton


@Module
class DatabaseModule(@ApplicationContext private val context: Context) {

    private val dbName = "cats"

    @Provides
    fun provideDatabaseName(): String {
        return dbName
    }

    @Singleton
    @Provides
    fun providePersonDao(db: CatsDatabase): CatsDao {
        return db.catsDao()
    }

    @Provides
    @Singleton
    fun provideDataBase() : CatsDatabase {
        return Room.databaseBuilder(
            context,
            CatsDatabase::class.java,
            dbName
        ).fallbackToDestructiveMigration().build()
    }
}