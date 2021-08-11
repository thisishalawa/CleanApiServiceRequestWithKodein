package master.clean.architecture_kodein.network.response

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import master.clean.architecture_kodein.data.login.LoginRequest
import master.clean.architecture_kodein.data.login.LoginResponse
import master.clean.architecture_kodein.network.connectivity.ConnectivityInterceptor
import master.clean.architecture_kodein.utils.Constant.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiSystemService {

    @POST("User/Login")
    fun loginAsync(
        @Body loginRequest: LoginRequest
    ): Deferred<LoginResponse?>?


    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApiSystemService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()//.addQueryParameter("apiKey", "111")
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor) //intercept every call
                .addInterceptor(connectivityInterceptor) //custom interceptor check Internet
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())// Deferred!
                .addConverterFactory(GsonConverterFactory.create()) // Gson!
                .build()
                .create(ApiSystemService::class.java)
        }
    }

}