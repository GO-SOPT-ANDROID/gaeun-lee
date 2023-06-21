package org.android.go.sopt.remote.service

import org.android.go.sopt.remote.remoteData.model.MyProfileDto
import org.android.go.sopt.remote.remoteData.model.ResponseListUsersDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MainPageService {

    @GET("users")
    fun getListUsers(
    ): Call<ResponseListUsersDto>
}