package com.example.cleanarchitecture.app.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class BaseViewModel :ViewModel()
{

    protected fun getUseCaseResponse(
        flow : Flow<Resource<Any>>,loaderState:MutableLiveData<Boolean>,
        successCallback:(Any?)->Unit, failureCallback:(ViewError)->Unit)
    {
        viewModelScope.launch {
            flow.collectLatest {
                when(it)
                {
                    is Resource.Success->{
                        successCallback(it.data)
                        loaderState.value=false
                    }
                    is Resource.Error -> {
                        failureCallback(it.viewError)
                        loaderState.value=false
                    }
                    Resource.Loading ->{
                        loaderState.value=true

                    }
                }
            }
        }
    }
}