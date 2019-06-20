package br.com.renannunessil.testesantander.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserAccountData (
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("bankAccount")
    val bankAccount: String,

    @SerializedName("agency")
    val agency: String,

    @SerializedName("balance")
    val balance: Double
) : Parcelable {

    fun getFormattedAgencyAndAccount(): String {
        val builder = StringBuilder(this.agency)
        builder.append(" / ")
        builder.append(this.bankAccount)
        return builder.toString()
    }


}
