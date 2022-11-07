package com.example.cleanarchitecture.app.di

import com.example.cleanarchitecture.app.home.coins.source.remote.CoinRemoteDataSource
import com.example.cleanarchitecture.app.home.coins.source.remote.DefaultCoinRemoteDataSource
import com.example.cleanarchitecture.app.login.source.remote.DefaultLoginRemoteDataSource
import com.example.cleanarchitecture.app.login.source.remote.LoginRemoteDataSource
import com.example.cleanarchitecture.app.network.ShoppingApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Provides
    @Singleton
    fun providesLoginRemoteDataSource(shoppingApiService: ShoppingApiService)
    :LoginRemoteDataSource
    =DefaultLoginRemoteDataSource(shoppingApiService)

    @Provides
    @Singleton
    fun providesCoinRemoteDataSource(shoppingApiService: ShoppingApiService)
            :CoinRemoteDataSource
            =DefaultCoinRemoteDataSource(shoppingApiService)
}