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
    var breakfast:String =""
    var breakfastType:String =""
    var lunch:String=""
    var lunchType:String=""
    var supper:String=""
    var supperType:String=""
}
