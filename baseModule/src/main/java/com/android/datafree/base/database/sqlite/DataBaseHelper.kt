package com.android.datafree.base.database.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBaseHelper : SQLiteOpenHelper {
    val DATABASE_NAME: String = "user.db"
    val VERSION: Int = 1

    constructor(
        context: Context?,
        dataBaseName: String,
        factory: SQLiteDatabase.CursorFactory?,
        dataBaseVersion: Int,
    ) : super(context, dataBaseName, null, dataBaseVersion)

    constructor(context: Context?) {
        DataBaseHelper(context, DATABASE_NAME, null, VERSION)
    }



    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}