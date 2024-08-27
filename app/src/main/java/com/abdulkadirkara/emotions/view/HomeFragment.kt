package com.abdulkadirkara.emotions.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        Log.e("EGO","homefragment-onCreateView sonu")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("EGO","homefragment-onViewCreated bası")
        setupSwitches()
        Log.e("EGO","homefragment-onViewCreated setupSwitched cagrım sonu")
        observeViewModel()
        Log.e("EGO","homefragment-onViewCreated observeViewModel cagrım sonu")
        setupSwitchListeners()
        Log.e("EGO","homefragment-onViewCreated setupSwitchListeners cagrım sonu")

        // BottomNavigationView'i güncelle
        viewModel.navigationItems.value?.let { updateBottomNavigationView(it) }
        Log.e("EGO","homefragment-onViewCreated updateBottomNavigationView cagrım sonu")

    }
    override fun onResume() {
        super.onResume()
        Log.e("EGO","homefragment-onResume bası")
        // Switch ve BottomNavigationView durumlarını yeniden yükle
        viewModel.navigationItems.value?.let { updateBottomNavigationView(it) }
        Log.e("EGO","homefragment-onResume updateBottomNavigationView cagrım sonu")
    }
    private fun setupSwitches() {
        // Switchleri ViewBinding ile bul
        Log.e("EGO","homefragment-setupSwitches bası")
        with(binding) {
            switchEgo.isChecked = viewModel.isEgoChecked.value ?: false
            switchHappiness.isChecked = false
            switchOptimism.isChecked = false
            switchKindness.isChecked = false
            switchGiving.isChecked = false
            switchRespect.isChecked = false
        }
        Log.e("EGO","homefragment-setupSwitches sonu")
    }

    private fun observeViewModel() {
        Log.e("EGO","homefragment-observeViewModel bası")
        viewModel.isEgoChecked.observe(viewLifecycleOwner, Observer { isChecked ->
            Log.e("EGO","homefragment-observeViewModel isEgoChecked")
            binding.switchEgo.isChecked = isChecked
            Log.e("EGO","homefragment-observeViewModel isEgoChecked switchEgo.isChecked")
            updateBottomNavigationView()
            Log.e("EGO","homefragment-observeViewModel isEgoChecked updateBottomNavigationView")

        })

        viewModel.areSwitchesClickable.observe(viewLifecycleOwner, Observer { isClickable ->
            setSwitchesClickable(isClickable)
            Log.e("EGO","homefragment-observeViewModel areSwitchesClickable")
        })

        viewModel.areSwitchesChecked.observe(viewLifecycleOwner, Observer { areChecked ->
            Log.e("EGO","homefragment-observeViewModel areSwitchesChecked bası")
            setSwitchesChecked(areChecked)
            Log.e("EGO","homefragment-observeViewModel areSwitchesChecked sonu")
        })
        viewModel.navigationItems.observe(viewLifecycleOwner, Observer { items ->
            Log.e("EGO","homefragment-observeViewModel navigationItems bası")
            updateBottomNavigationView(items)
            Log.e("EGO","homefragment-observeViewModel navigationItems sonu")
        })

        // Maksimum öğe sayısına ulaşıldığında mesaj göster
        viewModel.maxItemsReachedMessage.observe(viewLifecycleOwner, { isMaxReached ->
            Log.e("EGO","homefragment-observeViewModel maxItemsReachedMessage bası")
            if (isMaxReached) {
                Toast.makeText(requireActivity(), "Maksimum 5 item ekleyebilirsiniz.", Toast.LENGTH_SHORT).show()
                viewModel.resetMaxItemsReachedMessage() // Mesajı sıfırla
                Log.e("EGO","homefragment-observeViewModel maxItemsReachedMessage reset")
            }
            Log.e("EGO","homefragment-observeViewModel maxItemsReachedMessage sonu")
        })
    }

    private fun setupSwitchListeners() {
        Log.e("EGO","homefragment-setupSwitchListeners bası")
        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
            Log.e("EGO","homefragment-setupSwitchListeners switchEgo onEgoSwitchChanged")
        }
        binding.switchHappiness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchChanged(R.id.happinessFragment, isChecked)
            Log.e("EGO","homefragment-setupSwitchListeners switchHappiness onOtherSwitchChanged")
        }
        binding.switchOptimism.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchChanged(R.id.optimismFragment, isChecked)
            Log.e("EGO","homefragment-setupSwitchListeners switchOptimism onOtherSwitchChanged")
        }
        binding.switchKindness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchChanged(R.id.kindnessFragment, isChecked)
            Log.e("EGO","homefragment-setupSwitchListeners switchKindness onOtherSwitchChanged")
        }
        binding.switchGiving.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchChanged(R.id.givingFragment, isChecked)
            Log.e("EGO","homefragment-setupSwitchListeners switchGiving onOtherSwitchChanged")
        }
        binding.switchRespect.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchChanged(R.id.respectFragment, isChecked)
            Log.e("EGO","homefragment-setupSwitchListeners switchRespect onOtherSwitchChanged")
        }
    }

    private fun setSwitchesClickable(isClickable: Boolean) {
        Log.e("EGO","homefragment-setSwitchesClickable bası")
        with(binding) {
            switchHappiness.isClickable = isClickable
            switchOptimism.isClickable = isClickable
            switchKindness.isClickable = isClickable
            switchGiving.isClickable = isClickable
            switchRespect.isClickable = isClickable
        }
        Log.e("EGO","homefragment-setSwitchesClickable sonu")
    }

    private fun setSwitchesChecked(isChecked: Boolean) {
        Log.e("EGO","homefragment-setSwitchesChecked bası")
        with(binding) {
            switchHappiness.isChecked = isChecked
            switchOptimism.isChecked = isChecked
            switchKindness.isChecked = isChecked
            switchGiving.isChecked = isChecked
            switchRespect.isChecked = isChecked
        }
        Log.e("EGO","homefragment-setSwitchesChecked sonu")
    }
    private fun updateBottomNavigationView(items: List<Int> = emptyList()) {
        Log.e("EGO","homefragment-updateBottomNavigationView bası")
        val mainActivity = activity as? MainActivity
        mainActivity?.toggleBottomNavigationView(items)
        Log.e("EGO","homefragment-updateBottomNavigationView sonu")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}