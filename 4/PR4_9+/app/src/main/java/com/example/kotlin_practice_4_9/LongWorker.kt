package com.example.kotlin_practice_4_9

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class LongWorker(val context: Context, val workParametr: WorkerParameters) : CoroutineWorker(context, workParametr) {
    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {
        Log.e("test_worker", "long_worker_start")
        val data1 = inputData.getString("data_is")
        val data2 = inputData.getInt("click", 100)

        var i = data1!!.length * 10000
        var j = data2
        var p: Long = 0

        while (i > 0){
            while (j > 0){
                p += i % j
                j --
            }
            i --
            j = data2
        }

        Log.e("test_worker", "long_worker_stop with rezult $p")
        return Result.Success(workDataOf("p" to p))
    }
}

//var i: Int = (1 until data1!!.length * 10000).random()
//val j: Int = (1..data2).random()