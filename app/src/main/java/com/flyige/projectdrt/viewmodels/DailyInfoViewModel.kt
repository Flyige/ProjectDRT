package com.flyige.projectdrt.viewmodels

import android.os.Build
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flyige.projectdrt.beans.DailyInfo
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar

/**
 * 这个 viewmodel 其实是用来 shared 的，
 * @author: yige
 * @params:
 * @date: 2024 2024/1/10 14:05
 */
class DailyInfoViewModel : ViewModel() {
    private val _data = mutableStateListOf<DailyInfo>()
    val dailyInfo:List<DailyInfo> get()=_data
    fun addDailyInfo(dailyInfo: DailyInfo){
        _data.add(dailyInfo)
    }
}