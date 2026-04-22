package com.example.bluetooth.domain.controller

import com.example.bluetooth.domain.model.BluetoothDevice
import com.example.bluetooth.domain.model.ConnectionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val scannedDevice : StateFlow<List<BluetoothDevice>>
    val pairedDevice : StateFlow<List<BluetoothDevice>>
    val incomingMessages: SharedFlow<String>

    fun startDiscovery()
    fun stopDiscovery()

    fun startBluetoothServer(): Flow<ConnectionResult>

    fun connectToDevice(device: BluetoothDevice): Flow<ConnectionResult>

    fun closeConnection()

    fun release()
}