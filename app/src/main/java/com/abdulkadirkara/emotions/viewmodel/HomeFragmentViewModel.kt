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
        Log.e("EGO", "HomeFragmentViewModel-init")
    }

    fun onEgoSwitchChanged(isChecked: Boolean) {
        _isEgoChecked.value = isChecked
        Log.e("EGO", "HomeFragmentViewModel-onEgoSwitchChanged isChecked: $isChecked")
        if (isChecked) {
            handleEgoSwitchChecked()
        } else {
            handleEgoSwitchUnchecked()
        }
    }

    fun onOtherSwitchChanged(switchId: Int, isChecked: Boolean) {
        val items = _navigationItems.value ?: mutableListOf()
        Log.e("EGO", "HomeFragmentViewModel-onOtherSwitchChanged switchId: $switchId isChecked: $isChecked")
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
    private fun handleEgoSwitchChecked() {
        Log.e("EGO", "HomeFragmentViewModel-handleEgoSwitchChecked")
        _areSwitchesClickable.value = false
        _areSwitchesChecked.value = false
        clearNavigationItems()
    }

    private fun handleEgoSwitchUnchecked() {
        Log.e("EGO", "HomeFragmentViewModel-handleEgoSwitchUnchecked")
        _areSwitchesClickable.value = true
        setHomeFragmentAsDefaultNavigationItem()
    }

    private fun clearNavigationItems() {
        _navigationItems.value?.clear()
    }

    private fun setHomeFragmentAsDefaultNavigationItem() {
        _navigationItems.value = mutableListOf(R.id.homeFragment)
    }
    private fun handleSwitchChecked(switchId: Int, items: MutableList<Int>) {
        Log.e("EGO", "HomeFragmentViewModel-handleSwitchChecked switchId: $switchId , items.size: ${items.size}")
        if (!items.contains(switchId) && items.size < 5) {
            items.add(switchId)
        } else if (items.size >= 5) {
            _maxItemsReachedMessage.value = true
        }
    }
    private fun handleSwitchUnchecked(switchId: Int, items: MutableList<Int>) {
        Log.e("EGO", "HomeFragmentViewModel-handleSwitchUnchecked switchId: $switchId , items.size: ${items.size}")
        items.remove(switchId)
    }
}