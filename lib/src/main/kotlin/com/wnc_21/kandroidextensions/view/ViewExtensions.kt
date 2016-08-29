package com.wnc_21.kandroidextensions.view

import android.view.View

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.setGone(gone: Boolean) {
    this.visibility = if (gone) View.GONE else View.VISIBLE
}

fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}