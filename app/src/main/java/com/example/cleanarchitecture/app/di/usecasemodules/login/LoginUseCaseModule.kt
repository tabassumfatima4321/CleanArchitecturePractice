package com.example.cleanarchitecture.app.di.usecasemodules.login

import com.example.cleanarchitecture.app.base.ErrorStateMapper
import com.example.cleanarchitecture.app.home.coins.domain.usecase.DefaultGetCoinsUseCase
import com.example.cleanarchitecture.app.home.coins.domain.usecase.GetCoinsUseCase
import com.example.cleanarchitecture.app.home.repositories.CoinRepository
import com.example.cleanarchitecture.app.login.domain.DefaultUserLoginUseCase
import com.example.cleanarchitecture.app.login.domain.UserLoginUseCase
import com.example.cleanarchitecture.app.login.source.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginUseCaseModule
{
    @Provides
    @Singleton
   fun providesUserLoginUserCase(loginRepository: LoginRepository
                                 ,errorStateMapper: ErrorStateMapper):UserLoginUseCase
   {
      return DefaultUserLoginUseCase(loginRepository,errorStateMapper)
   }
    @Provides
    @Singleton
    fun providesGetCoinsUseCase(coinRepository: CoinRepository
                                  ,errorStateMapper: ErrorStateMapper)
    : GetCoinsUseCase
    {
        return DefaultGetCoinsUseCase(coinRepository,errorStateMapper)
    }
}