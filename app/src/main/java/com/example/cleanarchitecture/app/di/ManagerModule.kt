package com.example.cleanarchitecture.app.di

import android.content.Context
import com.example.cleanarchitecture.app.base.DefaultErrorStateMapper
import com.example.cleanarchitecture.app.base.ErrorStateMapper
import com.example.cleanarchitecture.app.managers.DefaultStringResourceManager
import com.example.cleanarchitecture.app.managers.DefaultToastManager
import com.example.cleanarchitecture.app.managers.StringResourceManager
import com.example.cleanarchitecture.app.managers.ToastManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ManagerModule {

    @Provides
    @Singleton
    fun providesStringResManager(@ApplicationContext context: Context)
    :StringResourceManager
    {
        return DefaultStringResourceManager(context)
    }
    @Provides
    @Singleton
    fun providesToastManager(@ApplicationContext context: Context
    ,stringResourceManager: StringResourceManager): ToastManager
    {
        return DefaultToastManager(context,stringResourceManager)
    }
    @Provides
    @Singleton
    fun providesErrorStateMapper(stringResourceManager: StringResourceManager,
    gson: Gson):ErrorStateMapper
    {
        return DefaultErrorStateMapper(stringResourceManager,gson)
    }
}