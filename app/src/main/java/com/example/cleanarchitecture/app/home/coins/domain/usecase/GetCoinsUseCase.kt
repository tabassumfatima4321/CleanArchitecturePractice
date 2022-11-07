package com.example.cleanarchitecture.app.home.coins.domain.usecase

import com.example.cleanarchitecture.app.base.BaseUseCase
import com.example.cleanarchitecture.app.base.DefaultErrorStateMapper
import com.example.cleanarchitecture.app.base.ErrorStateMapper
import com.example.cleanarchitecture.app.base.Resource
import com.example.cleanarchitecture.app.home.coins.domain.data.CoinsApiResponse
import com.example.cleanarchitecture.app.home.repositories.CoinRepository
import com.example.cleanarchitecture.app.network.NetworkResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface GetCoinsUseCase : BaseUseCase<Unit,Resource<CoinsApiResponse>>
class DefaultGetCoinsUseCase @Inject constructor(
    private val  repository: CoinRepository,
    private val errorStateMapper: ErrorStateMapper
)
    : GetCoinsUseCase
{
    override fun invoke(params: Unit?): Flow<Resource<CoinsApiResponse>> =flow{
        emit(Resource.Loading)
        when(val resp=repository.getCoins())
        {
            is NetworkResponseResult.Success->emit(Resource.Success(resp.data))
            else ->
            {
                emit(errorStateMapper.map(resp))
            }

        }

    }
}