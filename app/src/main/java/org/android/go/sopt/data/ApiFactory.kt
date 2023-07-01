package org.android.go.sopt.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.android.go.sopt.BuildConfig.AUTH_BASE_URL
import org.android.go.sopt.BuildConfig.USERS_LIST_BASE_URL
import org.android.go.sopt.data.service.LogInPageService
import org.android.go.sopt.data.service.MainPageService
import retrofit2.Retrofit

object ApiFactory {
    val retrofitAuth: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val retrofitUsers: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(USERS_LIST_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> createAuth(): T = retrofitAuth.create<T>(T::class.java)
    inline fun <reified T> createUsers(): T = retrofitUsers.create<T>(T::class.java)
}

object ServicePool { // 서비스가 모여있는곳
    val loginPageService = ApiFactory.createAuth<LogInPageService>()
    val mainPageService = ApiFactory.createUsers<MainPageService>()
}
