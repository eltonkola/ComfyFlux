package com.eltonkola.comfyflux.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {

    companion object {
        private const val DB_NAME = "prompts.db"
    }

    init {
        createDatabase()
    }

    private fun createDatabase() {
        val dbPath = context.getDatabasePath(DB_NAME).path
        if (!checkDatabase(dbPath)) {
            this.readableDatabase // Create an empty database if it does not exist
            try {
                copyDatabase(dbPath)
            } catch (e: IOException) {
                throw Error("Error copying database", e)
            }
        }
    }

    private fun checkDatabase(dbPath: String): Boolean {
        val dbFile = File(dbPath)
        return dbFile.exists() && dbFile.length() > 0
    }

    private fun copyDatabase(dbPath: String) {
        val inputStream: InputStream = context.assets.open(DB_NAME)
        val outputFile = File(dbPath)
        val outputStream: OutputStream = FileOutputStream(outputFile)
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }
        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // No need to create tables here since the database is pre-populated
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Handle database upgrade if needed
    }
}
