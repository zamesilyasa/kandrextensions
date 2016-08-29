package com.wnc_21.kandroidextensions.view

import android.app.Activity
import android.app.Service
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Fragment.hideKeyboard() {
    hideKeyboardOnView(view)
}

fun Activity.hideKeyboard() {
    hideKeyboardOnView(window.decorView.rootView)
}

fun View.hideKeyboard() {
    hideKeyboardOnView(this)
}

private fun hideKeyboardOnView(view: View?) {
    if (view != null) {
        val imm = view.context.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}