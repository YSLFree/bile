package com.android.datafree.base.database.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBaseHelper : SQLiteOpenHelper {
    companion object {
        val DATABASE_NAME: String = "user.db"
        val VERSION: Int = 1
    }

    constructor(
        context: Context?,
        dataBaseVersion: Int,
    ) : super(context, DATABASE_NAME, null, dataBaseVersion)


    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}