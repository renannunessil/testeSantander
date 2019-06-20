package br.com.renannunessil.testesantander.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import br.com.renannunessil.testesantander.data.model.AccountLaunchesRequest
import br.com.renannunessil.testesantander.data.model.AccountLaunchesResponse
import br.com.renannunessil.testesantander.data.remote.CallApi
import br.com.renannunessil.testesantander.data.remote.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountLaunchesRepository {
    private val service: CallApi = RetrofitClientInstance.create()
    private lateinit var accountLaunchesResponseLiveData: MutableLiveData<AccountLaunchesResponse>

    fun getAccountLaunches(request: AccountLaunchesRequest) {
        service.getAccountLaunches(request.idUser).enqueue(object : Callback<AccountLaunchesResponse> {
            override fun onFailure(call: Call<AccountLaunchesResponse>, throwable: Throwable) {
                Log.d("CallApi", "Error: ${throwable.message}")
            }

            override fun onResponse(call: Call<AccountLaunchesResponse>, response: Response<AccountLaunchesResponse>) {
                accountLaunchesResponseLiveData.value = response.body()
            }
        })
    }

    fun getAccountLaunchesResponseObservable(): MutableLiveData<AccountLaunchesResponse> {
        if (!::accountLaunchesResponseLiveData.isInitialized) {
            accountLaunchesResponseLiveData = MutableLiveData()
        }

        return accountLaunchesResponseLiveData
    }

    object AccountLaunchesRepositoryProvider {
        fun provideAccountLaunchesRepository(): AccountLaunchesRepository {
            return AccountLaunchesRepository()
        }
    }
}