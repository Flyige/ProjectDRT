package com.flyige.projectdrt.beans

import java.util.Date
import kotlin.properties.Delegates

/**
 * @author: yige
 * @params:
 * @date: 2024 2024/1/10 11:39
 */
data class DailyInfo(
    var date: String,
) {
    lateinit var beakfast:DailyMeals
    lateinit var lunch:DailyMeals
    lateinit var supper:DailyMeals
    lateinit var other:DailyMeals
    lateinit var training:DailyTraining
}
