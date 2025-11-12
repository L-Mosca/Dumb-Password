package br.com.moscatech.dumbpassword.ui.screens.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.moscatech.dumbpassword.base.BaseViewModel
import br.com.moscatech.dumbpassword.domain.use_case.PlatformUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlatformViewModel @Inject constructor(
    private val platformUseCase: PlatformUseCase,
) : BaseViewModel() {

    private val _platformList = MutableLiveData<List<String>>()
    val platformList: LiveData<List<String>> get() = _platformList

    private val _showEditDialog = MutableLiveData<Pair<Int, String>>()
    val showEditDialog: LiveData<Pair<Int, String>> get() = _showEditDialog

    init {
        getPlatforms()
    }

    fun getPlatforms() {
        viewModelScope.launch {
            _platformList.postValue(platformUseCase.getPlatforms())
        }
    }

    fun onEditClicked(group: String, position: Int) {
        _showEditDialog.postValue(Pair(position, group))
    }

    fun onDeleteClicked(group: String) {
        viewModelScope.launch {
            val list = _platformList.value?.toMutableList() ?: mutableListOf()
            list.remove(group)
            platformUseCase.deletePlatform(list)
            _platformList.postValue(list)
        }
    }
}