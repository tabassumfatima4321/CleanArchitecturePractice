package com.example.cleanarchitecture.app.base

import androidx.annotation.LayoutRes

interface ItemViewModel
{
    val layoutId:Int
    @get:LayoutRes
    val viewType:Int
    get() = 0
    fun areItemsTheSame(other:ItemViewModel):Boolean
    fun areContentTheSame(other: ItemViewModel):Boolean
}