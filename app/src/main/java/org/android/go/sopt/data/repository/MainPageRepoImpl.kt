package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.MainPageDataSource
import org.android.go.sopt.data.model.ResponseListUsersDto
import org.android.go.sopt.domain.MainPageRepo

class MainPageRepoImpl(
    private val mainPagerDataSource: MainPageDataSource,
) : MainPageRepo {
    override suspend fun getUserList(): ResponseListUsersDto {
        return mainPagerDataSource.getUserList()
    }
}
