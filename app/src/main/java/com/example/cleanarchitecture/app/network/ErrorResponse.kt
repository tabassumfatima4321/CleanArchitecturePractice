package com.example.cleanarchitecture.app.network

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.app.managers.StringResourceManager
import retrofit2.HttpException
import java.io.EOFException
import java.net.UnknownHostException

interface ErrorResponse{
    fun getErrorResponse(throwable:Any?=null):NetworkResponseError
}
interface IErrorFactory
{
    fun getError(type:Throwable) : ErrorResponse

}
class DefaultErrorFactory constructor(private val stringResourceManager: StringResourceManager)
    : IErrorFactory
{
    override fun getError(type: Throwable): ErrorResponse {
        return when(type) {
            is UnknownHostException-> InternetError(stringResourceManager)
            is EOFException -> NoResultFoundError(stringResourceManager)
            is HttpException -> HttpError(stringResourceManager)
            else -> UnExpectedError(stringResourceManager)
        }
    }

}
class InternetError  constructor(
    private val stringResourceManager: StringResourceManager
) : ErrorResponse
 {
     override fun getErrorResponse(throwable: Any?): NetworkResponseError {
        return NetworkResponseError(message = stringResourceManager.
        getString(R.string.INTERNET_ERROR))
     }
 }
class NoResultFoundError  constructor(
    private val  stringResourceManager: StringResourceManager) : ErrorResponse
{
    override fun getErrorResponse(throwable: Any?): NetworkResponseError {
        return NetworkResponseError(message = stringResourceManager.
        getString(R.string.NO_RESULT_FOUND))
    }

}
class UnExpectedError  constructor
    (
    private val  stringResourceManager: StringResourceManager
) : ErrorResponse
{
    override fun getErrorResponse(throwable: Any?): NetworkResponseError {
        return NetworkResponseError(message = stringResourceManager.
        getString(R.string.UNEXPECTED_ERROR))
    }

}
class HttpError  constructor(
    private val  stringResourceManager: StringResourceManager
) : ErrorResponse
{
    override fun getErrorResponse(throwable: Any?): NetworkResponseError {
        if(throwable is HttpException)
        {
            throwable.response()?.let { response ->
                return NetworkResponseError(
                    code=response.code(),
                    path = stringResourceManager.getString(R.string.SOMETHING_WENT_WRONG),
                    message = response.errorBody()?.string().toString()
                )
            }
        }
        return UnExpectedError(stringResourceManager).getErrorResponse()
    }

}