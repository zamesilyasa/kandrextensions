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
class TestCount {

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
    fun Count_should_reflect_cursor_content() {

        db.writableDatabase.runTransaction {db ->
            db.execSQL("CREATE TABLE test_empty_table (test_column TEXT PRIMARY KEY)")
            db.execSQL("CREATE TABLE test_table (test_column TEXT PRIMARY KEY)")
            db.insert("test_table", null, ContentValues().apply { put("test_column", "val1") })
            db.insert("test_table", null, ContentValues().apply { put("test_column", "val2") })
        }

        Assert.assertEquals(db.readableDatabase.count("test_empty_table"), 0)
        Assert.assertEquals(db.readableDatabase.count("test_table"), 2)
    }
}