package org.android.go.sopt.remote.remoteData.repoImpl

import org.android.go.sopt.remote.domain.MainPageRepo
import org.android.go.sopt.remote.remoteData.datasource.MainPageDataSource
import org.android.go.sopt.remote.remoteData.model.ResponseListUsersDto

class MainPageRepoImpl(
    private val mainPagerDataSource: MainPageDataSource,
) : MainPageRepo {
    override suspend fun getUserList(): ResponseListUsersDto {
        return mainPagerDataSource.getUserList()
    }
}
