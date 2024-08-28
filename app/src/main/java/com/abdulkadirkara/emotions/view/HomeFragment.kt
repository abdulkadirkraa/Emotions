package com.abdulkadirkara.emotions.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentHomeBinding
import com.abdulkadirkara.emotions.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment() {
    // View'u saklayacağımız bir alan tanımlıyoruz
    private var rootView: View? = null

    private val viewModel = HomeFragmentViewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Eğer rootView null ise, yeni bir View şişiriyoruz ve binding'i başlatıyoruz
        if (rootView == null) {
            rootView = binding.root

            // Burada diğer başlatma işlemleri yapılabilir
        }
        // rootView'u geri döndürüyoruz
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSwitches()
        observeViewModel()
        setupSwitchListeners()

        // BottomNavigationView'i güncelle
        viewModel.navigationItems.value?.let { updateBottomNavigationView(it) }
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

    private fun onEgoCheckedChanged(isChecked: Boolean){
        binding.switchEgo.isChecked = isChecked
        updateBottomNavigationView()
        if (rootView != null && isChecked){
            setSwitchesChecked(false)
            setSwitchesClickable(false)
            observeisEgoChecked()
            observeAreSwitchesChecked()
            observeAreSwitchesClickable()
        }
    }
    private fun onSwitchesClikcableChanged(isClickable: Boolean){
        setSwitchesClickable(isClickable)
    }
    private fun onSwitchesCheckedChanged(isChecked: Boolean){
        setSwitchesChecked(isChecked)
    }
    private fun onNavigationItemsChanged(items: List<Int>){
        updateBottomNavigationView(items)
    }
    private fun onMaxItemsReachedChanged(isMaxReached: Boolean) {
        if (isMaxReached) {
            Toast.makeText(requireActivity(), "Maksimum 5 item ekleyebilirsiniz.", Toast.LENGTH_SHORT).show()
            viewModel.resetMaxItemsReachedMessage() // Mesajı sıfırla
        }
    }

    private fun observeisEgoChecked() {
        viewModel.isEgoChecked.observe(viewLifecycleOwner,::onEgoCheckedChanged)
    }
    private fun observeAreSwitchesClickable() {
        viewModel.areSwitchesClickable.observe(viewLifecycleOwner, ::onSwitchesClikcableChanged)
    }
    private fun observeAreSwitchesChecked() {
        viewModel.areSwitchesChecked.observe(viewLifecycleOwner, ::onSwitchesCheckedChanged)
    }
    private fun observeNavigationItems() {
        viewModel.navigationItems.observe(viewLifecycleOwner, ::onNavigationItemsChanged)
    }
    private fun observeMaxItemsReachedMessage() {
        viewModel.maxItemsReachedMessage.observe(viewLifecycleOwner, ::onMaxItemsReachedChanged)
    }

    private fun observeViewModel() {
        observeisEgoChecked()
        observeAreSwitchesClickable()
        observeAreSwitchesChecked()
        observeNavigationItems()
        observeMaxItemsReachedMessage()
    }

    private fun setupSwitchEgoListener() {
        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
            // switchEgo değiştiğinde diğer switch'lerin durumunu güncelle
            with(binding) {
                val switches = listOf(switchHappiness, switchOptimism, switchKindness, switchGiving, switchRespect)
                switches.forEach { switch ->
                    if (isChecked) {
                        switch.isChecked = false
                        switch.isClickable = false
                        viewModel.onOtherSwitchChanged(switch.id, false)
                        setSwitchesChecked(false)
                        setSwitchesClickable(false)
                        observeisEgoChecked()
                        observeAreSwitchesChecked()
                        observeAreSwitchesClickable()

                        updateBottomNavigationView()
                    } else {
                        if (!switch.isChecked) {
                            viewModel.onOtherSwitchChanged(switch.id, false)
                        }
                    }
                }
            }
        }
    }
    private fun setupSwitchHappinessListener() {
        binding.switchHappiness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchChanged(R.id.happinessFragment, isChecked)
        }
    }
    private fun setupSwitchOptimismListener() {
        binding.switchOptimism.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchChanged(R.id.optimismFragment, isChecked)
        }
    }
    private fun setupSwitchKindnessListener(){
        binding.switchKindness.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchChanged(R.id.kindnessFragment, isChecked)
        }
    }
    private fun setupSwitchGivingListener(){
        binding.switchGiving.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchChanged(R.id.givingFragment, isChecked)
        }
    }
    private fun setupSwitchRespectListener(){
        binding.switchRespect.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onOtherSwitchChanged(R.id.respectFragment, isChecked)
        }
    }

    private fun setupSwitchListeners() {
        setupSwitchEgoListener()
        setupSwitchHappinessListener()
        setupSwitchOptimismListener()
        setupSwitchKindnessListener()
        setupSwitchGivingListener()
        setupSwitchRespectListener()
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
    private fun updateBottomNavigationView(items: List<Int> = emptyList()) {
        val mainActivity = activity as? MainActivity
        mainActivity?.toggleBottomNavigationView(items)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}