package com.wnc_21.kandroidextensions.view

import android.support.v4.app.FragmentManager

fun FragmentManager.isFragmentAdded(tag: String) : Boolean  = findFragmentByTag(tag) != null