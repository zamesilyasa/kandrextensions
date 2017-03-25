package com.wnc_21.kandroidextensions.db

import android.database.MatrixCursor
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestAsFinalList {

    @Test
    fun Cursor_should_be_mapped_to_collection() {
        val c: MatrixCursor = MatrixCursor(arrayOf("test_column"))

        c.addRow(arrayOf("v1"))
        c.addRow(arrayOf("v2"))
        c.addRow(arrayOf("v3"))

        val finalList: List<String> = c.asFinalList { it.getString(0) }

        Assert.assertTrue(finalList.size == 3)
        Assert.assertTrue(finalList.contains("v1"))
        Assert.assertTrue(finalList.contains("v2"))
        Assert.assertTrue(finalList.contains("v3"))
    }

    @Test
    fun Cursor_should_be_closed_by_default_after_mapping() {
        val c: MatrixCursor = MatrixCursor(arrayOf("test_column"))

        c.addRow(arrayOf("v1"))
        c.asFinalList { it.getString(0) }

        Assert.assertTrue(c.isClosed)
    }

    @Test
    fun Cursor_should_be_closed_if_it_is_empty() {
        val c: MatrixCursor = MatrixCursor(arrayOf("test_column"))
        val emptyList = c.asFinalList { c.getString(0) }

        Assert.assertTrue(emptyList.isEmpty())
        Assert.assertTrue(c.isClosed)
    }

    @Test
    fun Cursor_should_NOT_be_closed_if_requested() {
        val c: MatrixCursor = MatrixCursor(arrayOf("test_column"))

        c.addRow(arrayOf("v1"))
        c.asFinalList(closeCursor = false) { it.getString(0) }

        Assert.assertFalse(c.isClosed)

        c.asFinalList(closeCursor = true) { it.getString(0) }

        Assert.assertTrue(c.isClosed)
    }
}