package org.android.go.sopt.present.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.remote.ServicePool
import org.android.go.sopt.remote.remoteData.model.MyProfileDto
import org.android.go.sopt.remote.remoteData.model.RequestLogInDto
import org.android.go.sopt.remote.remoteData.model.ResponseLogInDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel:ViewModel() {
    private val loginService = ServicePool.loginPageService


    private val _loginResult = MutableLiveData<ResponseLogInDto>()
    val loginResult :LiveData<ResponseLogInDto> get() = _loginResult

    private val _signUpResult = MutableLiveData<ResponseSignUpDto>()
    val signUpResult:LiveData<ResponseSignUpDto> get() = _signUpResult

    private val _myProfile = MutableLiveData<MyProfileDto>()
    val myProfile:LiveData<MyProfileDto> get() = _myProfile


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
                    _loginResult.value = response.body()



                } else {

                }
            }

            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable) {

            }
        })
    }

    fun signUp(id:String, password:String, name:String, skill:String){
        loginService.signUp(RequestSignUpDto(id,password,name,skill)).enqueue(object : Callback<ResponseSignUpDto>{
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>
            ) {
                if(response.isSuccessful){
                    _signUpResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {

            }


        })
    }

    fun myProfile(userId:String){
        loginService.myProfile(userId).enqueue(object:Callback<MyProfileDto>{
            override fun onResponse(call: Call<MyProfileDto>, response: Response<MyProfileDto>) {
                if(response.isSuccessful){
                    _myProfile.value = response.body()
                }
            }

            override fun onFailure(call: Call<MyProfileDto>, t: Throwable) {
            }

        })
    }




}