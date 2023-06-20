package study.android.kotlindbnotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "results")
data class ResultEntity(
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "result") var result: Int?
)