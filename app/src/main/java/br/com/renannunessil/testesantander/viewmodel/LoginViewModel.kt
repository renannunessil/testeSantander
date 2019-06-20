package br.com.renannunessil.testesantander.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.renannunessil.testesantander.data.model.LoginRequest
import br.com.renannunessil.testesantander.data.model.LoginResponse
import br.com.renannunessil.testesantander.repository.LoginRepository

class LoginViewModel(repository: LoginRepository) : ViewModel() {

    private val loginRepository = repository
    private lateinit var loginResponse: MutableLiveData<LoginResponse>
    private lateinit var loginFailure: MutableLiveData<String>

    fun getLoginResponseObservable(): LiveData<LoginResponse> {
        if (!::loginResponse.isInitialized) {
            loginResponse = loginRepository.getLoginResponseObservable()
        }

        return loginResponse
    }

    fun getLoginFailureObservable(): LiveData<String> {
        if (!::loginFailure.isInitialized) {
            loginFailure = loginRepository.getLoginErrorObservable()
        }

        return loginFailure
    }

    fun login(request: LoginRequest) {
        loginRepository.login(request)
    }
}