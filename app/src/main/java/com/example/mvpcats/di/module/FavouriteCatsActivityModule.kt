package com.example.mvpcats.di.module

import com.example.mvpcats.ui.FavouriteCatsActivity
import dagger.Module
import dagger.Provides

@Module
class FavouriteCatsActivityModule {

    @Provides
    fun provideActivity(): FavouriteCatsActivity {
        return FavouriteCatsActivity()
    }
}