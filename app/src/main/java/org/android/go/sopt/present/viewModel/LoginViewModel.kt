package org.android.go.sopt.present.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.remote.ServicePool
import org.android.go.sopt.remote.remoteData.model.RequestLogInDto
import org.android.go.sopt.remote.remoteData.model.ResponseLogInDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel:ViewModel() {
    val loginResult: MutableLiveData<ResponseLogInDto> = MutableLiveData()
    private val loginService = ServicePool.loginPageService


    fun login(id: String, password: String) {
        loginService.login(
            RequestLogInDto(
                id,
                password
            )
        ).enqueue(object : Callback<ResponseLogInDto> {
            override fun onResponse(
                call: Call<ResponseLogInDto>,
                response: Response<ResponseLogInDto>,
            ) {
                if (response.isSuccessful) {
                    loginResult.value = response.body()



                } else {
                    // TODO: 에러 처리
                }
            }

            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable) {

            }
        })
    }




}