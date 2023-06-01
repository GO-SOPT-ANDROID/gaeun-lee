package org.android.go.sopt.remote.domain

import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.remote.remoteData.model.MyProfileDto
import org.android.go.sopt.remote.remoteData.model.RequestLogInDto
import org.android.go.sopt.remote.remoteData.model.ResponseLogInDto
import retrofit2.Response

interface LoginPageRepo {
    suspend fun login(request: RequestLogInDto): Response<ResponseLogInDto>

    suspend fun signUp(request: RequestSignUpDto) : Response<ResponseSignUpDto>

    suspend fun myProfile(userId : String):Response<MyProfileDto>

}