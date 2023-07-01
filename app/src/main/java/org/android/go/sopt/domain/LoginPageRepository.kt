package org.android.go.sopt.domain

import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.data.model.MyProfileDto
import org.android.go.sopt.data.model.RequestLogInDto
import org.android.go.sopt.data.model.ResponseLogInDto

interface LoginPageRepository {
    suspend fun login(request: RequestLogInDto): ResponseLogInDto

    suspend fun signUp(request: RequestSignUpDto): ResponseSignUpDto

    suspend fun myProfile(userId: String): MyProfileDto
}
