package com.example.cleanarchitecture.app.di

import com.example.cleanarchitecture.app.home.coins.source.remote.DefaultCoinRemoteDataSource
import com.example.cleanarchitecture.app.home.repositories.CoinRepository
import com.example.cleanarchitecture.app.home.repositories.DefaultCoinRepository
import com.example.cleanarchitecture.app.login.source.remote.LoginRemoteDataSource
import com.example.cleanarchitecture.app.login.source.repositories.DefaultLoginRepository
import com.example.cleanarchitecture.app.login.source.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesLoginRepository(loginRemoteDataSource: LoginRemoteDataSource)
    :LoginRepository
    {
        return DefaultLoginRepository(loginRemoteDataSource)
    }
    @Provides
    @Singleton
    fun providesCoinRepository(coinRemoteDataSource: DefaultCoinRemoteDataSource)
            :CoinRepository
    { return DefaultCoinRepository(coinRemoteDataSource) }
}