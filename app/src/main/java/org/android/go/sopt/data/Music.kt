package org.android.go.sopt.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(
    val music: String,
    val singer: String
) : Parcelable
