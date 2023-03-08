package com.example.pr5_5

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.MediaController
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    private var videoView: VideoView? = null
    private var videoFilePath = ""
    var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        videoView = findViewById(R.id.video)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSION)
        }
        button?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                openVideoIntent()
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO) {
            if (resultCode == Activity.RESULT_OK) {

                videoView?.setVideoURI(Uri.parse(videoFilePath))
                videoView?.start()


            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openVideoIntent() {
        val videoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)

        if (videoIntent.resolveActivity(packageManager) != null) {
            var videoFile: File? = null
            videoFile = try {
                createVideoFile()
            } catch (e: IOException) {
                e.printStackTrace()
                return
            }
            val videoUri: Uri? =
                videoFile?.let { FileProvider.getUriForFile(this, "$packageName.provider", it) }
            videoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri)

            startActivityForResult(videoIntent, REQUEST_VIDEO)
        }

    }

    @Throws(IOException::class)
    private fun createVideoFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "VIDEO_" + timeStamp + "_"
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_MOVIES)
        val video: File = File.createTempFile(imageFileName, ".mp4", storageDir)
        videoFilePath = video.getAbsolutePath()
        return video
    }


    companion object {
        const val REQUEST_VIDEO = 100
        const val REQUEST_PERMISSION = 200
    }
}