package com.example.cleanarchitecture.app.home.coins.presentation.adapters

import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.app.base.ItemViewModel
import com.example.cleanarchitecture.app.home.coins.domain.data.CoinsApiResponseItem
import com.example.cleanarchitecture.app.home.coins.presentation.viewmodels.CoinViewModel
import com.example.cleanarchitecture.app.home.coins.presentation.viewmodels.DefaultCoinViewModel

class CoinTwoAdapter constructor(val coinsApiResponseItem: CoinsApiResponseItem)
    : ItemViewModel
{
    override val layoutId: Int
        get() = R.layout.item_coin_two
    override val viewType: Int
        get() = DefaultCoinViewModel.LISTING_ITEM

    override fun areItemsTheSame(other: ItemViewModel): Boolean = this===other

    override fun areContentTheSame(other: ItemViewModel): Boolean {
        return if(other is CoinTwoAdapter)
        {
            other.coinsApiResponseItem==coinsApiResponseItem
        }else false

    }
}