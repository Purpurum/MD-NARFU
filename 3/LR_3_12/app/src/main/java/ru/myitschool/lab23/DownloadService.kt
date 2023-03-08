package ru.myitschool.lab23

import android.app.job.JobService
import android.app.job.JobParameters
import ru.myitschool.lab23.MainActivity
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import android.app.PendingIntent
import android.content.Intent
import ru.myitschool.lab23.R
import androidx.core.app.NotificationManagerCompat

class DownloadService : JobService() {
    private val NOTIFICATION_CHANNEL_ID = "property"
    private val NOTIFICATION_CHANNEL_NAME = "Update requested"
    override fun onStartJob(jobParameters: JobParameters): Boolean {
        createNotificationChanel()
        sendNotification(jobParameters.extras.getString(MainActivity.keyText))
        return false
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return false
    }

    fun createNotificationChanel() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager?.createNotificationChannel(channel)
    }

    fun sendNotification(message: String?) {
        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(NOTIFICATION_CHANNEL_NAME)
            .setContentText(message)
            .setContentIntent(
                PendingIntent.getActivity(
                    this, 0,
                    Intent(this, MainActivity::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
        NotificationManagerCompat.from(this).notify(0, builder.build())
    }
}