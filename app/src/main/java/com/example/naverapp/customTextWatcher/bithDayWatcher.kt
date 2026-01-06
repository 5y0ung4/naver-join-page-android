package com.example.naverapp.customTextWatcher

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView

class bithDayWatcher (private val daytxt : TextView) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        if(s.toString().toInt() > 31 || s.toString().toInt() < 1 ) daytxt.visibility = View.VISIBLE
        else daytxt.visibility = View.GONE
    }
}