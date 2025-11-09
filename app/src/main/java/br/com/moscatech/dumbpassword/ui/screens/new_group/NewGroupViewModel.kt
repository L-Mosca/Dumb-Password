package br.com.moscatech.dumbpassword.ui.screens.new_group

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.moscatech.dumbpassword.base.BaseViewModel
import br.com.moscatech.dumbpassword.domain.use_case.GroupsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewGroupViewModel @Inject constructor(
    private val groupUseCase: GroupsUseCase,
) : BaseViewModel() {

    private val _groupSaved: MutableLiveData<String> = MutableLiveData()
    val groupSaved: LiveData<String> get() = _groupSaved

    fun onEditorActionListener(view: TextView, actionId: Int): Boolean {
        return if (actionId == EditorInfo.IME_ACTION_SEND) {
            saveGroup(view.text.toString())
            true
        } else false
    }

    private fun saveGroup(groupName: String) {
        viewModelScope.launch {
            groupUseCase.saveGroup(groupName)
            _groupSaved.postValue(groupName)
        }

    }
}