package org.android.go.sopt.remote.domain

import org.android.go.sopt.remote.remoteData.model.ResponseListUsersDto
import retrofit2.Response

interface MainPageRepo {

    suspend fun getUserList():Response<ResponseListUsersDto>
}