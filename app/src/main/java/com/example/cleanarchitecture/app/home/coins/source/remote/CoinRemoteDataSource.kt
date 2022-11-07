package com.example.cleanarchitecture.app.home.coins.source.remote

import com.example.cleanarchitecture.app.home.coins.domain.data.CoinsApiResponse
import com.example.cleanarchitecture.app.login.domain.data.Authorization
import com.example.cleanarchitecture.app.login.domain.data.LoginRequest
import com.example.cleanarchitecture.app.network.DefaultBaseRemoteDataSource
import com.example.cleanarchitecture.app.network.NetworkResponseResult
import com.example.cleanarchitecture.app.network.ShoppingApiService
import javax.inject.Inject

interface CoinRemoteDataSource
{
    suspend fun coins() : NetworkResponseResult<CoinsApiResponse>

}
class DefaultCoinRemoteDataSource @Inject constructor(private val apiService: ShoppingApiService)
    : DefaultBaseRemoteDataSource(),CoinRemoteDataSource
{
    override suspend fun coins(): NetworkResponseResult<CoinsApiResponse> {
       return getResults{ apiService.getCoins() }
    }

}