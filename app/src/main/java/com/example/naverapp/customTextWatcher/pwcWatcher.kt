package com.example.naverapp.customTextWatcher

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class pwcWatcher(private val pwctxt : TextView, private val pw : EditText,
    private val locktxt : TextView, private val lockimage : ImageView) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        val pwcheck = s?.toString() ?: ""
        val pworigin = pw.text.toString()
        if(pwcheck != pworigin && pwcheck != "") {
            pwctxt.visibility = View.VISIBLE
            locktxt.visibility = View.VISIBLE
            lockimage.setColorFilter(Color.RED)
        }
        else {
            pwctxt.visibility = View.GONE
            locktxt.visibility = View.INVISIBLE
            lockimage.setColorFilter(Color.BLACK)
        }

    }
}