package study.android.room2.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import study.android.room2.entities.School
import study.android.room2.entities.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val students: List<Student>
)