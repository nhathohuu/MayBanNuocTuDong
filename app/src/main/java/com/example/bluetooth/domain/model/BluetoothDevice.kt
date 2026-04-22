package com.example.bluetooth.domain.model

typealias BluetoothDeviceDomain = BluetoothDevice

data class BluetoothDevice(
    val name:String?,
    val address:String
)
