package com.wnc_21.kandroidextensions.view

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

fun FragmentManager.isFragmentAdded(tag: String) : Boolean  = findFragmentByTag(tag) != null

fun FragmentManager.addIfNot(tag: String,  transaction: (FragmentTransaction) -> Unit) : Boolean {
    if (!isFragmentAdded(tag)) {
        val tr = beginTransaction()
        transaction(tr)
        tr.commit()

        return true
    }

    return false
}