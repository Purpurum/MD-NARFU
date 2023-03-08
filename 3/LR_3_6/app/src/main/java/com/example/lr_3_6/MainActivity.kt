package com.example.lr_3_6


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lr_3_6.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private fun loadData() {
        val colorNames = resources.getStringArray(R.array.colorNames)
        for (i in colorNames.indices) {
            val ta = resources.obtainTypedArray(R.array.colors)
            colors.add(ColorItem(colorNames[i], ta.getResourceId(i, 0)))
        }
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var colors = mutableListOf<ColorItem>()
    private lateinit var colorsAdapter: ColorsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadData()
        var recyclerView = RecyclerView(baseContext)
        colorsAdapter = ColorsAdapter(colors){

            binding.fragment2.setBackgroundResource(it.color)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = colorsAdapter
        }
        binding.fragment1.addView(recyclerView)
    }
}