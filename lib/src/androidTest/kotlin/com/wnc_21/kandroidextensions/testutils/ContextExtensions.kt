package com.wnc_21.kandroidextensions.testutils

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

fun Context.createDatabase(removeExistent: Boolean = true, databaseName: String = "test.db")
        : SQLiteOpenHelper {

    if (removeExistent) {
        deleteDatabase(databaseName)
    }

    return DatabaseHelper(this, name = databaseName)
}