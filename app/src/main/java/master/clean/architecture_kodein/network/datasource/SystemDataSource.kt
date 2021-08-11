package master.clean.architecture_kodein.network.datasource

import androidx.lifecycle.LiveData
import master.clean.architecture_kodein.data.login.LoginRequest
import master.clean.architecture_kodein.data.login.LoginResponse

interface SystemDataSource {

    val loginResponse: LiveData<LoginResponse>
    suspend fun fetchLoginResponse(
        loginRequest: LoginRequest
    )

}