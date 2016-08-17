package com.wnc_21.kandroidextensions.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wnc_21.kandroidextensions.view.addIfNot

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportFragmentManager.addIfNot(MainFragment.TAG, {
            it.add(R.id.fragment_container, MainFragment.create(), MainFragment.TAG)
        })
    }
}
