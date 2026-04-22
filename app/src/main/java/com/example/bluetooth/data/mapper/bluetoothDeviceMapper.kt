package com.example.bluetooth.data.mapper

import android.Manifest
import android.bluetooth.BluetoothDevice
import androidx.annotation.RequiresPermission
import com.example.bluetooth.domain.model.BluetoothDeviceDomain

@RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return com.example.bluetooth.domain.model.BluetoothDeviceDomain(
        name = name,
        address = address
    )
}