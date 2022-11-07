package com.example.cleanarchitecture.app.base

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.app.managers.StringResourceManager
import com.example.cleanarchitecture.app.network.NetworkResponseResult
import com.google.gson.Gson
import javax.inject.Inject

data class ViewError(
    val message: String,
    val code: Int = -1
)
interface BaseMapper<From, To> {

    fun map(from: From): To
}

interface ErrorStateMapper:BaseMapper<NetworkResponseResult<*>,Resource.Error>
class DefaultErrorStateMapper @Inject
constructor(private val stringResourceManager: StringResourceManager
,private val gson: Gson)
    : ErrorStateMapper {


    override fun map(from: NetworkResponseResult<*>): Resource.Error {
        when(from)
        {
            is NetworkResponseResult.EmptyResponse->{
           return Resource.Error(
                ViewError(message =stringResourceManager.getString(R.string.NO_DATA_FOUND)))
            }
            is NetworkResponseResult.NetworkError->{
                return Resource.Error (ViewError(message
            =stringResourceManager.getString(R.string.INTERNET_ERROR)))
            }
            is NetworkResponseResult.Failure -> {
                return Resource.Error(ViewError(gson.fromJson(from.errorResponse.message, Error::class.java).message ?:
                stringResourceManager.getString(R.string.EMPTY_STRING),
                    from.errorResponse.code ?: -1
                ))
            }
        }
        return Resource.Error(ViewError(message = stringResourceManager.getString(R.string
            .INVALID_STATE)))
    }
}