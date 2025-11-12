package br.com.moscatech.dumbpassword.domain.use_case

import br.com.moscatech.dumbpassword.data.local.preferences.PreferencesContract
import br.com.moscatech.dumbpassword.domain.model.exceptions.DuplicatedGroupException
import javax.inject.Inject


class GroupsUseCase @Inject constructor(private val preferences: PreferencesContract) {

    suspend fun saveGroup(group: String) {
        val savedGroups = getGroups().toMutableList()
        val newGroup = group.trim()

        if (savedGroups.contains(newGroup)) throw DuplicatedGroupException()

        savedGroups.add(newGroup)
        preferences.saveGroup(savedGroups.sortedBy { it })
    }

    suspend fun getGroups(): List<String> {
        return preferences.getGroups()
    }

    suspend fun deleteGroup(list: List<String>) {
        preferences.saveGroup(list)
    }

    suspend fun editGroup(oldName: String, newName: String) {
        val list = getGroups().toMutableList()
        val index = list.indexOf(oldName)
        if (index != -1) {
            list[index] = newName
            preferences.saveGroup(list)
        }
    }
}