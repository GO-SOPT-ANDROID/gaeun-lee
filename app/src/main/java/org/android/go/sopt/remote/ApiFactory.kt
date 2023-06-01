package org.android.go.sopt.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.android.go.sopt.BuildConfig.AUTH_BASE_URL
import org.android.go.sopt.BuildConfig.USERS_LIST_BASE_URL
import org.android.go.sopt.remote.service.UsersListService
import org.android.go.sopt.remote.service.LogInService
import org.android.go.sopt.remote.service.MyProfileService
import org.android.go.sopt.remote.service.SignUpService
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
    val signUpService = ApiFactory.createAuth<SignUpService>()
    val logInService = ApiFactory.createAuth<LogInService>()
    val listUsersService = ApiFactory.createUsers<UsersListService>()
    val myProfileService = ApiFactory.createAuth<MyProfileService>()
}