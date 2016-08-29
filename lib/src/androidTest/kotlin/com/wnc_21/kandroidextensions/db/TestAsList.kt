package com.wnc_21.kandroidextensions.db

import android.database.Cursor
import android.database.MatrixCursor
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestAsList {

    private val cursor: MatrixCursor = MatrixCursor(arrayOf("first", "second"))
    private lateinit var list: List<Entity>

    @Before
    fun runBeforeEachTest() {
        cursor.addRow(arrayOf(1, "first"))
        cursor.addRow(arrayOf(2, "second"))
        cursor.addRow(arrayOf(3, "third"))

        list = listOf(Entity(1, "first"), Entity(2, "second"), Entity(3, "third"))
    }

    @After
    fun runAfterEachTest() {
        cursor.close()
    }

    @Test
    fun Cursor_should_be_wrapped_with_list() {
        val cursorAsList: List<Entity> = cursor.asList(::entityMapper)

        assertEquals(cursor.count, list.size)
        assertTrue(list.containsAll(cursorAsList))
    }

    @Test
    fun Cursor_should_be_converted_to_emptylist_in_case_of_size_eq_0() {
        val cursor: Cursor = MatrixCursor(arrayOf("id"))

        val emptyList: List<Any> = cursor.asList { fail("Should not be called, because of empty cursor") }

        assertEquals(0, emptyList.size)
    }

    @Test
    fun Test_cursor_contains() {
        val list = cursor.asList(::entityMapper)

        assertTrue(list.contains(Entity(1, "first")))
        assertFalse(list.contains(Entity(Int.MAX_VALUE, "max")))
    }

    @Test
    fun Test_cursor_containsAll() {
        val contains: List<Entity> = listOf(Entity(3, "third"), Entity(1, "first"))
        val notAll: List<Entity> = listOf(Entity(Integer.MAX_VALUE, "max"), Entity(1, "first"))
        val noneOfAll: List<Entity> = listOf(Entity(-1, "none"), Entity(-2, "off"), Entity(-3, "all"))
        val empty: List<Entity> = listOf()

        val list: List<Entity> = cursor.asList(::entityMapper)

        assertTrue(list.containsAll(contains))
        assertFalse(list.containsAll(notAll))
        assertFalse(list.containsAll(noneOfAll))
        assertTrue(list.containsAll(empty))
    }

    @Test
    fun Test_list_iteration() {
        val list: List<Entity> = cursor.asList(::entityMapper)
        val iterator = list.iterator()

        var counter: Int = 0
        while (iterator.hasNext()) {
            val entity: Entity = list[counter]

            assertEquals(entity, iterator.next())

            counter++
        }

        assertEquals(list.size, counter)
    }

    @Test
    fun Test_index_of_operations() {
        val cursor = MatrixCursor(arrayOf("_1"))

        cursor.use {
            cursor.addRow(arrayOf("0"))
            cursor.addRow(arrayOf("1"))
            cursor.addRow(arrayOf("1"))
            cursor.addRow(arrayOf("0"))

            val list: List<String> = cursor.asList { it.getString(0) }

            assertEquals(0, list.indexOf("0"))
            assertEquals(1, list.indexOf("1"))
            assertEquals(2, list.lastIndexOf("1"))
            assertEquals(3, list.lastIndexOf("0"))
        }
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun List_should_raise_index_of_bound_exception() {
        val c: MatrixCursor = MatrixCursor(arrayOf("_"))

        val list:List<String> = c.asList { c.getString(0) }

        list[0]
    }

    @Test
    fun Cursor_should_be_closed_after_list_close_method_call() {
        val cursor = MatrixCursor(arrayOf("_1"))
        cursor.addRow(arrayOf("first"))

        assertTrue(cursor.moveToFirst())

        val list = cursor.asList{it.getString(0)}

        assertFalse(cursor.isClosed)

        list.close()

        assertTrue(cursor.isClosed)
    }
}

internal data class Entity(val val1: Int, val val2: String)
internal fun entityMapper(it: Cursor) = Entity(it.getInt("first"), it.getString("second"))