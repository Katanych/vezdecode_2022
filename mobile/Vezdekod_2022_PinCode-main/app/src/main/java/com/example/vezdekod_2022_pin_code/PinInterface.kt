package com.example.vezdekod_2022_pin_code

interface PinInterface {
    fun setPin(pin: String)
    fun delete()
    fun getPin(callback: (String) -> Unit)
}