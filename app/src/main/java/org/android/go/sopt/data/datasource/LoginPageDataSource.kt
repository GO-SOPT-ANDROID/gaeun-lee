package org.android.go.sopt.data.datasource

import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.data.model.MyProfileDto
import org.android.go.sopt.data.model.RequestLogInDto
import org.android.go.sopt.data.model.ResponseLogInDto
import org.android.go.sopt.data.service.LogInPageService
import org.android.go.sopt.domain.LoginPageRepository

class LoginPageDataSource(private val apiService: LogInPageService) : LoginPageRepository {
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
