package org.android.go.sopt.remote.domain

import org.android.go.sopt.remote.remoteData.model.ResponseListUsersDto

interface MainPageRepo {

    suspend fun getUserList(): ResponseListUsersDto
}
