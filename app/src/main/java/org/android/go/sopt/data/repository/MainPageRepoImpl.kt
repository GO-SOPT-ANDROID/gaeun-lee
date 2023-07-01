package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.MainPageDataSource
import org.android.go.sopt.data.model.ResponseListUsersDto
import org.android.go.sopt.domain.MainPageRepository

class MainPageRepoImpl(
    private val mainPagerDataSource: MainPageDataSource,
) : MainPageRepository {
    override suspend fun getUserList(): ResponseListUsersDto {
        return mainPagerDataSource.getUserList()
    }
}
