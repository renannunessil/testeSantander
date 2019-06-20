package br.com.renannunessil.testesantander.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.renannunessil.testesantander.data.model.Launch
import br.com.renannunessil.testesantander.databinding.ItemRecentLaunchesListBinding
import br.com.renannunessil.testesantander.extension.brDateFormat
import br.com.renannunessil.testesantander.extension.brFormmatedCurrency
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AccountLaunchesAdapter: RecyclerView.Adapter<AccountLaunchesAdapter.
                                                            AccountLaunchesViewHolder>(){

        private var accountLaunchesList: List<Launch> = ArrayList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountLaunchesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemRecentLaunchesListBinding.inflate(inflater, parent, false)
            return AccountLaunchesViewHolder(binding)
        }

        override fun getItemCount(): Int = accountLaunchesList.size

        override fun onBindViewHolder(holder: AccountLaunchesViewHolder, position: Int)
                = holder.bind(accountLaunchesList[position])

        fun setData(accountLaunches: List<Launch>) {
            this.accountLaunchesList = accountLaunches
            notifyDataSetChanged()
        }

        inner class AccountLaunchesViewHolder(private val binding: ItemRecentLaunchesListBinding):
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Launch) {
                val date = getCalendarObjectFromLaunchDate(item.launchDate).brDateFormat()
                val value = item.launchValue.toString().brFormmatedCurrency()

                binding.launch = item
                binding.tvLaunchDate.text = date
                binding.tvLaunchValue.text = value
            }

            private fun getCalendarObjectFromLaunchDate(launchDate: String): Calendar {
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                val date = simpleDateFormat.parse(launchDate)
                val calendar = Calendar.getInstance()

                calendar.time = date

                return calendar
            }

        }
}