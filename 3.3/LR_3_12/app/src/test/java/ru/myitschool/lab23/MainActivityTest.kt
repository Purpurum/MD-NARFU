package ru.myitschool.lab23

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.junit.Assert.*
import ru.myitschool.lab23.DownloadService
import org.junit.Test

class MainActivityTest {

    @Test
    fun onResume() {
        DownloadService().sendNotification("RESUMED")
    }
}