package com.example.zerogram.utilities

import android.text.Editable
import android.text.TextWatcher

open class AppTextWatcher(val onSuccess:(Editable?) -> Unit): TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        onSuccess(s)
    }
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}