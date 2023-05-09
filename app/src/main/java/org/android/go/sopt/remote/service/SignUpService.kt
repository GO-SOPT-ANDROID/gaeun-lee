package org.android.go.sopt.remote.service

import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("sign-up")
    fun login(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>
}