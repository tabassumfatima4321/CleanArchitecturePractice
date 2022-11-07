package com.example.cleanarchitecture.app.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<T> constructor(private val
viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)
{
    abstract fun bind(position:Int,data:T)
}
abstract class BaseAdapter<T>() : RecyclerView.Adapter<BaseViewHolder<T>>()
{
    var listOfItems:MutableList<T>?= mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field=value
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(
        holder: BaseViewHolder<T>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        listOfItems?.get(position)?.let { holder.bind(position, it) }
    }

    override fun getItemCount(): Int = listOfItems?.size ?:run{0}
}
/*
class MedicalStorePickedOrdersAdapter
    (private val context: Context,
     private val pickedOrdersApiResponseItem: List<PickedOrdersApiResponseItem?>
     , private val orderType:String,
     private val onItemClick: ((OrderDetails) -> Unit))   :
    RecyclerView.Adapter<MedicalStorePickedOrdersAdapter.ItemViewModel>() {
    inner class ItemViewModel(private val binding: ItemMedicalStorePickedOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val pickedOrdersApiResponse=pickedOrdersApiResponseItem[position]
            pickedOrdersApiResponse?.apply {
                binding.tvMedicalStore.text=shopName
                pickedOrders?.let {
                    binding.rvItem.adapter=OrderAdapter(context,View.VISIBLE,it, orderType)
                    {

                        onItemClick(it)

                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewModel {
        return ItemViewModel(
            ItemMedicalStorePickedOrdersBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(
        holder: ItemViewModel,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int =pickedOrdersApiResponseItem.size

}*/
