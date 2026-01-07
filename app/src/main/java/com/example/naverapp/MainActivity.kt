package com.example.naverapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.lifecycle.lifecycleScope
import com.example.naverapp.customTextWatcher.bithDayWatcher
import com.example.naverapp.customTextWatcher.idWatcher
import com.example.naverapp.customTextWatcher.nameWatcher
import com.example.naverapp.customTextWatcher.pwWatcher
import com.example.naverapp.customTextWatcher.pwcWatcher
import com.example.naverapp.model.UserRequest
import com.example.naverapp.model.UserResponse
import com.example.naverapp.network.ApiService
import com.example.naverapp.network.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var birthMonth : Spinner; lateinit var sex : Spinner
    lateinit var signup : Button

    // error message
    lateinit var errorID : TextView; lateinit var errorPW : TextView
    lateinit var errorPWcheck : TextView; lateinit var errorName : TextView
    lateinit var errorEmail : TextView; lateinit var errorBirth : TextView
    lateinit var pwNotAvail : TextView; lateinit var pwcNotAvail : TextView

    // error image
    lateinit var lockpw : ImageView; lateinit var lockpwc : ImageView

    // edit text
    lateinit var userId : EditText; lateinit var password : EditText
    lateinit var passwordCheck : EditText; lateinit var username : EditText
    lateinit var birthYear : EditText; lateinit var birthDay : EditText
    lateinit var email : EditText; lateinit var phone : EditText

    val apiService = RetrofitClient.instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val home = Intent(this, HomeActivity::class.java)

        signup = findViewById<Button>(R.id.signup)
        signup.setBackgroundColor("#00aa55".toColorInt())

        // edit text initiate
        userId = findViewById<EditText>(R.id.userId)
        password = findViewById<EditText>(R.id.password)
        passwordCheck = findViewById<EditText>(R.id.passwordCheck)
        birthYear = findViewById<EditText>(R.id.year)
        birthDay = findViewById<EditText>(R.id.day)
        email = findViewById<EditText>(R.id.email)
        username = findViewById<EditText>(R.id.name)
        phone = findViewById<EditText>(R.id.phone)

        //error image initiate
        lockpw = findViewById<ImageView>(R.id.lockPW)
        lockpwc = findViewById<ImageView>(R.id.lockPWC)

        // error text initiate
        errorID = findViewById<TextView>(R.id.errorID)
        errorPW = findViewById<TextView>(R.id.errorPW)
        errorPWcheck = findViewById<TextView>(R.id.errorPWcheck)
        errorName = findViewById<TextView>(R.id.errorName)
        errorEmail = findViewById<TextView>(R.id.errorEmail)
        errorBirth = findViewById<TextView>(R.id.errorBirth)
        pwNotAvail = findViewById<TextView>(R.id.pwNotAvail)
        pwcNotAvail = findViewById<TextView>(R.id.pwcNotAvail)

        errorID.visibility = View.GONE
        errorPW.visibility = View.GONE
        errorPWcheck.visibility = View.GONE
        errorName.visibility = View.GONE
        errorEmail.visibility = View.GONE

        userId.addTextChangedListener(idWatcher(errorID))
        password.addTextChangedListener(
            pwWatcher(
                errorPW,
                errorPWcheck,
                passwordCheck,
                pwNotAvail,
                lockpw,
                pwcNotAvail,
                lockpwc
            )
        )
        passwordCheck.addTextChangedListener(
            pwcWatcher(
                errorPWcheck,
                password,
                pwcNotAvail,
                lockpwc
            )
        )
        username.addTextChangedListener(nameWatcher(errorName))
        birthDay.addTextChangedListener(bithDayWatcher(errorBirth))

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

        var datasex : String
        if (sex.selectedItem == "여성"){
            datasex = "FEMALE"
        } else if (sex.selectedItem == "남성"){
            datasex = "MALE"
        } else {
            datasex = "OTHER"
        }

        signup.setOnClickListener {
            val request = UserRequest(userId.text.toString(), password.text.toString(), username.text.toString(),
                birthYear.text.toString(), birthMonth.selectedItem.toString(), birthDay.text.toString(),
                datasex, phone.text.toString(), email.text.toString())

            lifecycleScope.launch {
                try {
                    val result = apiService.sendUser(request)
                    Log.d("MainActivity", "성공: $result")
                    // 서버 성공
                    home.putExtra("userId", userId.text.toString())
                    startActivity(home)
                } catch (e: Exception) {
                    Log.e("MainActivity", "통신 에러", e)
                }
            }
        }

    }

    fun sendDataToServer(request: UserRequest) {
        lifecycleScope.launch {
            try {
                val result = apiService.sendUser(request)
                Log.d("MainActivity", "성공: $result")
            } catch (e: Exception) {
                Log.e("MainActivity", "통신 에러", e)
            }
        }
    }



}