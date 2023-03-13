package ru.samsung.itacademy.mdev.getusdrate

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var textRate: TextView
    lateinit var textTargetRate: EditText
    lateinit var rootView: View
    lateinit var btn: Button

    private lateinit var myService: BoundService
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BoundService.MyBinder
            myService = binder.getService()
            isBound = true
            
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, BoundService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        textRate = findViewById(R.id.textUsdRubRate)
        textTargetRate = findViewById(R.id.textTargetRate)
        rootView = findViewById(R.id.rootView)
        btn = findViewById(R.id.btnSubscribeToRate)

        findViewById<Button>(R.id.btnRefresh).setOnClickListener {
            SetRate()
        }

        btn.setOnClickListener {
            if (isBound) {
                val targetRate = textTargetRate.text.toString()
                val startRate = myService.getUSDRate()

                if (targetRate.isNotEmpty() && startRate?.isNotEmpty() == true) {
                    RateCheckService.stopService(this)
                    RateCheckService.startService(this, startRate, targetRate)
                } else if (targetRate.isEmpty()) {
                    Snackbar.make(rootView, R.string.target_rate_empty, Snackbar.LENGTH_SHORT)
                        .show()
                } else if (startRate.isNullOrEmpty()) {
                    Snackbar.make(rootView, R.string.current_rate_empty, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }




    }
    fun SetRate() {
        if (isBound) {
            textRate.text = "${myService.getUSDRate()} RUB"
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}