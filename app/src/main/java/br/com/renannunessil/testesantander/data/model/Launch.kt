package br.com.renannunessil.testesantander.data.model

import com.google.gson.annotations.SerializedName

class Launch (
    @SerializedName("title")
    val launchTitle: String,

    @SerializedName("desc")
    val launchDescription: String,

    @SerializedName("date")
    val launchDate: String,

    @SerializedName("value")
    val launchValue: Double
)
