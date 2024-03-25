package com.flyige.projectdrt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * @author: yige
 * @params:
 * @date: 2024 2024/3/4 14:15
 */
@Database(entities = [DailyInfoEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class DRTDataBase : RoomDatabase() {
    companion object {
        lateinit var database: DRTDataBase
    }

    //dailyInfo 表的抽象获取类
    abstract fun dailyInfoDAO(): DailyInfoDAO
}