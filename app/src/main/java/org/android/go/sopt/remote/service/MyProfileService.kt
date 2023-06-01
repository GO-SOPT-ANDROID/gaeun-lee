package org.android.go.sopt.remote.service

import kotlinx.serialization.Serializable
import org.android.go.sopt.remote.model.MyProfileDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyProfileService {
    @GET("info/{userId}")
    fun myProfile(@Path("userId") userId: String):Call<MyProfileDto>
}