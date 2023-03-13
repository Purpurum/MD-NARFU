package ru.samsung.itacademy.mdev.getusdrate

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BoundService : Service() {

    private val binder = MyBinder()

    inner class MyBinder : Binder() {
        fun getService(): BoundService {
            return this@BoundService
        }
    }

    val usdRate = MutableLiveData<String>()
    val rateCheckInteractor = RateCheckInteractor()

    fun onRefreshClicked() {
        refreshRate()
    }

    fun getUSDRate(): String? {
        refreshRate()
        Toast.makeText(this, "Функция вызвалась... вроде", Toast.LENGTH_SHORT)
        return usdRate.value

    }
    private fun refreshRate() {
        GlobalScope.launch(Dispatchers.Main) {
            val rate = rateCheckInteractor.requestRate()
            usdRate.value = rate

        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        refreshRate()
        return binder
    }
}