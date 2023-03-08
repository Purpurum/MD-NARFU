package ru.myitschool.lab22toast

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
        Log.d(TAG, s)
    }

    private val TAG = "condition"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMessage("Activity CREATED")
    }
    override fun onStart() {
        super.onStart()
        showMessage("Activity STARTED")
        Log.i("MainActivity", "onStart() called")
    }
    override fun onRestart() {
        super.onRestart()
        showMessage("Activity RESTARTED")
        Log.i("MainActivity", "onRestart() called")
    }
    override fun onResume() {
        super.onResume()
        showMessage("Activity RESUMED")
        Log.i("MainActivity", "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        showMessage("Activity PAUSED")
        Log.i("MainActivity", "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        showMessage("Activity STOPPED")
        Log.i("MainActivity", "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        showMessage("Activity DESTROYED")
        Log.i("MainActivity", "onDestroy() called")
    }
}