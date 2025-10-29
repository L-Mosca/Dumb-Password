package br.com.moscatech.dumbpassword.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(direction: NavDirections) {
    findNavController().navigate(direction)
}

fun Fragment.navigate(direction: NavDirections, navOptions: NavOptions) {
    findNavController().navigate(direction, navOptions)
}

fun Fragment.popBackStack() {
    findNavController().popBackStack()
}