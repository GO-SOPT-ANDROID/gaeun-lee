package org.android.go.sopt.remote.service

import org.android.go.sopt.remote.remoteData.model.ResponseListUsersDto
import retrofit2.http.GET

interface MainPageService {

    @GET("users")
    suspend fun getListUsers(): ResponseListUsersDto
}
