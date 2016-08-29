package com.wnc_21.kandroidextensions.db

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

fun SQLiteDatabase.optQuery(table: String,
                            columns: Array<out String>? = null,
                            selection: String? = null,
                            selectionArgs: Array<out String>? = null,
                            groupBy: String? = null,
                            having: String? = null,
                            orderBy: String? = null,
                            distinct: Boolean? = null,
                            limit: String? = null): Cursor {

    return query(distinct ?: false, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)
}