package br.com.renannunessil.testesantander.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.renannunessil.testesantander.data.model.AccountLaunchesRequest
import br.com.renannunessil.testesantander.data.model.AccountLaunchesResponse
import br.com.renannunessil.testesantander.repository.AccountLaunchesRepository

class AccountLaunchesViewModel(repository: AccountLaunchesRepository): ViewModel() {
    private val accountLaunchesRepository = repository
    private lateinit var accountLaunchesResponseLiveData: MutableLiveData<AccountLaunchesResponse>

    fun getAccountLaunchesResponseObservable(): LiveData<AccountLaunchesResponse> {
        if (!::accountLaunchesResponseLiveData.isInitialized) {
            accountLaunchesResponseLiveData = accountLaunchesRepository.getAccountLaunchesResponseObservable()
        }

        return accountLaunchesResponseLiveData
    }

    fun getAccountLaunches(request: AccountLaunchesRequest) {
        accountLaunchesRepository.getAccountLaunches(request)
    }
}