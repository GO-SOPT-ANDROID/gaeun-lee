package org.android.go.sopt.remote.remoteData.repoImpl

import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.remote.domain.LoginPageRepo
import org.android.go.sopt.remote.remoteData.datasource.LoginPageDataSource
import org.android.go.sopt.remote.remoteData.model.MyProfileDto
import org.android.go.sopt.remote.remoteData.model.RequestLogInDto
import org.android.go.sopt.remote.remoteData.model.ResponseLogInDto

class LoginPageRepoImpl(private val loginPageDataSource: LoginPageDataSource):LoginPageRepo {

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
