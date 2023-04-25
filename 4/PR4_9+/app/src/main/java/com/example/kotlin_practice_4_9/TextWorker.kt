package com.example.kotlin_practice_4_9

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class TextWorker(val context: Context, val workParametr: WorkerParameters) : Worker(context, workParametr) {
    override fun doWork(): Result {
        Log.e("test_worker", "text_worker_start")
        val i = 'f'
        val j = 'r'
        val k = 'i'
        val p = 'e'
        val t = 'n'
        val m = 'd'
        val s = "$i$j$k$p$t$m"
        var c = ""
        while (c != "friend") {
            c = (0..5).map{s.random()}.joinToString("")
        }
        val d = workDataOf("data_is" to c)
        Log.e("test_worker", "text_worker_stop")
        return Result.success(d)
    }
}