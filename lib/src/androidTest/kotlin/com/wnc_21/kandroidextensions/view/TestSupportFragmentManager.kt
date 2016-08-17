package com.wnc_21.kandroidextensions.view

import android.os.Bundle
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import java.io.FileDescriptor
import java.io.PrintWriter

@RunWith(AndroidJUnit4::class)
class TestSupportFragmentManager {

    @Test
    fun SupportFragmentManager_searchesForFragmentByTag() {
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

    @Test
    fun SupportFragmentManager_add_fragment_ifNot() {
        var tag: String? = null

        val tr = object: MockTransaction() {
            override fun add(containerViewId: Int, fragment: Fragment?, trTag: String?): FragmentTransaction {
                tag = trTag
                return this
            }
        }

        val fm = object: MockSupportFragmentManager() {

            override fun findFragmentByTag(aTag: String?): Fragment? = if (tag == aTag) Fragment() else null

            override fun beginTransaction(): FragmentTransaction {
                return tr
            }
        }

        assertFalse(fm.isFragmentAdded("TAG"))

        assertTrue(fm.addIfNot("TAG", {it.add(1, Fragment(), "TAG")}))

        assertTrue(fm.isFragmentAdded("TAG"))

        assertFalse(fm.addIfNot("TAG", {it.add(1, Fragment(), "TAG")}))
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

abstract class MockTransaction: FragmentTransaction() {
    override fun setBreadCrumbShortTitle(res: Int): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBreadCrumbShortTitle(text: CharSequence?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun commit(): Int {
        return 0
    }

    override fun add(fragment: Fragment?, tag: String?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(containerViewId: Int, fragment: Fragment?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(containerViewId: Int, fragment: Fragment?, tag: String?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hide(fragment: Fragment?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replace(containerViewId: Int, fragment: Fragment?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replace(containerViewId: Int, fragment: Fragment?, tag: String?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun detach(fragment: Fragment?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun commitAllowingStateLoss(): Int {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCustomAnimations(enter: Int, exit: Int): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCustomAnimations(enter: Int, exit: Int, popEnter: Int, popExit: Int): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addToBackStack(name: String?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun disallowAddToBackStack(): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTransitionStyle(styleRes: Int): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTransition(transit: Int): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attach(fragment: Fragment?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun show(fragment: Fragment?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(fragment: Fragment?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isAddToBackStackAllowed(): Boolean {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addSharedElement(sharedElement: View?, name: String?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun commitNow() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBreadCrumbTitle(res: Int): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBreadCrumbTitle(text: CharSequence?): FragmentTransaction {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun commitNowAllowingStateLoss() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}