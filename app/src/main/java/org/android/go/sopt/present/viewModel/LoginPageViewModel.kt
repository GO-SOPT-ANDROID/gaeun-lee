package org.android.go.sopt.present.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.remote.remoteData.model.RequestLogInDto
import org.android.go.sopt.remote.remoteData.repoImpl.LoginPageRepoImpl
import org.android.go.sopt.util.Event
import org.android.go.sopt.util.MyApplication

class LoginPageViewModel(private val loginPageRepoImpl: LoginPageRepoImpl) : ViewModel() {
    private val _loginResult = MutableLiveData(Event(false))
    val loginResult: LiveData<Event<Boolean>> get() = _loginResult

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
}
