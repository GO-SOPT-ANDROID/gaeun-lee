package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.ResponseListUsersDto
import retrofit2.http.GET

interface MainPageService {

    @GET("users")
    suspend fun getListUsers(): ResponseListUsersDto
}
