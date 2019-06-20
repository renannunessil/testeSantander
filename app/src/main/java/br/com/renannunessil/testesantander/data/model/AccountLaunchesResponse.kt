package br.com.renannunessil.testesantander.data.model

import com.google.gson.annotations.SerializedName

class AccountLaunchesResponse (
    @SerializedName("statementList")
    val accountLounches: List<Launch>
)
