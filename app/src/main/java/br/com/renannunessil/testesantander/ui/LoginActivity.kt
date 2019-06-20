package br.com.renannunessil.testesantander.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.renannunessil.testesantander.data.model.LoginRequest
import br.com.renannunessil.testesantander.data.model.LoginResponse
import br.com.renannunessil.testesantander.utils.Constants
import br.com.renannunessil.testesantander.utils.showLoading
import br.com.renannunessil.testesantander.viewmodel.LoginViewModel
import br.com.renannunessil.testesantander.viewmodel.factory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import android.net.ConnectivityManager
import br.com.renannunessil.testesantander.R


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this,
            ViewModelFactory())[LoginViewModel::class.java]

        configOnClickListeners()
        subscribe()
        getSharedPreferences()
    }

    private fun saveOnSharedPreferences(user: String, password: String) {
        with (sharedPreferences.edit()) {
            putString(Constants.USER_ON_SHARED_PREFERENCES, user)
            putString(Constants.PASSWORD_ON_SHARED_PREFERENCES, password)
            apply()
        }
    }

    private fun getSharedPreferences() {
        sharedPreferences = applicationContext.getSharedPreferences(Constants.USER_ON_SHARED_PREFERENCES,
            Context.MODE_PRIVATE)

        et_login_username.setText( sharedPreferences.getString(Constants.USER_ON_SHARED_PREFERENCES, Constants.DEFAULT_SHARED_PREFERENCES_VALUE))
        et_login_password.setText(sharedPreferences.getString(Constants.PASSWORD_ON_SHARED_PREFERENCES, Constants.DEFAULT_SHARED_PREFERENCES_VALUE))
    }



    private fun subscribe() {
        loginViewModel.getLoginResponseObservable().observe(this, Observer {
            if (it != null) {
                onLoginSuccess(it)
                saveOnSharedPreferences(et_login_username.text.toString(), et_login_password.text.toString())
            }
        })

        loginViewModel.getLoginFailureObservable().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            showLoading(false, cl_progressbar)
        })
    }

    private fun onLoginSuccess(loginResponse: LoginResponse) {
        intent = Intent(this, AccountHomeActivity::class.java)
        intent.putExtra(Constants.USER_ACCOUNT_DATA ,loginResponse.userAccountData)
        startActivity(intent)
        finish()
    }

    private fun configOnClickListeners() {
        bt_login.setOnClickListener {
            callLogin()
        }
    }
    private fun callLogin() {
        val loginRequest = getLoginRequest()
        if (isNetworkAvailable()) {
            showLoading(true, cl_progressbar)
            loginViewModel.login(loginRequest)
        } else {
            Toast.makeText(this, "Internet Indisponível", Toast.LENGTH_LONG).show()
        }
    }

    private fun getLoginRequest(): LoginRequest {
        val user = if (et_login_username.text.toString().isNotEmpty()) {
            et_login_username.text.toString()
        } else {
            ""
        }

        val password = if (et_login_password.text.toString().isNotEmpty()){
            et_login_password.text.toString()
        } else {
            ""
        }
        return LoginRequest(user = user, password = password)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}