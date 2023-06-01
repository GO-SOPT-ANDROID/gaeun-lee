package org.android.go.sopt.remote.service

import org.android.go.sopt.remote.remoteData.model.ResponseListUsersDto
import retrofit2.Response
import retrofit2.http.GET

interface MainPageService {

    @GET("users")
    fun getListUsers(
    ): Response<ResponseListUsersDto>
}