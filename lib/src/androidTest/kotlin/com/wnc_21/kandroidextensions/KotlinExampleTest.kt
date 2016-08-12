package com.wnc_21.kandroidextensions

import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KotlinExampleTest {

    @Test
    fun initTest() {
        Log.i("Test module", "test message")
        assertTrue(true)
    }
}