package com.example.naverapp

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var hamburger : ImageButton; lateinit var qrcode : ImageButton
    lateinit var searchBar : EditText;
    lateinit var dl_main : DrawerLayout
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        dl_main = findViewById<DrawerLayout>(R.id.dl_main)

        qrcode = findViewById<ImageButton>(R.id.qrcode)
        qrcode.setOnClickListener {
            finish()
        }

        hamburger = findViewById<ImageButton>(R.id.hamburger)
        hamburger.setOnClickListener {
            dl_main.openDrawer(GravityCompat.START)
        }

        val navView = findViewById<NavigationView>(R.id.nav_view)

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    // 홈 페이지 이동
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                R.id.menu_setting -> {
                    // 설정 페이지 이동
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
            }

            // 드로어 닫기
            dl_main.closeDrawer(GravityCompat.START)
            true
        }


    }
}