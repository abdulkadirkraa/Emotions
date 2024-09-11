package com.abdulkadirkara.emotions.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abdulkadirkara.emotions.databinding.FragmentHomeBinding
import com.abdulkadirkara.emotions.viewmodel.HomeFragmentViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var viewModel: HomeFragmentViewModel

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    var activeSwitches = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HomeFragmentViewModel::class.java]

        checkedChangeListener()

    }

    private fun checkedChangeListener(){
        with(binding) {
            switchHappiness.setOnCheckedChangeListener { _, isChecked ->
                handleSwitchChange(isChecked, "Happiness", switchHappiness)
            }

            switchKindness.setOnCheckedChangeListener { _, isChecked ->
                handleSwitchChange(isChecked, "Kindness", switchKindness)
            }

            switchOptimism.setOnCheckedChangeListener { _, isChecked ->
                handleSwitchChange(isChecked, "Optimism", switchOptimism)
            }

            switchGiving.setOnCheckedChangeListener { _, isChecked ->
                handleSwitchChange(isChecked, "Giving", switchGiving)
            }

            switchRespect.setOnCheckedChangeListener { _, isChecked ->
                handleSwitchChange(isChecked, "Respect", switchRespect)
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

    // Her switch için kontrol: Eğer 4 switch açıksa, Toast göster ve durumu açtırma
    private fun handleSwitchChange(isChecked: Boolean, switchName: String, @SuppressLint("UseSwitchCompatOrMaterialCode") switchView: Switch) {
        if (isChecked && getActiveSwitchCount() >= 5) {
            // Maksimum limit mesajı göster ve switch'i tekrar kapalı yap
            Toast.makeText(requireContext(), "Maksimum 4 switch açılabilir", Toast.LENGTH_SHORT).show()
            switchView.isChecked = false
        } else {
            // Switch durumu ViewModel'e kaydet (opsiyonel)
            when (switchName) {
                "Happiness" -> viewModel.setSwitchHappinessState(isChecked)
                "Kindness" -> viewModel.setSwitchKindnessState(isChecked)
                "Optimism" -> viewModel.setSwitchOptimismState(isChecked)
                "Giving" -> viewModel.setSwitchGivingState(isChecked)
                "Respect" -> viewModel.setSwitchRespectState(isChecked)
            }
        }
    }

    // Aktif olan (checked=true) switch'leri say
    private fun getActiveSwitchCount(): Int {
        return with(binding) {
            var count = 0
            if (switchHappiness.isChecked) count++
            if (switchKindness.isChecked) count++
            if (switchOptimism.isChecked) count++
            if (switchGiving.isChecked) count++
            if (switchRespect.isChecked) count++
            count
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
}