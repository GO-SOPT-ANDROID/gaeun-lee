package org.android.go.sopt.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.android.go.sopt.present.viewModel.LoginPageViewModel
import org.android.go.sopt.present.viewModel.MainPageViewModel
import org.android.go.sopt.remote.ServicePool
import org.android.go.sopt.remote.remoteData.datasource.LoginPageDataSource
import org.android.go.sopt.remote.remoteData.datasource.MainPageDataSource
import org.android.go.sopt.remote.remoteData.repoImpl.LoginPageRepoImpl
import org.android.go.sopt.remote.remoteData.repoImpl.MainPageRepoImpl

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginPageViewModel::class.java) -> {
                val repository = LoginPageRepoImpl(LoginPageDataSource(ServicePool.loginPageService))
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