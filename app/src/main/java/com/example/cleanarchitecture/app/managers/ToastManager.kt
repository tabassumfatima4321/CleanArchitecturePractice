package com.example.cleanarchitecture.app.managers

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ToastManager {
    fun showToast(@StringRes resId:Int,length:Int)
    fun showToast(message:String,length: Int)
}
class DefaultToastManager
    @Inject constructor(@ApplicationContext private val context:Context
    ,private val stringResourceManager: StringResourceManager)
    : ToastManager
{
    override fun showToast(resId: Int, length: Int) {
       return Toast.makeText(context,stringResourceManager.getString(resId),
       length).show()
    }

    override fun showToast(message: String, length: Int) {
        return Toast.makeText(context,message,
            length).show()
    }

}