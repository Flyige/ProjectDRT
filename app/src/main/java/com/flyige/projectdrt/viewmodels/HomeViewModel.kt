package com.flyige.projectdrt.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flyige.projectdrt.database.DRTDataBase
import com.flyige.projectdrt.database.DailyInfoEntity
import com.flyige.projectdrt.utils.LogUtil
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _dailyInfos=MutableLiveData<List<DailyInfoEntity>>()
    val dailyInfos : LiveData<List<DailyInfoEntity>> get()=_dailyInfos
//    val dailyInfos: MutableLiveData<List<DailyInfo>> = _daliInfos
    companion object {
        var TAG: String = this.javaClass.name.toString()
    }
    init {
        loadData()
    }
    fun loadData() {
        viewModelScope.launch {
            try {
                _dailyInfos.value=DRTDataBase.database.dailyInfoDAO().getAll()
                LogUtil.i(HomeViewModel.TAG, "all: ->>> " + _dailyInfos.value)
            }catch (e:Exception){
                LogUtil.e(TAG,"Error loading data:${e.message}")
            }
        }
    }
}