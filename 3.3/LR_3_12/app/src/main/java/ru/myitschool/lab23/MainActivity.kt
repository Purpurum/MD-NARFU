package ru.myitschool.lab23

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import ru.myitschool.lab23.R
import android.app.job.JobScheduler
import android.os.PersistableBundle
import ru.myitschool.lab23.MainActivity
import android.app.job.JobInfo
import android.content.ComponentName
import android.view.View
import ru.myitschool.lab23.DownloadService

class MainActivity : AppCompatActivity() {
    private var propertyText: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val textBundle = PersistableBundle()
        textBundle.putString(keyText, "RESUMED")
        val jobInfo = JobInfo.Builder(13, ComponentName(this, DownloadService::class.java))
            .setExtras(textBundle).build()
        jobScheduler.schedule(jobInfo)

        propertyText = findViewById(R.id.property_text)
        findViewById<View>(R.id.submit_button).setOnClickListener { v: View? ->
            val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            val textBundle = PersistableBundle()
            textBundle.putString(keyText, propertyText!!.text.toString())
            val jobInfo = JobInfo.Builder(13, ComponentName(this, DownloadService::class.java))
                .setExtras(textBundle).build()
            jobScheduler.schedule(jobInfo)
        }
    }

    companion object {
        const val keyText = "Property Key"
    }
}