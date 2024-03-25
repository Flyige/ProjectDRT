package com.flyige.projectdrt.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flyige.projectdrt.beans.DailyInfo
import com.flyige.projectdrt.database.DRTDataBase
import com.flyige.projectdrt.database.DailyInfoEntity
import com.flyige.projectdrt.utils.LogUtil
import kotlinx.coroutines.launch

/**
 * 这个 viewmodel 其实是用来 shared 的，
 * @author: yige
 * @params:
 * @date: 2024 2024/1/10 14:05
 */
class DailyInfoViewModel : ViewModel() {
    private val TAG = this.javaClass.name
    private val _data = mutableStateListOf<DailyInfo>()
    private val _saveResult = MutableLiveData<Long>()
    val saveResult: LiveData<Long> = _saveResult
    val dailyInfo: List<DailyInfo> get() = _data
    fun addDailyInfo(dailyInfo: DailyInfo) {
        if (dailyInfo.checkNull())
            _data.add(dailyInfo)
        else
            LogUtil.e(TAG, "null info")
    }

    fun saveDailyInfoToDatabase(dailyInfo: DailyInfo) {
        viewModelScope.launch { //kotlin携程
            var dailyInfoEntity = DailyInfoEntity(
                1,
                date = dailyInfo.date,
                breakfast = dailyInfo.breakfast.meal,
                breakfastType = dailyInfo.breakfast.mealType,
                lunch = dailyInfo.lunch.meal,
                lunchType = dailyInfo.lunch.mealType,
                supper = dailyInfo.supper.meal,
                supperType = dailyInfo.supper.mealType,
                other = dailyInfo.other.meal,
                otherType = dailyInfo.other.mealType,
                trainingContent = dailyInfo.training.trainingContent,
                trainingIntensity = dailyInfo.training.trainingIntensity
            )
            val dailyInfoDAO = DRTDataBase.database.dailyInfoDAO()
            val insertRes = dailyInfoDAO.insert(dailyInfoEntity)
            _saveResult.postValue(insertRes)
            if (insertRes == -1L) {
                LogUtil.e(TAG, "数据库插入失败")
            } else {
                // TODO: 这里添加响应函数，告诉 ui 线程数据库线程操作完成关闭 ui
                LogUtil.i(TAG, "数据库插入成功，插入行: $insertRes")
            }
        }
    }
}