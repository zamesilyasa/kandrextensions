package com.wnc_21.kandroidextensions.view

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.View
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestViewVisibility {
    private lateinit var context: Context

    @Before
    fun runBeforeEachTest() {
        context = InstrumentationRegistry.getContext()
    }

    @Test
    fun setGone_Should_set_GONE_Visibility_flag() {
        val view: View = View(context)

        assertEquals(View.VISIBLE, view.visibility)

        view.setGone(true)

        assertEquals(View.GONE, view.visibility)

        view.setGone(false)

        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun setInvisible_Should_set_INVISIBLE_Visibility_flag() {
        val view: View = View(context)

        assertEquals(View.VISIBLE, view.visibility)

        view.setVisible(false)

        assertEquals(View.INVISIBLE, view.visibility)

        view.setVisible(true)

        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun isVisible_Should_return_visible_state_bool() {
        val view: View = View(context)

        assertTrue(view.isVisible())

        view.setVisible(false)
        assertFalse(view.isVisible())

        view.setVisible(true)
        assertTrue(view.isVisible())

        view.setGone(true)
        assertFalse(view.isVisible())

        view.setGone(false)
        assertTrue(view.isVisible())
    }
}
