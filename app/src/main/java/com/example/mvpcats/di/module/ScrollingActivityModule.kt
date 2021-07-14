package com.example.mvpcats.di.module

import com.example.mvpcats.ui.ScrollingActivity
import dagger.Module
import dagger.Provides

@Module
class ScrollingActivityModule {

    @Provides
    fun provideActivity(): ScrollingActivity {
        return ScrollingActivity()
    }
}