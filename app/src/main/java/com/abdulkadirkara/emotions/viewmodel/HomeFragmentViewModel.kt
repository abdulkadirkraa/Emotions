package com.abdulkadirkara.emotions.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdulkadirkara.emotions.R

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

    // Mesaj için LiveData
    private val _maxItemsReachedMessage = MutableLiveData<Boolean>()
    val maxItemsReachedMessage: LiveData<Boolean> get() = _maxItemsReachedMessage

    init {
        _isEgoChecked.value = true
        _areSwitchesClickable.value = false
        _areSwitchesChecked.value = false
        _maxItemsReachedMessage.value = false
    }

    fun onEgoSwitchChanged(isChecked: Boolean) {
        _isEgoChecked.value = isChecked
        if (isChecked) {
            handleEgoSwitchChecked()
        } else {
            handleEgoSwitchUnchecked()
        }
    }

    fun onOtherSwitchChanged(switchId: Int, isChecked: Boolean) {
        val items = _navigationItems.value ?: mutableListOf()
        if (isChecked) {
            handleSwitchChecked(switchId, items)
        } else {
            handleSwitchUnchecked(switchId, items)
        }
        _navigationItems.value = items
    }

    fun resetMaxItemsReachedMessage() {
        _maxItemsReachedMessage.value = false
    }
    fun handleEgoSwitchChecked() {
        _areSwitchesClickable.value = false
        _areSwitchesChecked.value = false
        clearNavigationItems()
    }

    fun handleEgoSwitchUnchecked() {
        _areSwitchesClickable.value = true
        setHomeFragmentAsDefaultNavigationItem()
    }

    fun clearNavigationItems() {
        _navigationItems.value?.clear()
    }

    fun setHomeFragmentAsDefaultNavigationItem() {
        _navigationItems.value = mutableListOf(R.id.homeFragment)
    }
    fun handleSwitchChecked(switchId: Int, items: MutableList<Int>) {
        if (!items.contains(switchId) && items.size < 5) {
            items.add(switchId)
        } else if (items.size >= 5) {
            _maxItemsReachedMessage.value = true
        }
    }
    fun handleSwitchUnchecked(switchId: Int, items: MutableList<Int>) {
        items.remove(switchId)
    }
}