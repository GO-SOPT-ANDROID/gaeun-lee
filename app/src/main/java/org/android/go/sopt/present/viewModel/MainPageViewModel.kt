package org.android.go.sopt.present.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.remote.remoteData.model.ResponseListUsersDto
import org.android.go.sopt.remote.remoteData.repoImpl.MainPageRepoImpl

class MainPageViewModel(private val mainPageRepoImpl: MainPageRepoImpl) : ViewModel() {

    private val _userList = MutableLiveData<List<ResponseListUsersDto.Data>>()
    val userList: LiveData<List<ResponseListUsersDto.Data>> get() = _userList

    fun getUserList() = viewModelScope.launch {
        kotlin.runCatching {
            mainPageRepoImpl.getUserList()
        }.onSuccess { response ->
            _userList.value = response.data
        }.onFailure {
            Log.d("mainPageViewModel", "서버 에러 발생")
        }
    }
}
