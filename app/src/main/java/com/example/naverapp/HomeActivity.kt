package com.example.naverapp

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    lateinit var hamburger : ImageButton; lateinit var qrcode : ImageButton
    lateinit var searchBar : EditText;
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        qrcode = findViewById<ImageButton>(R.id.qrcode)
        qrcode.setOnClickListener {
            finish()
        }

        hamburger = findViewById<ImageButton>(R.id.hamburger)
        hamburger.setOnClickListener {

        }

    }
}