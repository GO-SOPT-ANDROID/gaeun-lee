package org.android.go.sopt.present.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.remote.remoteData.model.MyProfileDto
import org.android.go.sopt.remote.remoteData.model.RequestLogInDto
import org.android.go.sopt.remote.remoteData.repoImpl.LoginPageRepoImpl
import org.android.go.sopt.util.Event
import org.android.go.sopt.util.MyApplication

class LoginPageViewModel(private val loginPageRepoImpl: LoginPageRepoImpl) : ViewModel() {
    private val _loginResult = MutableLiveData<Event<Boolean>>()
    val loginResult: LiveData<Event<Boolean>> get() = _loginResult

    private val _signUpResult = MutableLiveData<Boolean>()
    val signUpResult: LiveData<Boolean> get() = _signUpResult

    private val _getMyProfile = MutableLiveData<MyProfileDto>()
    val getMyProfile: LiveData<MyProfileDto> get() = _getMyProfile

    fun login(request: RequestLogInDto) = viewModelScope.launch {
        kotlin.runCatching {
            loginPageRepoImpl.login(request)
        }.onSuccess {
            _loginResult.value = Event(true)
            MyApplication.mySharedPreferences.setUserId(request.id)
        }.onFailure {
            _loginResult.value = Event(false)
        }
    }

    fun signUp(request: RequestSignUpDto) = viewModelScope.launch {
        kotlin.runCatching {
            loginPageRepoImpl.signUp(request)
        }.onSuccess {
            _signUpResult.value = true
        }.onFailure {
            _signUpResult.value = false
        }
    }

    fun getMyProfile(userId: String) = viewModelScope.launch {
        kotlin.runCatching {
            loginPageRepoImpl.myProfile(userId)
        }.onSuccess { response ->
            _getMyProfile.value = response
        }.onFailure {
            Log.d("error", "서버 통신 실패")
        }
    }
}
