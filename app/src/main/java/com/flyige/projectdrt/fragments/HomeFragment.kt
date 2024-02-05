package com.flyige.projectdrt.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flyige.projectdrt.R
import com.flyige.projectdrt.databinding.FragmentHomeBinding
import com.flyige.projectdrt.viewmodels.HomeViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

    override fun onResume() {
        super.onResume()
//        binding.includedItemDailyInfo.tvItemDailyDateValue.text=getDate().toString()
    }
}