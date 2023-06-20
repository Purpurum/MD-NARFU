package study.android.kotlindbnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import study.android.kotlindbnotes.databinding.ActivityStatBinding

class StatActivity : AppCompatActivity() {
    lateinit var binding: ActivityStatBinding
    val db by lazy {
        AppDatabase.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        GlobalScope.launch {
            binding.money.text = db.resultsDao().sumCapitalization().toString()
        }
        GlobalScope.launch {
            binding.good.text = db.resultsDao().highCapitalization().toString()
        }
        GlobalScope.launch {
        }

        GlobalScope.launch {
            binding.best.text = db.resultsDao().bestCapitalization()
        }

        GlobalScope.launch {
            binding.english.text = db.resultsDao().enCapitalization().toString()
        }

        GlobalScope.launch {
            binding.longest.text = db.resultsDao().longestName()
        }
    }
}