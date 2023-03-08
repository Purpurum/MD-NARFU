package ru.myitschool.lab23_5_1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pr5_1.R
import java.io.File
import java.io.FileOutputStream
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_write_external = findViewById<Button>(R.id.btn_write_external)
        val btn_write_internal = findViewById<Button>(R.id.btn_write_internal)
        val et_words_input = findViewById<EditText>(R.id.et_words_input)

        val file_name = "words.txt"

        btn_write_internal.setOnClickListener{
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput(file_name, MODE_APPEND)
                if (et_words_input.text.contains(",")) {
                    val text_file = et_words_input.text.toString().split(",")
                    for (word in text_file) {
                        fileOutputStream.write(word.replace(" ", "").toByteArray())
                        fileOutputStream.write("\n".toByteArray())
                    }
                }
                else {
                    val text_file = et_words_input.text.toString()
                    fileOutputStream.write(text_file.replace(" ", "").toByteArray())
                    fileOutputStream.write("\n".toByteArray())
                }
                fileOutputStream.close()
                et_words_input.text.clear()
                Toast.makeText(this, "File saved at internal storage", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Can't open file", Toast.LENGTH_SHORT).show()
            }
        }

        btn_write_external.setOnClickListener{
            val calendar = Calendar.getInstance()
            val month = calendar.get(Calendar.MONTH) + 1
            val file_path = "/storage/emulated/0/Android/ru.myitschool.lab23/files/$month/"

            val myExternalFile = File(getExternalFilesDir(file_path), file_name)
            val fileOutPutStream = FileOutputStream(myExternalFile)
            try {
                if (et_words_input.text.contains(",")) {
                    val text_file = et_words_input.text.toString().split(",")
                    for (word in text_file) {
                        fileOutPutStream.write(word.replace(" ", "").toByteArray())
                        fileOutPutStream.write("\n".toByteArray())
                    }
                }
                else {
                    val text_file = et_words_input.text.toString()
                    fileOutPutStream.write(text_file.replace(" ", "").toByteArray())
                    fileOutPutStream.write("\n".toByteArray())
                }
                fileOutPutStream.close()
                et_words_input.text.clear()
                Toast.makeText(this, "File saved at external storage", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Can't open file", Toast.LENGTH_SHORT).show()
            }
        }
    }
}