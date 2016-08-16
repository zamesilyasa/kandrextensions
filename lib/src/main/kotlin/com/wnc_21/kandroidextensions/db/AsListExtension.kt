package com.wnc_21.kandroidextensions.db

import android.database.Cursor
import java.io.Closeable

fun <T> Cursor.asList(mapper: (c: Cursor) -> T): CloseableList<T> {

    this@asList.moveToFirst()
    return object : CloseableList<T>() {

        override fun close() {
            this@asList.close()
        }

        override val size: Int
            get() = count

        override fun contains(element: T): Boolean {
            for(item in this) {
                if (element == item) {
                    return true
                }
            }

            return false
        }

        override fun containsAll(elements: Collection<T>): Boolean {
            val list: MutableList<T> = mutableListOf()
            list.addAll(this)

            return list.containsAll(elements)
        }

        override fun get(index: Int): T {
            this@asList.moveToPosition(index)
            return mapper.invoke(this@asList)
        }

        override fun indexOf(element: T): Int {
            return indexOfFirst { it == element }
        }

        override fun isEmpty(): Boolean {
            return count == 0
        }

        override fun iterator(): Iterator<T> {
            return object : Iterator<T> {
                var position = 0

                override fun next(): T {
                    this@asList.moveToPosition(position++)
                    return mapper.invoke(this@asList)
                }

                override fun hasNext(): Boolean {
                    return position < (count)
                }
            }
        }

        override fun lastIndexOf(element: T): Int {
            return indexOfLast { it == element }
        }

        override fun listIterator(): ListIterator<T> {
            return listIterator(-1)
        }

        override fun listIterator(index: Int): ListIterator<T> {
            var position = index

            return object : ListIterator<T> {
                override fun hasNext(): Boolean {
                    return position < count
                }

                override fun hasPrevious(): Boolean {
                    return position > 0 && position <= this@asList.count
                }

                override fun next(): T {
                    this@asList.moveToPosition(++position)
                    return mapper.invoke(this@asList)
                }

                override fun nextIndex(): Int {
                    return position
                }

                override fun previous(): T {
                    this@asList.moveToPosition(--position)
                    return mapper.invoke(this@asList)
                }

                override fun previousIndex(): Int {
                    return position - 2
                }
            }
        }

        override fun subList(fromIndex: Int, toIndex: Int): List<T> {
            val list: MutableList<T> = mutableListOf()

            moveToPosition(fromIndex)
            while (position <= toIndex) {
                list.add(mapper.invoke(this@asList))
            }

            return list.toList()
        }
    }
}

abstract class CloseableList<out T>: List<T>, Closeable