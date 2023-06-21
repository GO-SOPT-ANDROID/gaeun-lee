package org.android.go.sopt.present.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.remote.ServicePool
import org.android.go.sopt.remote.remoteData.model.ResponseListUsersDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPageViewModel : ViewModel() {
    private val mainPageService = ServicePool.mainPageService

    private val _userList = MutableLiveData<ResponseListUsersDto>()
    val userList: LiveData<ResponseListUsersDto> get() = _userList

    fun gerUserList() {
        mainPageService.getListUsers().enqueue(object : Callback<ResponseListUsersDto> {
            override fun onResponse(
                call: Call<ResponseListUsersDto>,
                response: Response<ResponseListUsersDto>
            ) {
                if (response.isSuccessful){
                    _userList.value  = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseListUsersDto>, t: Throwable) {

            }
        })
    }

}