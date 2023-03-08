package ru.myitschool.trajectorymocktest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        var requestData: TextView = findViewById(R.id.requested_data)
        var requestData2: TextView = findViewById(R.id.requested_data2)
        val retrofit = Retrofit.Builder().baseUrl(connectionURL).addConverterFactory(GsonConverterFactory.create()).build()
        val service: UserController = retrofit.create(UserController::class.java)
        buttonSend.setOnClickListener {
            requestData.text="Ща всё буит"
            requestData2.text="Ща всё буитх2"
            val call: Call<User> = service.getUser(editTextLogin.text.toString())
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.code() == 200) {
                        val user: User? = response.body()
                        requestData2.text = user?.public_repos.toString()
                        requestData.text = user?.name.toString()//Вот это необязательно, можно удалить, либо закомментить
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    //Пустышка, чтоб не жаловалось, какой же этот язык душный
                }
            })
        }
    }
}

