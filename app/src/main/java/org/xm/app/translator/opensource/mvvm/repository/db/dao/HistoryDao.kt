package org.xm.app.translator.opensource.mvvm.repository.db.dao

import androidx.room.*
import org.xm.app.translator.opensource.mvvm.repository.db.entity.History

@Dao
interface HistoryDao {

    @Insert
    fun insertHistory(history: History): Long

    @Delete
    fun deleteHistory(history: History)

    @Update()
    fun updateHistory(history: History)

    @Query("select * from History where srcText = :content and sl = :sl and tl = :tl")
    fun getHistory(content: String, sl: String, tl: String): MutableList<History>

    @Query("select * from History order by time desc")
    fun getAllHistory(): MutableList<History>

    @Query("select * from History where sl = :sl")
    fun getHistoryBySl(sl: String): MutableList<History>

    @Query("select * from History where tl = :tl")
    fun getHistoryByTl(tl: String): MutableList<History>

    @Query("select * from History where sl = :sl and tl = :tl")
    fun getHistoryBySlAndTl(sl: String, tl: String): MutableList<History>

}