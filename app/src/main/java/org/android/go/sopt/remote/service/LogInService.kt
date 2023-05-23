package org.android.go.sopt.remote.service

import org.android.go.sopt.remote.model.RequestLogInDto
import org.android.go.sopt.remote.model.ResponseLogInDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInService {
    @POST("sign-in")
    fun login(
        @Body request: RequestLogInDto,
    ): Call<ResponseLogInDto>
}