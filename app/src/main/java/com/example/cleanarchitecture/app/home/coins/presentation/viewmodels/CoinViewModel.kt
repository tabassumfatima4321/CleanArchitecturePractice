package com.example.cleanarchitecture.app.home.coins.presentation.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.app.base.BaseViewModel
import com.example.cleanarchitecture.app.base.ItemViewModel
import com.example.cleanarchitecture.app.home.coins.domain.data.CoinsApiResponse
import com.example.cleanarchitecture.app.home.coins.domain.data.CoinsApiResponseItem
import com.example.cleanarchitecture.app.home.coins.domain.usecase.GetCoinsUseCase
import com.example.cleanarchitecture.app.home.coins.presentation.adapters.CoinAdapter
import com.example.cleanarchitecture.app.home.coins.presentation.adapters.CoinTwoAdapter
import com.example.cleanarchitecture.app.managers.StringResourceManager
import com.example.cleanarchitecture.app.managers.ToastManager
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.FieldPosition
import javax.inject.Inject

@HiltViewModel
class DefaultCoinViewModel @Inject
constructor(private val coinsUseCase: GetCoinsUseCase
,private val stringResourceManager: StringResourceManager,
private val toastManager: ToastManager)
    : BaseViewModel(),CoinViewModel
{
    private val _loader by lazy { MutableLiveData<Boolean>() }
    val loader: LiveData<Boolean> get() = _loader
    private val _itemViewModel by lazy { MutableLiveData<List<ItemViewModel>>(emptyList()) }
    val itemViewModel: LiveData<List<ItemViewModel>> get() = _itemViewModel
    init {
        getCoins()
    }
    override fun getCoins() {
        val viewData = mutableListOf<ItemViewModel>()
       getUseCaseResponse(coinsUseCase(),_loader,
       successCallback= { data->
           //toastManager.showToast("HELLO", Toast.LENGTH_SHORT)
          (data as CoinsApiResponse).apply {
              for(i in 0 until this.size)
              {
                  viewData.add(createCoinRecyclerViewData( this[i],i
                  ))
              }
               /* this.forEach { item ->

               }*/
               _itemViewModel.postValue(viewData)

           }


       }
       , failureCallback =
           {

           })
    }

    override fun createCoinRecyclerViewData( coinsApiResponseItem: CoinsApiResponseItem
    ,position: Int)
    : ItemViewModel{
        if(position==0)
        {return CoinAdapter(coinsApiResponseItem)}
        else return CoinTwoAdapter(coinsApiResponseItem)
    }
    companion object {
        const val HEADER_ITEM = 0
        const val LISTING_ITEM = 1
        const val AD_ITEM = 2
    }

}
interface CoinViewModel
{
    fun getCoins()
    fun createCoinRecyclerViewData(coinsApiResponseItem: CoinsApiResponseItem
    ,position: Int) : ItemViewModel

}
