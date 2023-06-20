package study.android.room2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import study.android.room2.entities.Director
import study.android.room2.entities.School
import study.android.room2.entities.Student
import study.android.room2.entities.Subject
import study.android.room2.entities.relations.StudentSubjectCrossRef

@Database(
    entities = [
        School::class,
        Student::class,
        Director::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract val schoolDao: SchoolDao
    abstract class SchoolDataBase: RoomDatabase() {
        abstract fun resultsDao(): SchoolDao
    }
}