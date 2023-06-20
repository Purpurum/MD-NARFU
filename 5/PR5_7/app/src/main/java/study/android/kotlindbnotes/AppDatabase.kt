package study.android.kotlindbnotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ResultEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultsDao(): ResultsDao

    companion object{
        @Volatile
        private var database:AppDatabase?=null

        @Synchronized
        fun getInstance(context: Context):AppDatabase{
            return if (database==null){
                database = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database"
                ).build()
                database as AppDatabase
            } else database as AppDatabase
        }
    }
}