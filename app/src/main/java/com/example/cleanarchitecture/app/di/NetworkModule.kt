package com.example.cleanarchitecture.app.di

import com.example.cleanarchitecture.app.managers.StringResourceManager
import com.example.cleanarchitecture.app.network.DefaultErrorFactory
import com.example.cleanarchitecture.app.network.IErrorFactory
import com.example.cleanarchitecture.app.network.ShoppingApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule
{
    @Provides
    @Singleton
    fun providesGson()= Gson()

    @Singleton
    @Provides
    fun providesOkHttpInterceptor()=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }
    @Provides
    @Singleton
    fun providesOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient
    {
        return OkHttpClient.Builder()
            .writeTimeout(60,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit
    {
        return Retrofit.Builder().baseUrl("http://riderserviceapi-dev.eu-west-1.elasticbeanstalk.com:80/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun providesShoppingApiService(retrofit: Retrofit) : ShoppingApiService
    {
        return retrofit.create(ShoppingApiService::class.java)
    }
    @Provides
    @Singleton
    fun providesErrorFactory(stringResourceManager: StringResourceManager):IErrorFactory
    {
        return DefaultErrorFactory(stringResourceManager)
    }
}