package study.android.kotlindbnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import study.android.kotlindbnotes.databinding.ActivityMainBinding
import androidx.lifecycle.Observer as Observer



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val db by lazy {
        AppDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val context = this
        GlobalScope.launch {
            for (company in TestData.russianCompanies2020) {
                db.resultsDao().insert(company)
            }
        }
        binding.companiesList.layoutManager = LinearLayoutManager(this)
        db.resultsDao().getAll("RESULT DESC").observe(this,
            { results -> binding.companiesList.adapter = ResultAdapter(results) })

        binding.statistics.setOnClickListener {
            startActivity(Intent(this, StatActivity::class.java))
        }

        binding.delete.setOnClickListener {
            GlobalScope.launch {
                db.resultsDao().deleteByName("%" + binding.toDelete.text.toString() + "%")
            }
        }
    }
}