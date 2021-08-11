package master.clean.architecture_kodein.network.connectivity

import okhttp3.Interceptor


interface ConnectivityInterceptor : Interceptor {
    fun isOnline(): Boolean

}