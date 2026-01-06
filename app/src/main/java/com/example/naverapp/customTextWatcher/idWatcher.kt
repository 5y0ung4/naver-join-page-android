package com.example.naverapp.customTextWatcher

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView

class idWatcher (private val errorid : TextView) : TextWatcher {
    private val availText = "abcdefghijklmnopqrstuvwxyz0123456789_-"
    override fun afterTextChanged(s: Editable?) {
        val input = s.toString().lowercase()

        if (input.isEmpty()){
            errorid.visibility = View.GONE
            return
        }

        val isValid = input.all { char ->
            availText.contains(char) }

        if(s.toString().length < 5 || s.toString().length > 20 || !isValid) {
            errorid.visibility = View.VISIBLE
        }
        else {
            errorid.visibility = View.GONE
        }
    }

    override fun beforeTextChanged(
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {

    }

    override fun onTextChanged(
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {

    }

}