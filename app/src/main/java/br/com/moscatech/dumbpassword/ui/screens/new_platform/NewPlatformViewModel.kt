package br.com.moscatech.dumbpassword.ui.screens.new_platform

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.moscatech.dumbpassword.base.BaseViewModel
import br.com.moscatech.dumbpassword.domain.use_case.PlatformUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPlatformViewModel @Inject constructor(
    private val platformUseCase: PlatformUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _platformSaved: MutableLiveData<String> = MutableLiveData()
    val platformSaved: LiveData<String> get() = _platformSaved

    private val platformArg: String = savedStateHandle["platform"] ?: ""

    private val _platformName: MutableLiveData<String> = MutableLiveData()
    val platformName: LiveData<String> get() = _platformName

    private val isEditMode = platformArg.isNotEmpty()

    init {
        _platformName.postValue(platformArg)
    }

    fun onEditorActionListener(view: TextView, actionId: Int): Boolean {
        return if (actionId == EditorInfo.IME_ACTION_SEND) {
            val platformName = view.text.toString()
            viewModelScope.launch {
                if (isEditMode) editPlatform(platformName)
                else savePlatform(platformName)
                _platformSaved.postValue(platformName)
            }
            true
        } else false
    }

    private suspend fun savePlatform(platformName: String) {
        platformUseCase.savePlatform(platformName)
    }

    private suspend fun editPlatform(platformName: String) {
        platformUseCase.editPlatform(platformArg, platformName)
    }
}