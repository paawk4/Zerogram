package com.example.zerogram.utilities

import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val READ_CONTACTS = android.Manifest.permission.READ_CONTACTS
const val READ_IMAGES = android.Manifest.permission.READ_MEDIA_IMAGES
const val READ_VIDEO = android.Manifest.permission.READ_MEDIA_VIDEO
const val READ_AUDIO = android.Manifest.permission.READ_MEDIA_AUDIO
const val READ_STORAGE = android.Manifest.permission.READ_EXTERNAL_STORAGE
const val PERMISSION_REQUEST = 200

fun checkPermission(permission: String): Boolean {
    return if (Build.VERSION.SDK_INT >= 23
        && ContextCompat.checkSelfPermission(APP_ACTIVITY, permission) != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(APP_ACTIVITY, arrayOf(permission), PERMISSION_REQUEST)
        false
    }else true
}