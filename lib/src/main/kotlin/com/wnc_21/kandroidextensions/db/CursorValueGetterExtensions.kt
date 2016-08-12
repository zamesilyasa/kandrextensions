package com.wnc_21.kandroidextensions.db
import android.database.Cursor

fun Cursor.getString(name: String): String {
    return getString(this.getColumnIndex(name))
}

fun Cursor.getNullableString(name: String): String? {
    return getString(this.getColumnIndex(name))
}

fun Cursor.getInt(name: String): Int {
    return getInt(this.getColumnIndex(name))
}

fun Cursor.getNullableInt(name: String): Int? {
    val index = this.getColumnIndex(name)
    return if(isNull(index)) null else getInt(index)
}

fun Cursor.getLong(name: String): Long {
    return getLong(this.getColumnIndex(name))
}

fun Cursor.getNullableLong(name: String): Long? {
    val index = this.getColumnIndex(name)
    return if(isNull(index)) null else getLong(index)
}

fun Cursor.getDouble(name: String): Double {
    return getDouble(this.getColumnIndex(name))
}

fun Cursor.getNullableDouble(name: String): Double? {
    val index = this.getColumnIndex(name)
    return if(isNull(index)) null else getDouble(index)
}

fun Cursor.getFloat(name: String): Float {
    return getFloat(this.getColumnIndex(name))
}

fun Cursor.getNullableFloat(name: String): Float? {
    val index = this.getColumnIndex(name)
    return if(isNull(index)) null else getFloat(index)
}

fun Cursor.getBoolean(name: String): Boolean {
    return getInt(this.getColumnIndex(name)) != 0
}

fun Cursor.getNullableBoolean(name: String): Boolean? {
    val index = this.getColumnIndex(name)
    return if(isNull(index)) null else getBoolean(name)
}