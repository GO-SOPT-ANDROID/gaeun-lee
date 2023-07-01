package org.android.go.sopt.data.repository

import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.data.datasource.LoginPageDataSource
import org.android.go.sopt.data.model.MyProfileDto
import org.android.go.sopt.data.model.RequestLogInDto
import org.android.go.sopt.data.model.ResponseLogInDto
import org.android.go.sopt.domain.LoginPageRepo

class LoginPageRepoImpl(private val loginPageDataSource: LoginPageDataSource) : LoginPageRepo {

    override suspend fun login(request: RequestLogInDto): ResponseLogInDto {
        return loginPageDataSource.login(request)
    }

    override suspend fun signUp(request: RequestSignUpDto): ResponseSignUpDto {
        return loginPageDataSource.signUp(request)
    }

    override suspend fun myProfile(userId: String): MyProfileDto {
        return loginPageDataSource.myProfile(userId)
    }
}
