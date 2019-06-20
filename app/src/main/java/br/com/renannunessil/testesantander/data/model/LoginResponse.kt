package br.com.renannunessil.testesantander.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class LoginResponse (
    @SerializedName("userAccount")
    val userAccountData: UserAccountData
) : Parcelable
