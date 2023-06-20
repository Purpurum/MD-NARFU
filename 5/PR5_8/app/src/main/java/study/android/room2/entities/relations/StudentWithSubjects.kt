package study.android.room2.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import study.android.room2.entities.Student
import study.android.room2.entities.Subject

data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<Subject>
)