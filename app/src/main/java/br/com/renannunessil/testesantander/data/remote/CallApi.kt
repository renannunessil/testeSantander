package br.com.renannunessil.testesantander.data.remote

import br.com.renannunessil.testesantander.data.model.AccountLaunchesResponse
import br.com.renannunessil.testesantander.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface CallApi {
    @FormUrlEncoded
        @POST("login")
        fun callLogin(@Field("user") user: String,
                      @Field("password") password: String): Call<LoginResponse>

        @GET("statements/{idUser}")
        fun getAccountLaunches(
        @Path("idUser") idUser: String
        ): Call<AccountLaunchesResponse>
}