package com.flyige.projectdrt.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.flyige.projectdrt.R
import com.flyige.projectdrt.beans.DailyInfo
import com.flyige.projectdrt.viewmodels.DailyInfoViewModel


/**
 * @author: yige
 * @params:
 * @date: 2024 2024/1/10 14:09
 */
class DailyInfoDialogFragment : DialogFragment() {
    companion object {
        const val TAG: String = "DailyInfoDialogFragment"
    }

    fun newInstance(tittle: String?): DailyInfoDialogFragment? {
        val fragment = DailyInfoDialogFragment()
        val bundle = Bundle()
        bundle.putString("添加今日数据", tittle)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(this).get(DailyInfoViewModel::class.java)
        return inflater.inflate(R.layout.item_daily_info, container, false)
    }

    // TODO: 能不能用 binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val date = view.findViewById<TextView>(R.id.tv_item_daily_date_value)
        val breakfast = view.findViewById<EditText>(R.id.et_item_daily_food_breakfast_intake)
        val breakfastType = view.findViewById<TextView>(R.id.rbg_item_daily_food_breakfast_type)
        val dailyInfo = DailyInfo(date.toString())
        dailyInfo.breakfast= breakfast.text.toString()
        breakfastType
    }

}