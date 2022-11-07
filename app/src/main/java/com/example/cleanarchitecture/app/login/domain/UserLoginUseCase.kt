package com.example.cleanarchitecture.app.login.domain

import com.example.cleanarchitecture.app.base.BaseUseCase
import com.example.cleanarchitecture.app.base.ErrorStateMapper
import com.example.cleanarchitecture.app.base.Resource
import com.example.cleanarchitecture.app.login.domain.data.Authorization
import com.example.cleanarchitecture.app.login.domain.data.LoginRequest
import com.example.cleanarchitecture.app.login.source.repositories.LoginRepository
import com.example.cleanarchitecture.app.network.NetworkResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface UserLoginUseCase: BaseUseCase<LoginRequest, Resource<Authorization>>
class DefaultUserLoginUseCase
@Inject constructor(private val repository: LoginRepository, private val errorStateMapper: ErrorStateMapper)
    : UserLoginUseCase
{
    override  fun invoke(params: LoginRequest?): Flow<Resource<Authorization>> = flow {
        emit(Resource.Loading)
        when (val resp = repository.login(params!!)) {
            is NetworkResponseResult.Success -> emit(Resource.Success())
            else -> {
                emit(errorStateMapper.map(resp))
            }
        }
    }.flowOn(getDispatcher())
}
