package com.example.cleanarchitecture.app.network

import com.example.cleanarchitecture.app.login.domain.data.Authorization
import com.example.cleanarchitecture.app.home.coins.domain.data.CoinsApiResponse
import com.example.cleanarchitecture.app.login.domain.data.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ShoppingApiService
{
    @POST("https://login.free.beeceptor.com/")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<Authorization>
    @GET("https://coinimage.free.beeceptor.com/")
    suspend fun getCoins():Response<CoinsApiResponse>

}