package com.example.cleanarchitecture.app.login.source.remote

import com.example.cleanarchitecture.app.login.domain.data.Authorization
import com.example.cleanarchitecture.app.login.domain.data.LoginRequest
import com.example.cleanarchitecture.app.network.DefaultBaseRemoteDataSource
import com.example.cleanarchitecture.app.network.NetworkResponseResult
import com.example.cleanarchitecture.app.network.ShoppingApiService
import javax.inject.Inject

class DefaultLoginRemoteDataSource @Inject constructor(
    private val shoppingApiService: ShoppingApiService
) : DefaultBaseRemoteDataSource(),LoginRemoteDataSource
{
    override suspend fun login(loginRequest: LoginRequest): NetworkResponseResult<Authorization> {
        return getResults { shoppingApiService.login(loginRequest) }
    }
}
interface LoginRemoteDataSource
{
suspend fun login(loginRequest: LoginRequest) : NetworkResponseResult<Authorization>
}