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
    // View'u saklayacağımız bir alan tanımlıyoruz
    private var rootView: View? = null

    private val viewModel = HomeFragmentViewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("EGO","HomeFragment-onCreateView")
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

        Log.e("EGO","HomeFragment-onViewCreated rootView: ${rootView.toString()}")
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
            Log.e("EGO","HomeFragment-setupSwitches switchEgo: ${switchEgo.isChecked}")
            Log.e("EGO","HomeFragment-setupSwitches switchRespect: ${switchRespect.isChecked}")
        }
    }

    private fun onEgoCheckedChanged(isChecked: Boolean){
        binding.switchEgo.isChecked = isChecked
        Log.e("EGO","HomeFragment-onEgoCheckedChanged isChecked: $isChecked")
        updateBottomNavigationView()
    }
    private fun onMaxItemsReachedChanged(isMaxReached: Boolean) {
        if (isMaxReached) {
            Toast.makeText(requireActivity(), "Maksimum 5 item ekleyebilirsiniz.", Toast.LENGTH_SHORT).show()
            viewModel.resetMaxItemsReachedMessage() // Mesajı sıfırla
        }
    }

    private fun observeisEgoChecked() {
        Log.e("EGO","HomeFragment-observeisEgoChecked")
        viewModel.isEgoChecked.observe(viewLifecycleOwner,::onEgoCheckedChanged)
    }
    private fun observeAreSwitchesClickable() {
        Log.e("EGO","HomeFragment-observeAreSwitchesClickable")
        viewModel.areSwitchesClickable.observe(viewLifecycleOwner, ::setSwitchesClickable)
    }
    private fun observeAreSwitchesChecked() {
        Log.e("EGO","HomeFragment-observeAreSwitchesChecked")
        viewModel.areSwitchesChecked.observe(viewLifecycleOwner, ::setSwitchesChecked)
    }
    private fun observeNavigationItems() {
        viewModel.navigationItems.observe(viewLifecycleOwner, ::updateBottomNavigationView)
    }
    private fun observeMaxItemsReachedMessage() {
        viewModel.maxItemsReachedMessage.observe(viewLifecycleOwner, ::onMaxItemsReachedChanged)
    }

    private fun observeViewModel() {
        Log.e("EGO","HomeFragment-observeViewModel")
        observeisEgoChecked()
        observeAreSwitchesClickable()
        observeAreSwitchesChecked()
        observeNavigationItems()
        observeMaxItemsReachedMessage()
    }

    private fun setupSwitchEgoListener() {
        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
            Log.e("EGO","HomeFragment-setupSwitchEgoListener isChecked: $isChecked")
            if (isChecked){
                setSwitchesClickable(false)
                setSwitchesChecked(false)
                updateBottomNavigationView()
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
            Log.e("EGO","HomeFragment-setupSwitchRespectListener isChecked: $isChecked")
        }
    }

    private fun setupSwitchListeners() {
        Log.e("EGO","HomeFragment-setupSwitchListeners")
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
            Log.e("EGO","HomeFragment-setSwitchesClickable isClickable: $isClickable")
        }
    }

    private fun setSwitchesChecked(isChecked: Boolean) {
        with(binding) {
            switchHappiness.isChecked = isChecked
            switchOptimism.isChecked = isChecked
            switchKindness.isChecked = isChecked
            switchGiving.isChecked = isChecked
            switchRespect.isChecked = isChecked
            Log.e("EGO","HomeFragment-setSwitchesChecked isChecked: $isChecked")
        }
    }
    private fun updateBottomNavigationView(items: List<Int> = emptyList()) {
        val mainActivity = activity as? MainActivity
        mainActivity?.toggleBottomNavigationView(items)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("EGO","HomeFragment-onDestroyView")
        _binding = null
    }
}