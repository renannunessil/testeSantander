package br.com.renannunessil.testesantander.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import br.com.renannunessil.testesantander.data.model.LoginRequest
import br.com.renannunessil.testesantander.data.model.LoginResponse
import br.com.renannunessil.testesantander.data.remote.CallApi
import br.com.renannunessil.testesantander.data.remote.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {
    private val service: CallApi = RetrofitClientInstance.create()
    private lateinit var loginResponseLiveData: MutableLiveData<LoginResponse>

    fun login(request: LoginRequest) {
        service.callLogin(request.user, request.password).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, throwable: Throwable) {
                Log.d("CallApi", "Error: ${throwable.message}")
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    loginResponseLiveData.value = response.body()
                }
            }
        })
    }

    fun getLoginResponseObservable(): MutableLiveData<LoginResponse> {
        if (!::loginResponseLiveData.isInitialized) {
            loginResponseLiveData = MutableLiveData()
        }

        return loginResponseLiveData
    }

    object LoginRepositoryProvider {
        fun provideLoginRepository(): LoginRepository {
            return LoginRepository()
        }
    }
}