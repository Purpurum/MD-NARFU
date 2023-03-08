package ru.samsung.itacademy.mdev.getusdrate

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val usdRate = MutableLiveData<String>()
    val rateCheckInteractor = RateCheckInteractor()

    @SuppressLint("StaticFieldLeak")
    var myService: BoundService? = null

    fun onCreate() {
        refreshRate()
    }

    fun onRefreshClicked() {
        refreshRate()
    }

    private fun refreshRate() {
        myService?.getRate()
    }

    companion object {
        const val TAG = "MainViewModel"
        const val USD_RATE_URL = "https://www.freeforexapi.com/api/live?pairs=USDRUB"
    }
}