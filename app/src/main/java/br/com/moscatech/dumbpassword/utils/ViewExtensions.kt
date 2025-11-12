package br.com.moscatech.dumbpassword.utils

import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

fun View.showKeyboard(window: Window) {
    WindowCompat.getInsetsController(window, this).show(WindowInsetsCompat.Type.ime())
}