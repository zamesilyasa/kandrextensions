package com.wnc_21.kandroidextensions.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestOptQuery {

    private lateinit var helper: SQLiteOpenHelper
    private lateinit var context: Context


    @Before
    fun runBeforeEachTest() {
        context = InstrumentationRegistry.getTargetContext()
        helper = DatabaseHelper(context)

        addUsersTable(helper.writableDatabase)
    }

    @After
    fun runAfterEachTest() {
        helper.readableDatabase.close()
        context.deleteDatabase("test.db")
    }

    @Test
    fun testSelectAll() {
        helper.readableDatabase.optQuery("Users").use {
            assertEquals(3, it.count)
        }
    }

    @Test
    fun testSelectColumns() {
        helper.readableDatabase.optQuery("Users", columns = arrayOf("name")).use {
            assertEquals(1, it.columnCount)
            assertEquals(3, it.count)
        }
    }

    @Test
    fun testSelectWithArgs() {
        helper.readableDatabase.optQuery("Users", selection = "name=?", selectionArgs = arrayOf("user 1")).use {
            assertEquals(1, it.count)

            it.moveToFirst()
            assertEquals("user 1", it.getString("name"))
        }
    }

    @Test
    fun testOrderBy() {
        helper.readableDatabase.optQuery("Users", orderBy = "_id DESC").use {
            assertEquals(3, it.count)

            it.moveToPosition(0)
            assertEquals("3", it.getString(0))

            it.moveToLast()
            assertEquals("1", it.getString(0))
        }
    }

    @Test
    fun testDistinct() {
        helper.readableDatabase.optQuery("Users", columns = arrayOf("same"), distinct = true ).use {
            assertEquals(1, it.count)
        }

        helper.readableDatabase.optQuery("Users", columns = arrayOf("same"), distinct = false ).use {
            assertEquals(3, it.count)
        }

        helper.readableDatabase.optQuery("Users", columns = arrayOf("same")).use {
            assertEquals(3, it.count)
        }
    }

    @Test
    fun testGroupBy() {

        helper.readableDatabase.optQuery("Users", groupBy = "same").use {
            assertEquals(1, it.count)
        }
    }

    @Test
    fun testHaving() {

        helper.readableDatabase.optQuery("Users", groupBy = "same", having = "count(name) = 1").use {
            assertEquals(0, it.count)
        }
    }

    private fun addUsersTable(db: SQLiteDatabase) {

        try {
            db.beginTransaction()

            db.execSQL("CREATE TABLE Users(_id INTEGER PRIMARY KEY, name TEXT NOT NULL, same TEXT NOT NULL)")

            db.insert("Users", null, ContentValues().apply {
                put("_id", 1)
                put("name", "user 1")
                put("same", "same")
            })

            db.insert("Users", null, ContentValues().apply {
                put("_id", 2)
                put("name", "user 2")
                put("same", "same")
            })

            db.insert("Users", null, ContentValues().apply {
                put("_id", 3)
                put("name", "user 3")
                put("same", "same")
            })

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }
}

private class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "test.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        throw UnsupportedOperationException("not implemented")
    }

}
