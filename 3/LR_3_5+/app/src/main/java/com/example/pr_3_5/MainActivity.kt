package com.example.pr_3_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var state = 0
        var btn : Button = findViewById(R.id.button)

        btn.setOnClickListener{

            var manager = supportFragmentManager
            var transaction = manager.beginTransaction()
            var transaction1 = manager.beginTransaction()

            if (state == 0) {
                transaction.replace(R.id.fragmentContainerView, BlankFragment2())
                transaction.replace(R.id.fragmentContainerView2, BlankFragment())
                transaction.commit()
                state = 1
            }
            else if (state == 1){
                transaction1.replace(R.id.fragmentContainerView, BlankFragment())
                transaction1.replace(R.id.fragmentContainerView2, BlankFragment2())
                transaction1.commit()
                state = 0
            }
        }
    }
}