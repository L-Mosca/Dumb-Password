package br.com.moscatech.dumbpassword.ui.screens.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.moscatech.dumbpassword.base.BaseViewModel
import br.com.moscatech.dumbpassword.domain.use_case.GroupsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val groupUseCase: GroupsUseCase,
) : BaseViewModel() {

    private val _groupList = MutableLiveData<List<String>>()
    val groupList: LiveData<List<String>> get() = _groupList

    init {
        getGroups()
    }

    fun getGroups() {
        viewModelScope.launch {
            _groupList.postValue(groupUseCase.getGroups())
        }
    }
}