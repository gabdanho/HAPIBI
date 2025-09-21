package com.gabdanho.hapibi.di

import com.gabdanho.hapibi.presentation.navigation.Navigator
import com.gabdanho.hapibi.presentation.navigation.NavigatorImpl
import com.gabdanho.hapibi.presentation.navigation.model.AppGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return NavigatorImpl(startDestination = AppGraph.LoginScreen)
    }
}