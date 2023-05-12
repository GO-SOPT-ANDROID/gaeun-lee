package org.android.go.sopt.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.android.go.sopt.BuildConfig.AUTH_BASE_URL
import org.android.go.sopt.remote.service.ListUsersService
import org.android.go.sopt.remote.service.LogInService
import org.android.go.sopt.remote.service.SignUpService
import retrofit2.Retrofit

object ApiFactory {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool { // 서비스가 모여있는곳
    val signUpService = ApiFactory.create<SignUpService>()
    val logInService = ApiFactory.create<LogInService>()
}