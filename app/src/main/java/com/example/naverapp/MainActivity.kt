package com.example.naverapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var birthMonth : Spinner; lateinit var sex : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        birthMonth = findViewById<Spinner>(R.id.spinnerMonth)
        sex = findViewById<Spinner>(R.id.sex)

        val monthadapter = ArrayAdapter.createFromResource(
            this,
            R.array.month_array,
            android.R.layout.simple_spinner_item
        )

        val sexadapter = ArrayAdapter.createFromResource(
            this,
            R.array.sexArray,
            android.R.layout.simple_spinner_item
        )

        monthadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        birthMonth.adapter = monthadapter

        sexadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sex.adapter = sexadapter

    }
}