package com.flyige.projectdrt.fragments

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.flyige.projectdrt.R
import com.flyige.projectdrt.listener.DailyInfoFragmentListener
import com.flyige.projectdrt.ui.DailyInfoScreen
import com.flyige.projectdrt.utils.LogUtil
import com.flyige.projectdrt.viewmodels.DailyInfoViewModel


/**
 * @author: yige
 * @params:
 * @date: 2024 2024/1/10 14:09
 */
class DailyInfoDialogFragment : DialogFragment() {
    lateinit var dailyInfoViewModel: DailyInfoViewModel
    var listener: DailyInfoFragmentListener? = null

    companion object {
        const val TAG: String = "DailyInfoDialogFragment"
    }

    fun newInstance(title: String?): DailyInfoDialogFragment? {
        val fragment = DailyInfoDialogFragment()
        val bundle = Bundle()
        bundle.putString("添加今日数据", title)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DailyInfoFragmentListener) {
            listener = context
        } else {
            throw ClassCastException("Hosting Activity must implement FragmentResultListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dailyInfoViewModel =
            ViewModelProvider(this).get(DailyInfoViewModel::class.java)
        return inflater.inflate(R.layout.daily_info_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val container: ComposeView = view.findViewById(R.id.compose_view_daily_info_screen)
        container.setContent {
            // 创建一个状态来控制对话框是否应该关闭
            val shouldDismiss = remember { mutableStateOf(false) }
            // 如果状态为 true，则触发 dismiss
            if (shouldDismiss.value) {
                LaunchedEffect(Unit) {
                    returnResult("finish")
                    dismiss()
                }
            }
            // 传递一个 lambda 给 DailyInfoScreen，当需要关闭对话框时，更新状态
            DailyInfoScreen(context, dailyInfoViewModel) {
                LogUtil.d(TAG, "获得数据库操作反馈")
                shouldDismiss.value = true  // 这将触发上面的 if 判断和 LaunchedEffect
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        params?.height = 1200
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    fun returnResult(result: String) {
        listener?.onResult(result)
    }

    override fun onDestroy() {
        super.onDestroy()
        returnResult("canceled")
    }
}