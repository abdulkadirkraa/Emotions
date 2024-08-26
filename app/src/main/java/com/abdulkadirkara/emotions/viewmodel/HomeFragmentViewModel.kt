package com.abdulkadirkara.emotions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeFragmentViewModel: ViewModel() {

    // Ego switch'in durumu
    private val _isEgoChecked = MutableLiveData<Boolean>()
    val isEgoChecked: LiveData<Boolean> get() = _isEgoChecked

    // Diğer switchlerin tıklanabilirlik ve durum bilgisi
    private val _areSwitchesClickable = MutableLiveData<Boolean>()
    val areSwitchesClickable: LiveData<Boolean> get() = _areSwitchesClickable

    private val _areSwitchesChecked = MutableLiveData<Boolean>()
    val areSwitchesChecked: LiveData<Boolean> get() = _areSwitchesChecked

    private val _navigationItems = MutableLiveData<MutableList<Int>>(mutableListOf())
    val navigationItems: LiveData<MutableList<Int>> get() = _navigationItems

    init {
        _isEgoChecked.value = true
        _areSwitchesClickable.value = false
        _areSwitchesChecked.value = false
    }

    fun onEgoSwitchChanged(isChecked: Boolean) {
        _isEgoChecked.value = isChecked
        if (isChecked) {
            _areSwitchesClickable.value = false
            _areSwitchesChecked.value = false
            _navigationItems.value?.clear()
        } else {
            _areSwitchesClickable.value = true
        }
        _navigationItems.value = _navigationItems.value // Trigger observer
    }


    fun onOtherSwitchChanged(switchId: Int, isChecked: Boolean) {
        val items = _navigationItems.value ?: mutableListOf()
        if (isChecked) {
            if (!items.contains(switchId)) {
                items.add(switchId)
            }
        } else {
            items.remove(switchId)
        }
        _navigationItems.value = items
    }
}