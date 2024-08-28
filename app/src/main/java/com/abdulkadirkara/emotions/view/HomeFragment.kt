package com.abdulkadirkara.emotions.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdulkadirkara.emotions.R
import com.abdulkadirkara.emotions.databinding.FragmentHomeBinding
import com.abdulkadirkara.emotions.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment() {
    // View'u saklayacağımız bir alan tanımlıyoruz
    private var rootView: View? = null

    private var viewModel = HomeFragmentViewModel()
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
            Log.e("EGO", "homefragment-onCreateView: Yeni View oluşturuldu")
        } else {
            Log.e("EGO", "homefragment-onCreateView: Eski View kullanıldı")
        }
        // rootView'u geri döndürüyoruz
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        // Switch ve BottomNavigationView durumlarını yeniden yükle
        viewModel.isEgoChecked.value?.let { isEgoChecked ->
            updateOtherSwitchesBasedOnEgo(isEgoChecked)
            Log.e("EGO","homefragment-onResume updateOtherSwitchesBasedOnEgo cagrım sonu")
        }
        viewModel.navigationItems.value?.let { updateBottomNavigationView(it) }
        Log.e("EGO","homefragment-onResume updateBottomNavigationView cagrım sonu")
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
        Log.e("EGO","homefragment-setupSwitches")
    }

    private fun observeViewModel() {
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
            setSwitchesChecked(areChecked)
            Log.e("EGO","homefragment-observeViewModel areSwitchesChecked")
        })
        viewModel.navigationItems.observe(viewLifecycleOwner, Observer { items ->
            updateBottomNavigationView(items)
            Log.e("EGO","homefragment-observeViewModel navigationItems")
        })

        // Maksimum öğe sayısına ulaşıldığında mesaj göster
        viewModel.maxItemsReachedMessage.observe(viewLifecycleOwner, { isMaxReached ->
            if (isMaxReached) {
                Toast.makeText(requireActivity(), "Maksimum 5 item ekleyebilirsiniz.", Toast.LENGTH_SHORT).show()
                viewModel.resetMaxItemsReachedMessage() // Mesajı sıfırla
                Log.e("EGO","homefragment-observeViewModel maxItemsReachedMessage reset")
            }
            Log.e("EGO","homefragment-observeViewModel maxItemsReachedMessage")
        })
    }

    private fun setupSwitchListeners() {
        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEgoSwitchChanged(isChecked)
            // switchEgo değiştiğinde diğer switch'lerin durumunu güncelle
            updateOtherSwitchesBasedOnEgo(isChecked)
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
    private fun updateOtherSwitchesBasedOnEgo(isEgoChecked: Boolean) {
        with(binding) {
            val switches = listOf(switchHappiness, switchOptimism, switchKindness, switchGiving, switchRespect)
            switches.forEach { switch ->
                if (isEgoChecked) {
                    // Eğer switchEgo açık ise, diğer switch'leri kontrol et
                    Log.e("EGO","homefragment-updateOtherSwitchesBasedOnEgo switchEgo açık")
                    if (switch.isChecked) {
                        Log.e("EGO","homefragment-updateOtherSwitchesBasedOnEgo $switch isChecked true -> flase")
                        switch.isChecked = false
                        viewModel.onOtherSwitchChanged(switch.id, false)
                    }
                } else {
                    // Eğer switchEgo kapalı ise, diğer switch'lerin durumunu güncelle
                    Log.e("EGO","homefragment-updateOtherSwitchesBasedOnEgo switchEgo kapalı")
                    if (!switch.isChecked) {
                        Log.e("EGO","homefragment-updateOtherSwitchesBasedOnEgo $switches isChecked false -> true")
                        viewModel.onOtherSwitchChanged(switch.id, false)
                    }
                }
            }
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
        Log.e("EGO","homefragment-setSwitchesClickable")
    }

    private fun setSwitchesChecked(isChecked: Boolean) {
        with(binding) {
            switchHappiness.isChecked = isChecked
            switchOptimism.isChecked = isChecked
            switchKindness.isChecked = isChecked
            switchGiving.isChecked = isChecked
            switchRespect.isChecked = isChecked
        }
        Log.e("EGO","homefragment-setSwitchesChecked")
    }
    private fun updateBottomNavigationView(items: List<Int> = emptyList()) {
        val mainActivity = activity as? MainActivity
        mainActivity?.toggleBottomNavigationView(items)
        Log.e("EGO","homefragment-updateBottomNavigationView")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("EGO","homefragment-onDestroyView bası")
        _binding = null
    }
}