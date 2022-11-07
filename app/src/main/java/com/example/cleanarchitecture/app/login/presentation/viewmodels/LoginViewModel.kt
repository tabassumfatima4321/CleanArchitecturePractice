package com.example.cleanarchitecture.app.login.presentation.viewmodels

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.app.base.BaseViewModel
import com.example.cleanarchitecture.app.login.domain.UserLoginUseCase
import com.example.cleanarchitecture.app.login.domain.data.LoginRequest
import com.example.cleanarchitecture.app.login.source.repositories.LoginRepository
import com.example.cleanarchitecture.app.managers.StringResourceManager
import com.example.cleanarchitecture.app.managers.ToastManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface  LoginViewModel
{
    fun login(userName:String,passwordString: String)
}
@HiltViewModel
class DefaultLoginViewModel @Inject constructor(
   private val stringResourceManager: StringResourceManager,
   private val toastManager: ToastManager
, private val loginUseCase: UserLoginUseCase
): BaseViewModel()/*,LoginViewModel*/
{
    private val _loader by lazy { MutableLiveData<Boolean>() }
    val loader: LiveData<Boolean> get() = _loader
    private val _userName by lazy { MutableLiveData<String>() }
    val userName: LiveData<String> get() = _userName
    private val _password by lazy { MutableLiveData<String>() }
    val isNavigateTo: LiveData<Boolean> get() = _isNavigateTo
    private val _isNavigateTo by lazy { MutableLiveData<Boolean>() }
    val password: LiveData<String> get() = _password
    private val _userNameError by lazy { MutableLiveData<String>() }
     val userNameError : LiveData<String> get()=_userNameError

    private val _passwordError by lazy { MutableLiveData<String>() }
     val passwordError:LiveData<String> get()=_passwordError

    val userNameTextWatcher by lazy {
        object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =Unit
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                when {
                    text.toString().trim().isEmpty()->{
                        _userNameError.postValue(stringResourceManager.getString(R.string.
                        user_name_error))
                    }

                    else -> {
                        _userNameError.postValue(stringResourceManager.getString(R.string.
                        EMPTY_STRING))
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?){
                _userName.postValue(p0.toString())

            }

        }
    }
    val passwordTextWatcher by lazy {
        object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =Unit
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                when {
                    text.toString().trim().isEmpty()->{
                        _passwordError.postValue(stringResourceManager.getString(R.string.
                        password_error))
                    }
                    text.toString().trim().length<6->
                    {
                        _passwordError.postValue(stringResourceManager.getString(R.string.
                        password_cant_be_less_six_digit))
                    }
                    else->
                    {
                        _passwordError.postValue(stringResourceManager.getString(R.string.
                        EMPTY_STRING))
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?){
                _password.postValue(p0.toString())

            }

        }
    }


    fun login(userName: String, passwordString: String) {


        if(userName.isNotEmpty() && passwordString.isNotEmpty())
        {

            //_loader.postValue(false)
            getUseCaseResponse(loginUseCase(LoginRequest(userName,passwordString)),
                _loader
                , successCallback = {
                    toastManager.showToast("HELLO CLICKED",Toast.LENGTH_SHORT)
                    _isNavigateTo.postValue(true)
                },
                failureCallback =
                {
                    toastManager.showToast(it.message,Toast.LENGTH_SHORT)

                })
        }
        else{
            toastManager.showToast(R.string.Invalid_username_pass,Toast.LENGTH_SHORT)
        }
    }

}