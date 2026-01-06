package com.example.naverapp.customTextWatcher

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView

class nameWatcher(private val nametxt : TextView) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        if(s.toString().length > 10) nametxt.visibility = View.VISIBLE
        else nametxt.visibility = View.GONE
    }
}