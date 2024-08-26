package com.abdulkadirkara.emotions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentHomeBinding
import com.abdulkadirkara.emotions.viewmodel.HomeFragmentVİewModel

class HomeFragment : Fragment() {

    private val viewModel = HomeFragmentVİewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSwitches()
        observeViewModel()
        setupSwitchListeners()

    }
    private fun setupSwitches() {
        // Switchleri ViewBinding ile bul
        with(binding) {
            switchEgo.isChecked = viewModel.isEgoChecked.value ?: false
            switchHappiness.isChecked = false
            switchOptimism.isChecked = false
            switchKindness.isChecked = false
            switchGiving.isChecked = false
            switchRespect.isChecked = false
        }
    }

    private fun observeViewModel() {
        viewModel.isEgoChecked.observe(viewLifecycleOwner, Observer { isChecked ->
            binding.switchEgo.isChecked = isChecked
        })

        viewModel.areSwitchesClickable.observe(viewLifecycleOwner, Observer { isClickable ->
            setSwitchesClickable(isClickable)
        })

        viewModel.areSwitchesChecked.observe(viewLifecycleOwner, Observer { areChecked ->
            setSwitchesChecked(areChecked)
        })
    }

    private fun setupSwitchListeners() {
        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
        }
    }

    private fun setSwitchesClickable(isClickable: Boolean) {
        with(binding) {
            switchHappiness.isClickable = isClickable
            switchOptimism.isClickable = isClickable
            switchKindness.isClickable = isClickable
            switchGiving.isClickable = isClickable
            switchRespect.isClickable = isClickable
        }
    }

    private fun setSwitchesChecked(isChecked: Boolean) {
        with(binding) {
            switchHappiness.isChecked = isChecked
            switchOptimism.isChecked = isChecked
            switchKindness.isChecked = isChecked
            switchGiving.isChecked = isChecked
            switchRespect.isChecked = isChecked
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}