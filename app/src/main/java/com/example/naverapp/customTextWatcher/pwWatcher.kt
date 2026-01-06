package com.example.naverapp.customTextWatcher

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class pwWatcher(private val pwtxt : TextView, private val pwctext : TextView, private val pwcedit : EditText,
    private val pwnotavail : TextView, private val pwlock : ImageView,
    private val pwcnotavail : TextView, private val pwclock : ImageView) : TextWatcher {

    private val availableText : String = "abcdefghijgklmnopqrstuvwxyz0123456789*_-!@#^&"
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        val input = s.toString().lowercase()

        if (input.isEmpty()){
            pwtxt.visibility = View.GONE
            pwnotavail.visibility = View.INVISIBLE
            pwlock.setColorFilter(Color.BLACK)
            return
        }

        val isValid = input.all { char ->
            availableText.contains(char) }

        if(s.toString().length < 8 || s.toString().length > 16 || !isValid) {
            pwtxt.visibility = View.VISIBLE
            pwnotavail.visibility = View.VISIBLE
            pwlock.setColorFilter(Color.RED)
        }
        else {
            pwtxt.visibility = View.GONE
            pwnotavail.visibility = View.INVISIBLE
            pwlock.setColorFilter(Color.BLACK)
        }

        if(s.toString().isNotEmpty() && pwcedit.text.toString().isNotEmpty() && pwcedit.text.toString() != s.toString()) {
            pwctext.visibility = View.VISIBLE
            pwcnotavail.visibility = View.VISIBLE
            pwclock.setColorFilter(Color.RED)
        }
        else {
            pwctext.visibility = View.GONE
            pwcnotavail.visibility = View.INVISIBLE
            pwclock.setColorFilter(Color.BLACK)
        }
    }
}