package com.wnc_21.kandroidextensions.db

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.wnc_21.kandroidextensions.testutils.createDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestSQLiteTransaction {

    private lateinit var db: SQLiteOpenHelper

    @Before
    fun runBeforeEachTest() {
        db = InstrumentationRegistry.getTargetContext().createDatabase()
    }

    @After
    fun runAfterEachTest() {
        db.close()
    }

    @Test
    fun Database_should_handle_transaction_opening_and_closing() {
        db.writableDatabase.runTransaction {db ->
            db.execSQL("CREATE TABLE test_table(test_column TEXT PRIMARY KEY)")
        }

        Assert.assertEquals(db.readableDatabase.count("test_table"), 0L)

        db.writableDatabase.runTransaction {db ->
            db.insert("test_table", null, ContentValues().apply { put("test_column", "value1") })
            db.insert("test_table", null, ContentValues().apply { put("test_column", "value2") })
        }

        val values: List<String> = db.readableDatabase.optQuery("test_table").asFinalList {
            it.getString(0)
        }

        Assert.assertEquals(2, values.size)
        Assert.assertTrue(values.contains("value1"))
        Assert.assertTrue(values.contains("value2"))
    }

    @Test(expected = Exception::class)
    fun Database_should_throw_exception_if_error() {
        db.writableDatabase.runTransaction {db ->
            db.execSQL("EXCEPTION SHOULD BE THROWN")
        }
    }
}