package com.example.naverapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.example.naverapp.customTextWatcher.bithDayWatcher
import com.example.naverapp.customTextWatcher.idWatcher
import com.example.naverapp.customTextWatcher.nameWatcher
import com.example.naverapp.customTextWatcher.pwWatcher
import com.example.naverapp.customTextWatcher.pwcWatcher

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
    lateinit var email : EditText


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

        signup.setOnClickListener {
            startActivity(home)
        }

    }



}