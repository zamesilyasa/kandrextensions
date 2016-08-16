package com.wnc_21.kandroidextensions.view

import android.os.Bundle
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import java.io.FileDescriptor
import java.io.PrintWriter

@RunWith(AndroidJUnit4::class)
class TestSupportFragmentManager {

    @Test
    fun testSupportFragmentManager_searchesForFragmentByTag() {
        val mgrWithFragment: FragmentManager = object: MockSupportFragmentManager() {
            override fun findFragmentByTag(tag: String?): Fragment? = Fragment()
        }

        assertTrue(mgrWithFragment.isFragmentAdded("ANY"))

        val mgrWithoutFragment: FragmentManager = object : MockSupportFragmentManager() {
            override fun findFragmentByTag(tag: String?): Fragment? {
                return null
            }
        }

        assertFalse(mgrWithoutFragment.isFragmentAdded("ANY"))
    }
}

abstract class MockSupportFragmentManager : FragmentManager() {
    override fun saveFragmentInstanceState(f: Fragment?): Fragment.SavedState {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun findFragmentById(id: Int): Fragment {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun getFragments(): MutableList<Fragment>? {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun beginTransaction(): FragmentTransaction {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun putFragment(bundle: Bundle?, key: String?, fragment: Fragment?) {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun removeOnBackStackChangedListener(listener: OnBackStackChangedListener?) {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun getFragment(bundle: Bundle?, key: String?): Fragment {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun getBackStackEntryCount(): Int {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun isDestroyed(): Boolean {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun getBackStackEntryAt(index: Int): BackStackEntry {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun executePendingTransactions(): Boolean {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun popBackStackImmediate(): Boolean {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun popBackStackImmediate(name: String?, flags: Int): Boolean {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun popBackStackImmediate(id: Int, flags: Int): Boolean {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun findFragmentByTag(tag: String?): Fragment? {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun addOnBackStackChangedListener(listener: OnBackStackChangedListener?) {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun dump(prefix: String?, fd: FileDescriptor?, writer: PrintWriter?, args: Array<out String>?) {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun popBackStack() {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun popBackStack(name: String?, flags: Int) {
        throw UnsupportedOperationException("Not mocked")
    }

    override fun popBackStack(id: Int, flags: Int) {
        throw UnsupportedOperationException("Not mocked")
    }

}