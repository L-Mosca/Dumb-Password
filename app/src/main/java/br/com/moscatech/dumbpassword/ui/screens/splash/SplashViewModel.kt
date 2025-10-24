package br.com.moscatech.dumbpassword.ui.screens.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.moscatech.dumbpassword.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _showHome: LiveData<Unit> = MutableLiveData<Unit>()
    val showHome: LiveData<Unit> get() = _showHome


    init {
        showHome()
    }

    private fun showHome() {
        Handler(Looper.getMainLooper()).postDelayed({
            _showHome.post(Unit)
        }, 2000)
    }
}