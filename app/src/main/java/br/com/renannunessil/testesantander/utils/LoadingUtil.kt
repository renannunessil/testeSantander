package br.com.renannunessil.testesantander.utils

import android.view.View

fun showLoading(show: Boolean, loading: View) {
    loading.visibility = if (show) View.VISIBLE else View.GONE
}