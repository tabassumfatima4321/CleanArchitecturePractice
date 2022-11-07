package com.example.cleanarchitecture.app.home.coins.presentation.adapters

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.app.base.ItemViewModel
import com.example.cleanarchitecture.app.home.coins.domain.data.CoinsApiResponseItem
import com.example.cleanarchitecture.app.home.coins.presentation.viewmodels.DefaultCoinViewModel

class CoinAdapter constructor( val coinsApiResponseItem:CoinsApiResponseItem)
    : ItemViewModel {
    override val layoutId: Int get() = R.layout.item_coins
    override val viewType: Int
        get() = DefaultCoinViewModel.AD_ITEM

    override fun areItemsTheSame(other: ItemViewModel): Boolean =this===other

    override fun areContentTheSame(other: ItemViewModel): Boolean {
        return if(other is CoinAdapter) {
            other.coinsApiResponseItem==coinsApiResponseItem
        } else false
    }
}