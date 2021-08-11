package master.clean.architecture_kodein.repository

import androidx.lifecycle.LiveData
import master.clean.architecture_kodein.data.login.LoginRequest
import master.clean.architecture_kodein.data.login.LoginResponse

interface SystemRepository {

    suspend fun login(loginRequest: LoginRequest): LiveData<out LoginResponse>

}