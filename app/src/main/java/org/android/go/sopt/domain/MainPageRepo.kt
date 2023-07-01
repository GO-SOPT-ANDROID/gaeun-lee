package org.android.go.sopt.domain

import org.android.go.sopt.data.model.ResponseListUsersDto

interface MainPageRepo {

    suspend fun getUserList(): ResponseListUsersDto
}
