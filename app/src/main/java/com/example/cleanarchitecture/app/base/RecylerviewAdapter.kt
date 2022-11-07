package com.example.cleanarchitecture.app.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.BR

class RecyclerviewAdapter : RecyclerView.Adapter<BindableViewHolder>(){

    var itemViewModels:List<ItemViewModel> = emptyList()
    private val viewTypeToLayoutId:MutableMap<Int,Int> = mutableMapOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewTypeToLayoutId[viewType] ?: 0,
            parent,
            false)
        return BindableViewHolder(binding)
  /*    val binding:ViewDataBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
     viewTypeId[viewType]?:0,parent,false)
        return BindableViewHolder(binding)*/
    }

    override fun getItemViewType(position: Int): Int {
        val item=itemViewModels[position]
        if(!viewTypeToLayoutId.containsKey(item.viewType))
        {
            viewTypeToLayoutId[item.viewType]=item.layoutId
        }
        return  item.viewType
    }

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
       holder.bind(itemViewModels[position])
    }

    override fun getItemCount(): Int = itemViewModels.size
    fun updateItems(items:List<ItemViewModel>?)
    {
        val diffResults=DiffUtil.calculateDiff(DiffUtilCallback(itemViewModels,items?: emptyList()),false)
        itemViewModels=items.orEmpty().toMutableList()
        diffResults.dispatchUpdatesTo(this)
    }

}
class BindableViewHolder constructor(private
val viewDataBinding: ViewDataBinding):RecyclerView.ViewHolder(
    viewDataBinding.root
)
{
    fun bind(itemViewModel:ItemViewModel)
    {
        viewDataBinding.setVariable(BR.itemViewModel,itemViewModel)
    }

}
class DiffUtilCallback(val old : List<ItemViewModel>,val new : List<ItemViewModel>)
    : DiffUtil.Callback()
{
    override fun getOldListSize(): Int=old.size

    override fun getNewListSize(): Int =new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItems=old[oldItemPosition]
        val newItem=new[newItemPosition]
        return newItem.areItemsTheSame(oldItems)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem=old[oldItemPosition]
        val newItem=new[newItemPosition]
        return newItem.areContentTheSame(oldItem)

    }

}