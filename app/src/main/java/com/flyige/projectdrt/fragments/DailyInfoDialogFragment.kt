package com.flyige.projectdrt.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.flyige.projectdrt.R
import com.flyige.projectdrt.ui.DailyInfoScreen
import com.flyige.projectdrt.viewmodels.DailyInfoViewModel


/**
 * @author: yige
 * @params:
 * @date: 2024 2024/1/10 14:09
 */
class DailyInfoDialogFragment : DialogFragment() {
    lateinit var dailyInfoViewModel:DailyInfoViewModel
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
        dailyInfoViewModel = ViewModelProvider(this).get(DailyInfoViewModel::class.java)
        return inflater.inflate(R.layout.daily_info_fragment, container, false)
    }
    // TODO: 能不能用 binding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val container: ComposeView = view.findViewById(R.id.compose_view_daily_info_screen)
        container.setContent {
            DailyInfoScreen(dailyInfoViewModel)
        }
    }
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        params?.height = 1200
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}