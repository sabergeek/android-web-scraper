package com.example.webscraper.utilities

import android.content.Context
import android.widget.Toast

var toast: Toast? = null

fun Context.longToast(message: String) {
    toast?.cancel()
    if (toast == null) {
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
    }
    toast?.show()
}

fun Context.shortToast(message: String) {
    toast?.cancel()
    if (toast == null) {
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    }
    toast?.show()
}