package com.flyige.projectdrt.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flyige.projectdrt.beans.DailyInfo
import com.flyige.projectdrt.constants.DATABASE_TABLE

/**
 * @author: yige
 * @params:
 * @date: 2024 2024/3/4 11:50
 */
@Dao
interface DailyInfoDAO {
    @Query("SELECT * FROM $DATABASE_TABLE")
    suspend fun getAll(): List<DailyInfoEntity>

    @Insert
    suspend fun insertAll(vararg dailyInfoEntitys: DailyInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insert(dailyInfoEntity: DailyInfoEntity): Long

    @Delete
    suspend fun deleteByDate(dailyInfoEntity: DailyInfoEntity): Int
}