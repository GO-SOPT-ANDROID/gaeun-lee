package org.android.go.sopt.data

sealed class DataObject {

    data class TopRvTitle(
        val title: String,
    ) : DataObject()

    data class Music(
        val music: String,
        val singer: String,
    ) : DataObject()

    data class BottomSponsor(
        val sponsor:String
    ) : DataObject()

}