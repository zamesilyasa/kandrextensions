package com.wnc_21.kandroidextensions.db

import android.database.MatrixCursor
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestCursorValues {
    val ROW_WITH_NULLS: Array<Any?> = arrayOf("string 1", null, 11, null, 111, null, 1.1, null, 11.33, null)

    val cursor: MatrixCursor = MatrixCursor(arrayOf(
            "c_string", "c_nullableString",
            "c_int", "c_nullableInt",
            "c_long", "c_nullableLong",
            "c_float", "c_nullableFloat",
            "c_double", "c_nullableDouble"))

    @Before
    fun runBeforeEachTest() {
        cursor.addRow(ROW_WITH_NULLS)
    }

    @After
    fun runAfterEachTest() {
        cursor.close()
    }

    @Test
    fun testGetString() {
        cursor.moveToFirst()

        assertEquals("string 1", cursor.getString("c_string"))
        assertEquals(null, cursor.getNullableString("c_nullableString"))
    }

    @Test
    fun testGetInt() {
        cursor.moveToFirst()

        assertEquals(11, cursor.getInt("c_int"))
        assertEquals(null, cursor.getNullableInt("c_nullableInt"))
    }

    @Test
    fun testGetLong() {
        cursor.moveToFirst()

        assertEquals(111, cursor.getLong("c_long"))
        assertEquals(null, cursor.getNullableLong("c_nullableLong"))
    }

    @Test
    fun testGetFloat() {
        cursor.moveToFirst()

        assertEquals(1.1F, cursor.getFloat("c_float"))
        assertEquals(null, cursor.getNullableFloat("c_nullableFloat"))
    }

    @Test
    fun testGetDouble() {
        cursor.moveToFirst()

        assertEquals(11.33, cursor.getDouble("c_double"), 0.0)
        assertEquals(null, cursor.getNullableDouble("c_nullableDouble"))
    }

    @Test
    fun testGetBooleanShouldReturnTrueIfPositive() {
        val cursor = MatrixCursor(arrayOf("boolean"))
        cursor.use {
            cursor.addRow(arrayOf(1))

            cursor.moveToFirst()

            assertTrue(cursor.getBoolean("boolean"))
        }
    }

    @Test
    fun testGetBooleanShouldReturnFalseIfZero() {
        val cursor = MatrixCursor(arrayOf("boolean"))

        cursor.use {
            cursor.addRow(arrayOf(0))

            cursor.moveToFirst()

            assertFalse(cursor.getBoolean("boolean"))
        }
    }

    @Test
    fun testGetBooleanShouldReturnFalseIfNull() {
        val cursor = MatrixCursor(arrayOf("boolean"))

        cursor.use {
            cursor.addRow(arrayOfNulls(1))

            cursor.moveToFirst()

            assertFalse(cursor.getBoolean("boolean"))
            assertEquals(null, cursor.getNullableBoolean("boolean"))
        }
    }
}