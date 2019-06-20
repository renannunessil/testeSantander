package br.com.renannunessil.testesantander.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ApiError (
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    var message: String
): Parcelable
