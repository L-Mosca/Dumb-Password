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

    private val _showEditDialog = MutableLiveData<Pair<Int, String>>()
    val showEditDialog: LiveData<Pair<Int, String>> get() = _showEditDialog

    init {
        getGroups()
    }

    fun getGroups() {
        viewModelScope.launch {
            _groupList.postValue(groupUseCase.getGroups())
        }
    }

    fun onEditClicked(group: String, position: Int) {
        _showEditDialog.postValue(Pair(position, group))
    }

    fun onDeleteClicked(group: String) {
        viewModelScope.launch {
            val list = _groupList.value?.toMutableList() ?: mutableListOf()
            list.remove(group)
            groupUseCase.deleteGroup(list)
            _groupList.postValue(list)
        }
    }
}