package com.abdulkadirkara.emotions.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentHomeBinding
import com.abdulkadirkara.emotions.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeFragmentViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("EGO","HomeFragment-onCreateView")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HomeFragmentViewModel::class.java]

        with(binding) {
            switchHappiness.setOnCheckedChangeListener { _, isChecked ->
                Log.e("EGO","HomeFragment-onViewCreated-switchHappiness $isChecked")
                viewModel.setSwitchHappinessState(isChecked)
            }

            switchKindness.setOnCheckedChangeListener { _, isChecked ->
                Log.e("EGO","HomeFragment-onViewCreated-switchKindness $isChecked")
                viewModel.setSwitchKindnessState(isChecked)
            }

            switchOptimism.setOnCheckedChangeListener { _, isChecked ->
                Log.e("EGO","HomeFragment-onViewCreated-switchOptimism $isChecked")
                viewModel.setSwitchOptimismState(isChecked)
            }

            switchGiving.setOnCheckedChangeListener { _, isChecked ->
                Log.e("EGO","HomeFragment-onViewCreated-switchGiving $isChecked")
                viewModel.setSwitchGivingState(isChecked)
            }

            switchRespect.setOnCheckedChangeListener { _, isChecked ->
                Log.e("EGO","HomeFragment-onViewCreated-switchRespect $isChecked")
                viewModel.setSwitchRespectState(isChecked)
            }

            switchEgo.setOnCheckedChangeListener { _, isChecked ->
                Log.e("EGO","HomeFragment-onViewCreated-switchEgo $isChecked")
                viewModel.setSwitchEgoState(isChecked)

                if (isChecked) {
                    setSwitchStatus(false)
                    setIsCheckedToFalse()
                } else {
                    setSwitchStatus(true)
                }
            }
        }

    }

    private fun setSwitchStatus(status: Boolean) {
        Log.e("EGO","HomeFragment-setSwitchStatus $status")
        with(binding) {
            switchRespect.isEnabled = status
            switchGiving.isEnabled = status
            switchKindness.isEnabled = status
            switchOptimism.isEnabled = status
            switchHappiness.isEnabled = status
        }
    }

    private fun setIsCheckedToFalse() {
        Log.e("EGO","HomeFragment-setIsCheckedToFalse")
        with(binding) {
            switchGiving.isChecked = false
            switchRespect.isChecked = false
            switchKindness.isChecked = false
            switchOptimism.isChecked = false
            switchHappiness.isChecked = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("EGO","HomeFragment-onDestroyView")
        _binding = null
    }
}