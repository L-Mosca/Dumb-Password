package br.com.moscatech.dumbpassword.ui.screens.new_group

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.moscatech.dumbpassword.base.BaseViewModel
import br.com.moscatech.dumbpassword.domain.use_case.GroupsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewGroupViewModel @Inject constructor(
    private val groupUseCase: GroupsUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _groupSaved: MutableLiveData<String> = MutableLiveData()
    val groupSaved: LiveData<String> get() = _groupSaved

    private val groupArg: String = savedStateHandle["group"] ?: ""

    private val _groupName: MutableLiveData<String> = MutableLiveData()
    val groupName: LiveData<String> get() = _groupName

    private val isEditMode = groupArg.isNotEmpty()

    init {
        _groupName.postValue(groupArg)
    }

    fun onEditorActionListener(view: TextView, actionId: Int): Boolean {
        return if (actionId == EditorInfo.IME_ACTION_SEND) {
            val groupName = view.text.toString()
            viewModelScope.launch {
                if (isEditMode) editGroup(groupName)
                else saveGroup(groupName)
                _groupSaved.postValue(groupName)
            }
            true
        } else false
    }

    private suspend fun saveGroup(groupName: String) {
        groupUseCase.saveGroup(groupName)
    }

    private suspend fun editGroup(groupName: String) {
        groupUseCase.editGroup(groupArg, groupName)
    }
}