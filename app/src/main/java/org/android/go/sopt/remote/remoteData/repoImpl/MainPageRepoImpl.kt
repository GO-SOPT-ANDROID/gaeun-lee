package org.android.go.sopt.remote.remoteData.repoImpl

import org.android.go.sopt.remote.remoteData.datasource.MainPageDataSource
import org.android.go.sopt.remote.remoteData.model.ResponseListUsersDto
import retrofit2.Response

class MainPageRepoImpl(
    private val mainPagerDataSource: MainPageDataSource
) {
    suspend fun getUserList():Response<ResponseListUsersDto>{
        return mainPagerDataSource.getUserList()
    }
}