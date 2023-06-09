package org.android.go.sopt.remote.remoteData.datasource

import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.remote.domain.LoginPageRepo
import org.android.go.sopt.remote.remoteData.model.MyProfileDto
import org.android.go.sopt.remote.remoteData.model.RequestLogInDto
import org.android.go.sopt.remote.remoteData.model.ResponseLogInDto
import org.android.go.sopt.remote.service.LogInPageService

class LoginPageDataSource(private val apiService: LogInPageService) : LoginPageRepo {
    override suspend fun login(request: RequestLogInDto): ResponseLogInDto {
        return apiService.login(request)
    }

    override suspend fun signUp(request: RequestSignUpDto): ResponseSignUpDto {
        return apiService.signUp(request)
    }

    override suspend fun myProfile(userId: String): MyProfileDto {
        return apiService.myProfile(userId)
    }
}
