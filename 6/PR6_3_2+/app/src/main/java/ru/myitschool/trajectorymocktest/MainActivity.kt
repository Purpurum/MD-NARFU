package ru.myitschool.trajectorymocktest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.myitschool.trajectorymocktest.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private val connectionURL: String = "https://gitee.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var editTextLogin: EditText = findViewById(R.id.edit_text_login)
        var buttonSend: Button = findViewById(R.id.button_send)
        var requestData: TextView = findViewById(R.id.textView)
        var requestData2: TextView = findViewById(R.id.textView2)
        var requestData3: TextView = findViewById(R.id.textView3)
        var requestData4: TextView = findViewById(R.id.textView4)
        var button: Button = findViewById(R.id.button)
        val retrofit = Retrofit.Builder().baseUrl(connectionURL).addConverterFactory(GsonConverterFactory.create()).build()
        val service: UserController = retrofit.create(UserController::class.java)



        button.setOnClickListener{
            requestData.text = "Тута буит имечко"
            requestData3.text = "Тута буит датка"
            requestData2.text = "Тута будут репки"
            requestData4.text = "Тута буит помоешный ник"
            editTextLogin.text = null
        }

        requestData4.setOnClickListener {
            if (requestData4.text != "null" || requestData.text != "Pi") {
                val twitterIntent = packageManager.getLaunchIntentForPackage("com.twitter.android")
                if (twitterIntent != null) {
                    startActivity(twitterIntent)
                } else {
                    // Twitter app not installed, open Twitter website in browser
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/${requestData4.text}")
                    )
                    startActivity(browserIntent)
                }
            }
        }

        buttonSend.setOnClickListener {
            //Оповещение
            val notificationManager = ContextCompat.getSystemService(this,NotificationManager::class.java) as NotificationManager
            val channelId = "github"
            val contextt = this

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(channelId,"github",NotificationManager.IMPORTANCE_HIGH).apply {
                    enableVibration(true)
                    vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                }
                notificationManager.createNotificationChannel(notificationChannel)
            }

            //Запрос
            requestData.text="Ща всё буит"
            requestData2.text="Ща всё буитх2"
            requestData3.text="Ща всё буитх3"
            requestData4.text="Ща всё буитх4"
            val call: Call<User> = service.getUser(editTextLogin.text.toString().trim())
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.code() == 200) {
                        val user: User? = response.body()
                        requestData.text = user?.login.toString()
                        requestData3.text = user?.created_at.toString()
                        requestData2.text = user?.public_repos.toString()
                        requestData4.text = user?.twitter_username.toString()
                        //Вызов оповещения
                        val notificationBuilder = NotificationCompat.Builder(contextt, channelId)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("Инфа о юзере")
                            .setContentText("${user?.login.toString()}" + "\n" +
                                    "${user?.created_at.toString()}" + "\n" +
                                    "${user?.public_repos.toString()}" + "\n" +
                                    "${user?.twitter_username.toString()}" + "\n")
                            .setPriority(NotificationCompat.PRIORITY_MIN)
                        notificationManager.notify(0, notificationBuilder.build())
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    //Пустышка, чтоб не жаловалось, какой же этот язык душный
                }
            })


        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        var requestData: TextView = findViewById(R.id.textView)
        var requestData2: TextView = findViewById(R.id.textView2)
        var requestData3: TextView = findViewById(R.id.textView3)
        var requestData4: TextView = findViewById(R.id.textView4)
        super.onSaveInstanceState(outState)
        outState.putString("name", requestData.text.toString())
        outState.putString("count", requestData2.text.toString())
        outState.putString("date", requestData3.text.toString())
        outState.putString("twitter", requestData4.text.toString())
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        var requestData: TextView = findViewById(R.id.textView)
        var requestData2: TextView = findViewById(R.id.textView2)
        var requestData3: TextView = findViewById(R.id.textView3)
        var requestData4: TextView = findViewById(R.id.textView4)
        super.onRestoreInstanceState(savedInstanceState)
        val name = savedInstanceState.getString("name")
        val count = savedInstanceState.getString("count")
        val date = savedInstanceState.getString("date")
        val twitter = savedInstanceState.getString("twitter")
        requestData.setText(name)
        requestData2.setText(count)
        requestData3.setText(date)
        requestData4.setText(twitter)
    }
}

