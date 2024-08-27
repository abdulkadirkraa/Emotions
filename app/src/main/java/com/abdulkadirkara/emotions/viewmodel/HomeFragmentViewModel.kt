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
        Log.e("EGO","viewmodel-init sonu")
    }

    fun onEgoSwitchChanged(isChecked: Boolean) {
        _isEgoChecked.value = isChecked
        Log.e("EGO","viewmodel-onEgoSwitchChanged başı")
        if (isChecked) {
            _areSwitchesClickable.value = false
            _areSwitchesChecked.value = false
            _navigationItems.value?.clear()
            Log.e("EGO","viewmodel-onEgoSwitchChanged isChecked true ise yapıldı")
        } else {
            _areSwitchesClickable.value = true
            _navigationItems.value = mutableListOf(R.id.homeFragment) // "Home" öğesini ekliyoruz.
            Log.e("EGO","viewmodel-onEgoSwitchChanged false ise yapıldı")
        }
        _navigationItems.value = _navigationItems.value
        Log.e("EGO","viewmodel-onEgoSwitchChanged sonu")
    }


    fun onOtherSwitchChanged(switchId: Int, isChecked: Boolean) {
        val items = _navigationItems.value ?: mutableListOf()
        Log.e("EGO","viewmodel-onOtherSwitchChanged başı")
        if (isChecked) {
            Log.e("EGO","viewmodel-onOtherSwitchChanged isCheckes true ise")
            if (!items.contains(switchId) && items.size < 5) {
                items.add(switchId)
                Log.e("EGO","viewmodel-onOtherSwitchChanged item\'ların size\'ı 5\'ten küçük ise itema eklendi")
            }else if (items.size >= 5) {
                _maxItemsReachedMessage.value = true
                Log.e("EGO","viewmodel-onOtherSwitchChanged item\'ların size\'ı 5\'ten büyük ise _maxItemsReachedMessage true")
            }
        } else {
            items.remove(switchId)
            Log.e("EGO","viewmodel-onOtherSwitchChanged isChecked false ise item remove")
        }
        _navigationItems.value = items
        Log.e("EGO","viewmodel-onOtherSwitchChanged sonu")
    }
    fun resetMaxItemsReachedMessage() {
        _maxItemsReachedMessage.value = false
        Log.e("EGO","viewmodel-resetMaxItemsReachedMessage")
    }
}