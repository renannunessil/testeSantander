package br.com.renannunessil.testesantander.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import br.com.renannunessil.testesantander.repository.AccountLaunchesRepository
import br.com.renannunessil.testesantander.repository.LoginRepository
import br.com.renannunessil.testesantander.viewmodel.AccountLaunchesViewModel
import br.com.renannunessil.testesantander.viewmodel.LoginViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        return if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val repository = LoginRepository.LoginRepositoryProvider.provideLoginRepository()
            LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(AccountLaunchesViewModel::class.java)) {
            val repository = AccountLaunchesRepository.AccountLaunchesRepositoryProvider.provideAccountLaunchesRepository()
            AccountLaunchesViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}