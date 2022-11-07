package com.example.cleanarchitecture.app.utils

import android.text.TextWatcher
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.app.base.ItemViewModel
import com.example.cleanarchitecture.app.base.RecyclerviewAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error_message")
fun TextInputLayout.setErrorMessage(message:String?)
{ this.error=message?:""
}
@BindingAdapter("set_text_watcher")
fun TextInputEditText.setTextWatcher(watcher: TextWatcher)
{
    this.addTextChangedListener(watcher)
}
@BindingAdapter("set_visibility")
fun View.setVisibility(isVisible:Boolean)
{
    if(isVisible) show() else hide()
}
/*Recyclerview work*/
fun createOrUpdateRecyclerView(recyclerView: RecyclerView): RecyclerviewAdapter
{
   return if(recyclerView.adapter!=null && recyclerView.adapter is RecyclerviewAdapter)
    {
        recyclerView.adapter as RecyclerviewAdapter
    }
    else
    {
       val recyclerViewAdapter=RecyclerviewAdapter()
        recyclerView.adapter=recyclerViewAdapter
        recyclerViewAdapter
    }
}
@BindingAdapter("set_recycler_view_items")
fun bindRecyclerViewItems(recyclerView: RecyclerView,itemViewModel: List<ItemViewModel>?)
{
    val recyclerviewAdapter= createOrUpdateRecyclerView(recyclerView)
    recyclerviewAdapter.updateItems(itemViewModel)
}