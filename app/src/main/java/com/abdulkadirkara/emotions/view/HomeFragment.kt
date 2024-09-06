package com.abdulkadirkara.emotions.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentHomeBinding
import com.abdulkadirkara.emotions.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment() {

    private val viewModel = HomeFragmentViewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("EGO","HomeFragment-onCreateView")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        with(binding) {
            switchHappiness.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setSwitchHappinessState(isChecked)
            }

            switchKindness.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setSwitchKindnessState(isChecked)
            }

            switchOptimism.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setSwitchOptimismState(isChecked)
            }

            switchGiving.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setSwitchGivingState(isChecked)
            }

            switchRespect.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setSwitchRespectState(isChecked)
            }

            switchEgo.setOnCheckedChangeListener { _, isChecked ->
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

    private fun observeViewModel() {
        viewModel.isEgoState.observe(viewLifecycleOwner) { isEgoChecked ->
            setSwitchStatus(!isEgoChecked)
            if (isEgoChecked) {
                setIsCheckedToFalse()
            }
        }

        viewModel.isHappinessState.observe(viewLifecycleOwner) { isChecked ->
            binding.switchHappiness.isChecked = isChecked
        }

        viewModel.isOptimismState.observe(viewLifecycleOwner) { isChecked ->
            binding.switchOptimism.isChecked = isChecked
        }

        viewModel.isKindnessState.observe(viewLifecycleOwner) { isChecked ->
            binding.switchKindness.isChecked = isChecked
        }

        viewModel.isGivingState.observe(viewLifecycleOwner) { isChecked ->
            binding.switchGiving.isChecked = isChecked
        }

        viewModel.isRespectState.observe(viewLifecycleOwner) { isChecked ->
            binding.switchRespect.isChecked = isChecked
        }
    }

    private fun setSwitchStatus(status: Boolean) {
        with(binding) {
            switchRespect.isEnabled = status
            switchGiving.isEnabled = status
            switchKindness.isEnabled = status
            switchOptimism.isEnabled = status
            switchHappiness.isEnabled = status
        }
    }

    private fun setIsCheckedToFalse() {
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