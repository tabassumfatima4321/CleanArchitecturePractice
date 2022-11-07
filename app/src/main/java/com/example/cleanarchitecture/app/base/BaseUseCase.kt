package com.example.cleanarchitecture.app.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/*
data class ViewError(
    val message: String,
    val code: Int = -1
)
*/

sealed class Resource<out T> {
    data class Success<T>(val data: T?=null) : Resource<T>()
    object Loading : Resource<Nothing>()
    data class Error(val viewError:ViewError) : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$viewError]"
            Loading -> "Loading"
        }
    }
}

interface BaseUseCase<in P,R> {

     operator fun  invoke(params:P?=null):Flow<R>
  /*  suspend operator fun  invoke(call:suspend ()->R,
                                 successCallback: (FlowCollector<Resource<R>>
                        )
        ): Flow<Resource<R>>*/
    fun getDispatcher():CoroutineDispatcher=Dispatchers.IO

}
/*
abstract class DefaultBaseUseCase<in P,R> : BaseUseCase<P,R>
{
    @set:Inject
    internal lateinit var dispatcher: DispatcherProvider
  */
/*  internal lateinit var flow: Flow<Resource<R>>

    override suspend fun invoke(
        call: suspend () -> R,
        successCallback: FlowCollector<Resource<R>>
    ): Flow<Resource<R>>  =flow{
        emit(Resource.Loading)
        when(call.invoke())
        {
            is NetworkResponseResult.Success<*> ->{
                successCallback
            }
            is NetworkResponseResult.EmptyResponse->{

            }

        }
    }.flowOn(getDispatcher())
*//*



    override fun getDispatcher(): CoroutineDispatcher {
        return dispatcher.io
    }
}*/
