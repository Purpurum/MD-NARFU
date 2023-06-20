package study.android.room2.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import study.android.room2.entities.Student
import study.android.room2.entities.Subject

data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: List<Student>
)