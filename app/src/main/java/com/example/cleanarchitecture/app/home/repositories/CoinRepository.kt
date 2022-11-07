package com.example.cleanarchitecture.app.home.repositories

import com.example.cleanarchitecture.app.home.coins.domain.data.CoinsApiResponse
import com.example.cleanarchitecture.app.home.coins.source.remote.CoinRemoteDataSource
import com.example.cleanarchitecture.app.login.domain.data.Authorization
import com.example.cleanarchitecture.app.login.domain.data.LoginRequest
import com.example.cleanarchitecture.app.network.NetworkResponseResult
import javax.inject.Inject

interface CoinRepository
{
    suspend fun getCoins() : NetworkResponseResult<CoinsApiResponse>

}
class DefaultCoinRepository @Inject constructor(private val
coinRemoteDataSource: CoinRemoteDataSource) : CoinRepository
{
    override suspend fun getCoins(): NetworkResponseResult<CoinsApiResponse> {
        return coinRemoteDataSource.coins()
    }

}