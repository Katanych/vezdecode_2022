package com.example.vezdekod_2022_pin_code.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vezdekod_2022_pin_code.PinInterface
import com.example.vezdekod_2022_pin_code.PinSharedPrefs

class PinViewModel(application: Application) : AndroidViewModel(application) {

    private val pinShared: PinInterface = PinSharedPrefs(application)

    private var _pinLiveData = MutableLiveData<String>()
    val pin: LiveData<String> = _pinLiveData

    fun setPin(pin: String, callback: (Boolean) -> Unit) {
        pinShared.getPin {
            if (pin.equals(it)) callback.invoke(true)
            else {
                pinShared.setPin(pin)
                _pinLiveData.postValue(pin)
                callback.invoke(false)
            }
        }
    }

    fun createPin(pin: String) {
        pinShared.setPin(pin)
        _pinLiveData.postValue(pin)
    }

    fun deletePin() {
        _pinLiveData.postValue("")
        pinShared.delete()
    }

    fun getPin() = pinShared.getPin { _pinLiveData.postValue(it) }
}