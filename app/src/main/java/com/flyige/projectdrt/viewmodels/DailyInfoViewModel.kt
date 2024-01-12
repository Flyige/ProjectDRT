package com.flyige.projectdrt.viewmodels

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    // TODO: 这里该如何保存数据？
    private val _date= MutableLiveData<String>().apply {
        value= getDate().toString()
    }
    fun getDate(): Any? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalDate.now()
        }else {
            val time = Calendar.getInstance().time
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            return simpleDateFormat.format(time)
        }
    }
}