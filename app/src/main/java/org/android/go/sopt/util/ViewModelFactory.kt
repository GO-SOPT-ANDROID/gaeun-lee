package org.android.go.sopt.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.android.go.sopt.data.ServicePool
import org.android.go.sopt.data.datasource.LoginPageDataSource
import org.android.go.sopt.data.datasource.MainPageDataSource
import org.android.go.sopt.data.repository.LoginPageRepoImpl
import org.android.go.sopt.data.repository.MainPageRepoImpl
import org.android.go.sopt.present.viewModel.LoginPageViewModel
import org.android.go.sopt.present.viewModel.MainPageViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginPageViewModel::class.java) -> {
                val repository =
                    LoginPageRepoImpl(LoginPageDataSource(ServicePool.loginPageService))
                LoginPageViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MainPageViewModel::class.java) -> {
                val repository = MainPageRepoImpl(MainPageDataSource(ServicePool.mainPageService))
                MainPageViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel : ${modelClass.name}")
            }
        }
    }
}
