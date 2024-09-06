package com.abdulkadirkara.emotions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeFragmentViewModel: ViewModel() {
    private val _isEgoState = MutableLiveData<Boolean>()
    val isEgoState: LiveData<Boolean> get() = _isEgoState

    private val _isHappinessState = MutableLiveData<Boolean>()
    val isHappinessState: LiveData<Boolean> get() = _isHappinessState

    private val _isOptimismState = MutableLiveData<Boolean>()
    val isOptimismState: LiveData<Boolean> get() = _isOptimismState

    private val _isKindnessState = MutableLiveData<Boolean>()
    val isKindnessState: LiveData<Boolean> get() = _isKindnessState

    private val _isGivingState = MutableLiveData<Boolean>()
    val isGivingState: LiveData<Boolean> get() = _isGivingState

    private val _isRespectState = MutableLiveData<Boolean>()
    val isRespectState: LiveData<Boolean> get() = _isRespectState

    init {
        _isEgoState.value = true
        _isHappinessState.value = false
        _isOptimismState.value = false
        _isKindnessState.value = false
        _isGivingState.value = false
        _isRespectState.value = false
    }

    fun setSwitchEgoState(state: Boolean) {
        _isEgoState.value = state
        if (state) {
            // Ego açıkken diğer anahtarları kapatıyoruz
            setAllSwitchesState(false)
        }
    }

    private fun setAllSwitchesState(state: Boolean) {
        _isHappinessState.value = state
        _isOptimismState.value = state
        _isKindnessState.value = state
        _isGivingState.value = state
        _isRespectState.value = state
    }


    fun setSwitchHappinessState(state: Boolean) {
        _isHappinessState.value = state
    }

    fun setSwitchKindnessState(state: Boolean) {
        _isKindnessState.value = state
    }

    fun setSwitchOptimismState(state: Boolean) {
        _isOptimismState.value = state
    }

    fun setSwitchRespectState(state: Boolean) {
        _isRespectState.value = state
    }

    fun setSwitchGivingState(state: Boolean) {
        _isGivingState.value = state
    }
}