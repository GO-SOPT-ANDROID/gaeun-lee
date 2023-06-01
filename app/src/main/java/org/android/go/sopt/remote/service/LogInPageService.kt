package org.android.go.sopt.remote.service

import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.remote.remoteData.model.MyProfileDto
import org.android.go.sopt.remote.remoteData.model.RequestLogInDto
import org.android.go.sopt.remote.remoteData.model.ResponseLogInDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LogInPageService {
    @POST("sign-in")
    fun login(
        @Body request: RequestLogInDto,
    ): Call<ResponseLogInDto>

    @POST("sign-up")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>


    @GET("info/{userId}")
    fun myProfile(@Path("userId") userId: String):Call<MyProfileDto>

}