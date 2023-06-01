package org.android.go.sopt.remote.remoteData.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyProfileDto(
    @SerialName("status")
    val status : Int,
    @SerialName("message")
    val message : String,
    @SerialName("data")
    val data : InfoData
){
    @Serializable
    data class InfoData(
        @SerialName("id")
        val id : String,
        @SerialName("name")
        val name : String,
        @SerialName("skill")
        val skill : String
    )
}