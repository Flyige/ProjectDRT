package com.flyige.projectdrt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flyige.projectdrt.database.DRTDataBase
import com.flyige.projectdrt.utils.LogUtil
import kotlinx.coroutines.launch

/**
 * @author: yige
 * @params:需要完成 1.数据库的读取；2.读取数据的转换封装；3.控制 livedata
 * @date: 2024 2024/3/25 14:26
 */
class MainActivityViewModel : ViewModel() {

    companion object {
        const val TAG: String = "DailyInfoDialogFragmentViewModel"
    }

    fun loadData() {
        viewModelScope.launch {
            val dailyInfoDAO = DRTDataBase.database.dailyInfoDAO()
            val all = dailyInfoDAO.getAll()
            LogUtil.i(TAG, "all: ->>> " + all)
        }
    }
}