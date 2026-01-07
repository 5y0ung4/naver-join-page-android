package com.example.naverapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.naverapp.model.UserResponse
import com.example.naverapp.network.RetrofitClient
import kotlinx.coroutines.launch

val apiService = RetrofitClient.instance
class ProfileActivity : AppCompatActivity() {
    lateinit var btnLayout : LinearLayout
    lateinit var username : TextView; lateinit var phone : Button
    lateinit var emailUpside : TextView; lateinit var eamilDowmSide : Button
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val userId = intent.getStringExtra("userId")
        println("유저 아이디: $userId")

        btnLayout = findViewById<LinearLayout>(R.id.buttonBox)

        lifecycleScope.launch {
            val user: UserResponse? = getUserProfile(userId.toString())
            user?.let {
                // UI 업데이트
                findViewById<TextView>(R.id.usernameProfile).text = it.name
                findViewById<TextView>(R.id.emailUpside).text = it.email
            }
        }



    }

    suspend fun getUserProfile(request: String): UserResponse? {
        return try {
            val response = apiService.getUser(request)
            if (response.isSuccessful) {
                println("유저 정보 받아오기 성공")
                response.body()  // UserResponse 반환
            } else {
                println("유저 정보 받기 실패 에러 코드 ${response.code()}")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}