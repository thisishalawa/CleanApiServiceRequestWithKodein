package master.clean.architecture_kodein.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import master.clean.architecture_kodein.data.login.LoginRequest
import master.clean.architecture_kodein.data.login.LoginResponse
import master.clean.architecture_kodein.internal.NoConnectivityException
import master.clean.architecture_kodein.network.response.ApiSystemService
import master.clean.architecture_kodein.utils.Constant.Companion.connection_debug


class SystemDataSourceImpl(
    private val apiSystemService: ApiSystemService
) : SystemDataSource {

    private val _loginResponse = MutableLiveData<LoginResponse>()

    override val loginResponse: LiveData<LoginResponse>
        get() = _loginResponse

    override suspend fun fetchLoginResponse(loginRequest: LoginRequest) {
        try {

            val fetchedResponse = apiSystemService
                .loginAsync(loginRequest)
                ?.await()
            _loginResponse.postValue(fetchedResponse)



        } catch (e: NoConnectivityException) {
            Log.e(connection_debug, "no internet connection_", e)
        }
    }
}