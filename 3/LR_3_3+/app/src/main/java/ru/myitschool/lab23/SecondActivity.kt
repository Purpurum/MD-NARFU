/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.myitschool.lab23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {

    fun getRandomList(random: Random, num : Int): Array<Int> =
        Array(num) { random.nextInt(0, 100) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val randomValues1 = getRandomList(Random(intent.getIntExtra("num1", 1) * intent.getIntExtra("num2",1)), intent.getIntExtra("num3",1))
        //val randomValues1 = getRandomList(Random(12), 3)
        val stringArray = randomValues1.map { it.toString() }.toTypedArray()

        var SArray: Array<String> = arrayOf("asd","hgfh")
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = FlowerAdapter(stringArray)
        recyclerView.adapter!!.notifyDataSetChanged()
    }
}
