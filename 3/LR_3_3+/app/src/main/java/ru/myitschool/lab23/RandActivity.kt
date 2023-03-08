package ru.myitschool.lab23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class RandActivity : AppCompatActivity() {

    fun getRandomList(random: Random, num : Int): Array<Int> =
        Array(num) { random.nextInt(0, 100) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rand)


        var SArray: Array<String> = arrayOf("s","asd","hgfh")
        val recyclerView: RecyclerView = findViewById(R.id.generated_list)
        //recyclerView.adapter = RVAdapter(SArray)
        recyclerView.adapter!!.notifyDataSetChanged()
/*
        val randomValues1 = getRandomList(Random(12), 5)
        //val randomValues1 = getRandomList(Random(a.num1 * a.num2), a.num3)
        val stringArray = randomValues1.map { it.toString() }.toTypedArray()
        var SArray: Array<String> = arrayOf("s","asd","hgfh")

        val recyclerView: RecyclerView = findViewById(R.id.generated_list)
        recyclerView.adapter = RVAdapter(SArray)

        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, SArray[0], duration).show()
        */

    }
}