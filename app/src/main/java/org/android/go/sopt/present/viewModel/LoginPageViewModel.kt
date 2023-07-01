package org.android.go.sopt.present.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.data.model.MyProfileDto
import org.android.go.sopt.data.model.RequestLogInDto
import org.android.go.sopt.data.repository.LoginPageRepoImpl
import org.android.go.sopt.util.Event
import org.android.go.sopt.util.MyApplication

class LoginPageViewModel(private val loginPageRepoImpl: LoginPageRepoImpl) : ViewModel() {
    private val _loginResult = MutableLiveData<Event<Boolean>>()
    val loginResult: LiveData<Event<Boolean>> get() = _loginResult

    private val _signUpResult = MutableLiveData<Boolean>()
    val signUpResult: LiveData<Boolean> get() = _signUpResult

    private val _getMyProfile = MutableLiveData<MyProfileDto>()
    val getMyProfile: LiveData<MyProfileDto> get() = _getMyProfile

    val signUpId = MutableStateFlow("")
    val signUpPwd = MutableStateFlow("")

    val isValidId: StateFlow<Boolean> = signUpId.map { inputId ->
        inputId.matches(Regex(idPattern))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), true)

    val isValidPwd: StateFlow<Boolean> = signUpPwd.map { inputPwd ->
        inputPwd.matches(Regex(pwPattern))
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        false,
    )
    val canClickSignUpBtn: StateFlow<Boolean> =
        combine(isValidId, isValidPwd) { isValidId, isValidPwd ->
            isValidId && isValidPwd
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

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

    companion object {
        const val idPattern = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,10}\$"
        const val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{6,12}\$"
    }
}
