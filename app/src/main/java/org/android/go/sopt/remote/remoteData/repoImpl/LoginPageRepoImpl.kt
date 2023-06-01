package org.android.go.sopt.remote.remoteData.repoImpl

import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.remote.remoteData.datasource.LoginPageDataSource
import org.android.go.sopt.remote.remoteData.model.MyProfileDto
import org.android.go.sopt.remote.remoteData.model.RequestLogInDto
import org.android.go.sopt.remote.remoteData.model.ResponseLogInDto
import retrofit2.Response

class LoginPageRepoImpl(private val loginPageDataSource: LoginPageDataSource) {

    suspend fun login(request:RequestLogInDto):Response<ResponseLogInDto>{
        return loginPageDataSource.login(request)
    }

    suspend fun signUp(request:RequestSignUpDto):Response<ResponseSignUpDto>{
        return loginPageDataSource.signUp(request)
    }

    suspend fun myProfile(userId:String):Response<MyProfileDto>{
        return loginPageDataSource.myProfile(userId)
    }
}