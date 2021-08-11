package master.clean.architecture_kodein.ui

import android.util.Log
import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import master.clean.architecture_kodein.data.login.LoginRequest
import master.clean.architecture_kodein.repository.SystemRepository
import master.clean.architecture_kodein.utils.Constant.Companion.login_debug
import master.clean.architecture_kodein.utils.Event

const val TAG = login_debug

class SystemViewModel(
    private val systemRepository: SystemRepository
) : ViewModel(), Observable {


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    /*
    * catch statusMessage
    * */
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage


    /*
    * Login
    * */
    @Bindable
    val inputEmail = MutableLiveData("")
    val inputPass = MutableLiveData("")

    private lateinit var _email: String
    private lateinit var _password: String

    fun login() {

        _email = inputEmail.value?.trim().toString()
        _password = inputPass.value?.trim().toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(_email).matches())
            statusMessage.value = Event("invalid email ..")
        else if (_password.length < 4)
            statusMessage.value = Event("invalid password  ..")
        else
            viewModelScope.launch(Dispatchers.Main) {
                systemRepository.login(LoginRequest(_email, _password))
            }

    }

}