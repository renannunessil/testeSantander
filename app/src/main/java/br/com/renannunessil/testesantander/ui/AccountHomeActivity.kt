package br.com.renannunessil.testesantander.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.renannunessil.testesantander.R
import br.com.renannunessil.testesantander.data.model.AccountLaunchesRequest
import br.com.renannunessil.testesantander.data.model.AccountLaunchesResponse
import br.com.renannunessil.testesantander.data.model.Launch
import br.com.renannunessil.testesantander.data.model.UserAccountData
import br.com.renannunessil.testesantander.databinding.ActivityAccountHomeBinding
import br.com.renannunessil.testesantander.extension.brFormmatedCurrency
import br.com.renannunessil.testesantander.ui.adapter.AccountLaunchesAdapter
import br.com.renannunessil.testesantander.utils.Constants
import br.com.renannunessil.testesantander.utils.showLoading
import br.com.renannunessil.testesantander.viewmodel.AccountLaunchesViewModel
import br.com.renannunessil.testesantander.viewmodel.factory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_account_home.*

class AccountHomeActivity: AppCompatActivity() {
    private lateinit var userAccountData: UserAccountData

    private lateinit var accountLaunchesResponse: AccountLaunchesResponse
    private lateinit var binding: ActivityAccountHomeBinding
    private lateinit var viewModel: AccountLaunchesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_home)
        viewModel = ViewModelProviders.of(this, ViewModelFactory())[AccountLaunchesViewModel::class.java]

        getLoginResponse()
        subscribe()
        getAccountLaunches()
        configLogoutOnClickListener()
    }

    private fun configLogoutOnClickListener() {
        iv_home_logout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun subscribe() {
        viewModel.getAccountLaunchesResponseObservable().observe(this, Observer {
            if (it != null) {
                accountLaunchesResponse = it
                configLaunchesList(accountLaunchesResponse.accountLounches)
                showLoading(false, cl_progressbar)
            }
        })
    }

    private fun configLaunchesList(accountLaunches: List<Launch>) {
        val accountLaunchesAdapter = AccountLaunchesAdapter()
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = rv_home_recent_launches

        accountLaunchesAdapter.setData(accountLaunches)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = accountLaunchesAdapter
    }

    private fun getAccountLaunches() {
        val accountLaunchesRequest = getAccountLaunchesRequest()
        showLoading(true, cl_progressbar)
        viewModel.getAccountLaunches(accountLaunchesRequest)
    }

    private fun getAccountLaunchesRequest(): AccountLaunchesRequest {
        return AccountLaunchesRequest(userAccountData.userId.toString())
    }

    private fun getLoginResponse() {
        userAccountData = intent.getParcelableExtra(Constants.USER_ACCOUNT_DATA)
        configUi(userAccountData)
    }

    private fun configUi(userAccountData: UserAccountData) {
        binding.userAccountData = userAccountData
        binding.tvHomeBalance.text = userAccountData.balance.toString().brFormmatedCurrency()
    }

}
