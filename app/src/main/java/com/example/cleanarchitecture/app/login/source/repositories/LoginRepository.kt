package com.example.cleanarchitecture.app.login.source.repositories

import com.example.cleanarchitecture.app.login.domain.data.Authorization
import com.example.cleanarchitecture.app.login.domain.data.LoginRequest
import com.example.cleanarchitecture.app.login.source.remote.LoginRemoteDataSource
import com.example.cleanarchitecture.app.network.NetworkResponseResult
import javax.inject.Inject

class DefaultLoginRepository @Inject constructor(private val loginRemoteDataSource: LoginRemoteDataSource)
    : LoginRepository
{
    override suspend fun login(loginRequest: LoginRequest): NetworkResponseResult<Authorization> {
        return loginRemoteDataSource.login(loginRequest)
    }

}
interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest) : NetworkResponseResult<Authorization>
}