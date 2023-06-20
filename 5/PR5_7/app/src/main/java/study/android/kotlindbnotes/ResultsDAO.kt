package study.android.kotlindbnotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ResultsDao {
    @Query("SELECT * FROM results ORDER BY :order")
    fun getAll(order: String): LiveData<List<ResultEntity>>
    @Insert
    fun insert(vararg result: ResultEntity)
    @Delete
    fun delete(result: ResultEntity)
    @Update
    fun update(vararg result: ResultEntity)



    @Query("DELETE FROM results WHERE name LIKE :name")
    fun deleteByName(name: String)

    @Query("SELECT SUM(result) FROM results")
    fun sumCapitalization(): Int

    @Query("SELECT COUNT(result) FROM results WHERE result >= (SELECT AVG(result)\n" +
            "FROM results)")
    fun highCapitalization(): Int

    @Query("SELECT COUNT(name) FROM results WHERE name < :expr")
    fun enCapitalization(expr: String = "А"): Int

    @Query("SELECT name FROM results WHERE result == (SELECT MAX(result) FROM results)")
    fun bestCapitalization(): String

    @Query("SELECT name FROM results WHERE LENGTH(name) == (SELECT MAX(LENGTH(name)) FROM results)")
    fun longestName(): String
}