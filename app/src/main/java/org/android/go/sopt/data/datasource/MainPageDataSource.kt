package org.android.go.sopt.data.datasource

import org.android.go.sopt.data.model.ResponseListUsersDto
import org.android.go.sopt.data.service.MainPageService
import org.android.go.sopt.domain.MainPageRepo

class MainPageDataSource(private val apiService: MainPageService) : MainPageRepo {
    override suspend fun getUserList(): ResponseListUsersDto {
        return apiService.getListUsers()
    }
}
