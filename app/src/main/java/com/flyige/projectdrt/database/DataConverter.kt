package com.flyige.projectdrt.database

import androidx.room.TypeConverter
import com.flyige.projectdrt.beans.DailyMeals
import java.util.Date

/**
 * @author: yige
 * @params:
 * @date: 2024 2024/3/4 15:30
 */
class DataConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }
    @TypeConverter
    fun toTimestamp(date:Date?):Long?{
        return date?.time
    }
}