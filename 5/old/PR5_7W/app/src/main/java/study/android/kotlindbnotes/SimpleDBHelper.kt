package study.android.kotlindbnotes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class SimpleDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    object  DBContract  {
        // Группируем данные
        object  Entry  : BaseColumns {
            const val TABLE_NAME =  "results"
            const val COLUMN_NAME_NAME =  "name"
            const val COLUMN_NAME_RESULT = "result"
        }
        // Команды для работы
        const val SQL_CREATE =
            "CREATE TABLE ${Entry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${Entry.COLUMN_NAME_NAME} TEXT," +
                    "${Entry.COLUMN_NAME_RESULT} INTEGER)"
        const val SQL_DELETE = "DROP TABLE IF EXISTS ${Entry.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DBContract.SQL_CREATE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DBContract.SQL_DELETE)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "results.db"
    }

    fun insert(result: Result){
        // Открываем базу на запись
        val db = writableDatabase
        // Комплектуем данные для вставки
        val values = ContentValues().apply {
            put(DBContract.Entry.COLUMN_NAME_NAME, result.name)
            put(DBContract.Entry.COLUMN_NAME_RESULT, result.result)
        }
        // вставляем данные в базу данных
        db?.insert(DBContract.Entry.TABLE_NAME, null, values)
    }

     fun getAll(order: String): List<Result> {
        val allRecords = mutableListOf<Result>()
        val cursor = readableDatabase.query(DBContract.Entry.TABLE_NAME, null, null,
            null, null,null, order)
        cursor.moveToFirst()
        while(cursor.moveToNext()){
            allRecords.add(Result(cursor.getString(cursor.getColumnIndex(DBContract.Entry.COLUMN_NAME_NAME)),
                           cursor.getInt(cursor.getColumnIndex(DBContract.Entry.COLUMN_NAME_RESULT))))
        }
        return allRecords;
    }

}