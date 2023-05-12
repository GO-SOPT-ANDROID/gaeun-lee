package org.android.go.sopt.remote.service

import org.android.go.sopt.remote.model.ResponseListUsersDto
import retrofit2.Call
import retrofit2.http.GET

interface ListUsersService {
    @GET("users")
    fun getListUsers(
    ): Call<ResponseListUsersDto>
}