package com.example.kotlingame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

    }

    fun Start_game(view: View) {
        val intent = Intent(this@MainActivity, TapTheNumChallenge::class.java)
        startActivity(intent)
    }


}