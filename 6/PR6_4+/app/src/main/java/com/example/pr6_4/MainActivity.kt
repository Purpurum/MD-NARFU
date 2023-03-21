package com.example.pr6_4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // здесь нужно указать URL проекта на Heroku
    private val BD_URL: String = "https://music-a177.restdb.io/rest/music/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BD_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service: MusicController = retrofit.create(MusicController::class.java)
        val id: EditText = findViewById(R.id.id)
        val name: EditText = findViewById(R.id.name)
        val phone: EditText = findViewById(R.id.album)
        val result: TextView = findViewById(R.id.result)
        val create: Button = findViewById(R.id.create)
        val update: Button = findViewById(R.id.update)
        val delete: Button = findViewById(R.id.delete)
        val readEntry: Button = findViewById(R.id.readEntry)
        val read: Button = findViewById(R.id.read)

        read.setOnClickListener({
            service.read().enqueue( object: Callback<List<MusicEntry>>{
                override fun onResponse(call: Call<List<MusicEntry>>, response:
                Response<List<MusicEntry>>) {
                    val list=response.body()
                    result. text="ID\tTrack\tAlbum\n";
                    for (entry in list.orEmpty()) {
                        result.text=result.text.toString() + entry._id+"\t"+entry.name+"\t"+entry.album+"\n";
                    }
                }
                override fun onFailure(call: Call<List<MusicEntry>>, t: Throwable) {}
            })
        })
        readEntry.setOnClickListener({
            if(id.text.isEmpty()) {
                return@setOnClickListener
            }
            service.readEntry(id.text.toString()).enqueue(object: Callback<MusicEntry>{
                override fun onResponse(call: Call<MusicEntry>, response: Response<MusicEntry>) {
                    val entry=response.body()
                    if (entry != null) {
                        result.text=""+entry._id+"\t"+entry.name+"\t"+entry.album+"\n"
                    };
                }
                override fun onFailure(call: Call<MusicEntry>, t: Throwable) {}
            })
        })
        create.setOnClickListener({
            service.create(MusicEntry("0",name.text.toString(),phone.text.toString())).enqueue(object:
                Callback<Int>{
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    result.text="Song created, ID: "+response.body()
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {}
            })
        })
        delete.setOnClickListener({
            if(id.text.isEmpty()) {
                return@setOnClickListener
            }
            service.delete(id.text.toString()).enqueue(object: Callback<Boolean>{
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    result.text=""+response.body()
                }
                override fun onFailure(call: Call<Boolean>, t: Throwable) { }
            })
        })
        update.setOnClickListener({
            if(id.text.isEmpty()) {
                return@setOnClickListener
            }

            service.update(MusicEntry(id.text.toString(),name.text.toString(),phone.text.toString())).enqueue(object: Callback<Boolean>{
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    result.text=""+response.body()
                }
                override fun onFailure(call: Call<Boolean>, t: Throwable) { }
            })
        })



    }
}