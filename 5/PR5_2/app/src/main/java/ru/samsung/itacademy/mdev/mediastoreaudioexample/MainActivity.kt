package ru.samsung.itacademy.mdev.mediastoreaudioexample

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    lateinit var textResults: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResults = findViewById(R.id.textResults)

        findViewById<Button>(R.id.btnAudio).setOnClickListener {
            loadAudio()
        }

    }

    private fun loadAudio() {
        if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
        ) {
            fetchAudioAndShowResult()
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQ_AUDIO
            )
        }
    }

    private fun fetchAudioAndShowResult() {
        val stringBuilder = StringBuilder()

        val projection = arrayOf( // media-database-columns-to-retrieve
                MediaStore.Audio.AudioColumns._ID,
                MediaStore.Audio.AudioColumns.DISPLAY_NAME,
                MediaStore.Audio.AudioColumns.MIME_TYPE
        )

        val selection = null // sql-where-clause-with-placeholder-variables
        val selectionArgs = null // values-of-placeholder-variables
        val sortOrder = "${MediaStore.Audio.AudioColumns._ID} DESC LIMIT 20" // sql-order-by-clause

        applicationContext.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs
        )?.use { cursor ->
            val nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME)
            val typeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.MIME_TYPE)

            while (cursor.moveToNext()) {
                stringBuilder.append(cursor.getString(nameColumn)).append("\n")
                        .append(cursor.getString(typeColumn)).append("\n\n")
            }
            textResults.text = stringBuilder
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
        ) {
               fetchAudioAndShowResult()
        } else {
            Snackbar.make(
                    findViewById(android.R.id.content),
                    "Can't get data without permission",
                    Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val REQ_AUDIO = 1
    }
}