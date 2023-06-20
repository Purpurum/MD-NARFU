package study.android.room2.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import study.android.room2.entities.Director
import study.android.room2.entities.School

data class   SchoolAndDirector(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val director: Director
)