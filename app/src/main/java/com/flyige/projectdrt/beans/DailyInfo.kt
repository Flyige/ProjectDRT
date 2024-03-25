package com.flyige.projectdrt.beans

import java.util.Date

/**
 * @author: yige
 * @params:
 * @date: 2024 2024/1/10 11:39
 */
data class DailyInfo(
    var date: String,
) {
    fun checkNull(): Boolean {
        return this.training != null && this.breakfast != null && this.lunch != null && this.supper != null && this.other != null
    }

    lateinit var breakfast: DailyMeals
    lateinit var lunch: DailyMeals
    lateinit var supper: DailyMeals
    lateinit var other: DailyMeals
    lateinit var training: DailyTraining
}
