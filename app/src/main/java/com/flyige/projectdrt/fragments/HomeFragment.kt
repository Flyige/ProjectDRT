package com.flyige.projectdrt.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flyige.projectdrt.R
import com.flyige.projectdrt.database.DailyInfoEntity
import com.flyige.projectdrt.databinding.FragmentHomeBinding
import com.flyige.projectdrt.listener.DailyInfoFragmentListener
import com.flyige.projectdrt.utils.LogUtil
import com.flyige.projectdrt.viewmodels.HomeViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar

typealias ListItemClickListener = (Int) -> Unit

class HomeFragment : Fragment(), DailyInfoFragmentListener {
    private val TAG: String = this.javaClass.name
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: DailyInfoAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        homeAdapter = DailyInfoAdapter(emptyList())
        binding.rlvHomeShowDailyInfos.layoutManager = GridLayoutManager(this.context, 2)
        binding.rlvHomeShowDailyInfos.adapter = homeAdapter
        homeViewModel.dailyInfos.observe(viewLifecycleOwner) { it ->
            if (it != null) {
                homeAdapter.updateData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getDate(): Any? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return LocalDate.now()
        } else {
            val time = Calendar.getInstance().time
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            return simpleDateFormat.format(time)
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.loadData()
        LogUtil.v(TAG,homeAdapter.itemCount.toString())
    }

    override fun onResult(result: String) {
        LogUtil.i(TAG, "数据库操作结果：$result")
        homeViewModel.loadData()
    }

    class DailyInfoAdapter(private var dalyInfosList: List<DailyInfoEntity>) :
        RecyclerView.Adapter<DailyInfoAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val mTvDate: TextView
            val mTvBreakfast: TextView
            val mTvBreakfasetType: TextView
            val mTvLunchType: TextView
            val mTvSupperType: TextView
            val mTvLunch: TextView
            val mTvSupper: TextView
            val mTvOther: TextView
            val mTvOtherType: TextView
            val mTvTrainingContent: TextView
            val mTvTrainingIntensity: TextView

            init {
                mTvDate = view.findViewById(R.id.tv_item_daily_date_value)
                mTvBreakfast = view.findViewById(R.id.tv_item_breakfast)
                mTvBreakfasetType = view.findViewById(R.id.tv_item_breakfast_type)
                mTvLunch = view.findViewById(R.id.tv_item_lunch)
                mTvLunchType = view.findViewById(R.id.tv_item_lunch_type)
                mTvSupper = view.findViewById(R.id.tv_item_supper)
                mTvSupperType = view.findViewById(R.id.tv_item_supper_type)
                mTvOther = view.findViewById(R.id.tv_item_other)
                mTvOtherType = view.findViewById(R.id.tv_item_other_type)
                mTvTrainingContent = view.findViewById(R.id.tv_item_training_content)
                mTvTrainingIntensity = view.findViewById(R.id.tv_item_training_intensity)
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): DailyInfoAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_home_daily_info_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: DailyInfoAdapter.ViewHolder, position: Int) {
            holder.mTvDate.text = dalyInfosList.get(position).date
            holder.mTvBreakfast.text = dalyInfosList.get(position).breakfast
            holder.mTvBreakfasetType.text = dalyInfosList.get(position).breakfastType.toString()
            holder.mTvLunch.text= dalyInfosList.get(position).lunch
            holder.mTvLunchType.text= dalyInfosList.get(position).lunchType.toString()
            holder.mTvSupper.text= dalyInfosList.get(position).supper
            holder.mTvSupperType.text= dalyInfosList.get(position).supperType.toString()
            holder.mTvOther.text= dalyInfosList.get(position).other
            holder.mTvOtherType.text= dalyInfosList.get(position).otherType.toString()
            holder.mTvTrainingContent.text= dalyInfosList.get(position).trainingContent
            holder.mTvTrainingIntensity.text= dalyInfosList.get(position).trainingIntensity.toString()
        }

        override fun getItemCount(): Int {
            return dalyInfosList.size
        }

        fun updateData(newDailyInfosList: List<DailyInfoEntity>) {
            dalyInfosList = newDailyInfosList
            notifyDataSetChanged()
        }
    }
}


