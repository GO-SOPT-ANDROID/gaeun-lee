package org.android.go.sopt.data.service

import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.data.model.MyProfileDto
import org.android.go.sopt.data.model.RequestLogInDto
import org.android.go.sopt.data.model.ResponseLogInDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LogInPageService {
    @POST("sign-in")
    suspend fun login(
        @Body request: RequestLogInDto,
    ): ResponseLogInDto

    @POST("sign-up")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): ResponseSignUpDto

    @GET("info/{userId}")
    suspend fun myProfile(@Path("userId") userId: String): MyProfileDto
}
