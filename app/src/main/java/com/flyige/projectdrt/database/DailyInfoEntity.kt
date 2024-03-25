package com.flyige.projectdrt.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.flyige.projectdrt.constants.DATABASE_TABLE
import java.util.Date

/**
 * @author: yige
 * @params:参照 DailyInfo
 * @date: 2024 2024/3/4 11:24
 */
@Entity(tableName = DATABASE_TABLE)
data class DailyInfoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "date") var date: String? = null,
    @ColumnInfo(name = "breakfast") var breakfast: String? = "",
    @ColumnInfo(name = "breakfastType") var breakfastType: Int? = -1,
    @ColumnInfo(name = "lunch") var lunch: String? = "",
    @ColumnInfo(name = "lunchType") var lunchType: Int? = -1,
    @ColumnInfo(name = "supper") var supper: String? = "",
    @ColumnInfo(name = "supperType") var supperType: Int? = -1,
    @ColumnInfo(name = "other") var other: String? = "",
    @ColumnInfo(name = "otherType") var otherType: Int? = -1,
    @ColumnInfo(name = "trainingContent") var trainingContent: String? = "",
    @ColumnInfo(name = "trainingIntensity") var trainingIntensity: Int? = -1
)
