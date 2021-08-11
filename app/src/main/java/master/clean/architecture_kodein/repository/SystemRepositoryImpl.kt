package master.clean.architecture_kodein.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import master.clean.architecture_kodein.data.login.LoginRequest
import master.clean.architecture_kodein.data.login.LoginResponse
import master.clean.architecture_kodein.network.datasource.SystemDataSource
import master.clean.architecture_kodein.utils.Constant.Companion.login_debug
import kotlin.math.log

class SystemRepositoryImpl(
    private val systemDataSource: SystemDataSource,
) : SystemRepository {


    init {
        systemDataSource.apply {
            loginResponse.observeForever { newLoginResponse ->
                persistLoginFetchedResponse(newLoginResponse)
            }
        }


    }

    private fun persistLoginFetchedResponse(response: LoginResponse?) {
        /*
        * 3# get observed data changed from dataSource
        * */
        if (response?.token != null)
            Log.d(
                login_debug, "SuccessfullyLogin: \n" +
                        "${response.fullName}"
            )
        else
            Log.d(login_debug, "login token failed __ ")

    }


    override suspend fun login(loginRequest: LoginRequest)
            : LiveData<out LoginResponse> {

        /*
        *
        *
        *  LOGIN
        * */
        Log.d(
            login_debug, "loginWithEmail&Password\n" +
                    "${loginRequest.Email}\n" +
                    "${loginRequest.Password}\n" +
                    ": ############ "
        )
        systemDataSource.fetchLoginResponse(
            LoginRequest(
                loginRequest.Email,
                loginRequest.Password
            )
        )
        /*
        *
        *  return Response !
        *
        * */
        return withContext(Dispatchers.IO) {
            return@withContext systemDataSource.loginResponse
        }
    }
}