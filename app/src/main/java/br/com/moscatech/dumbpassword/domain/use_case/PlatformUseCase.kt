package br.com.moscatech.dumbpassword.domain.use_case

import br.com.moscatech.dumbpassword.data.local.preferences.PreferencesContract
import br.com.moscatech.dumbpassword.domain.model.exceptions.DuplicatedPlatformException
import javax.inject.Inject

class PlatformUseCase @Inject constructor(private val preferences: PreferencesContract) {

    suspend fun savePlatform(platform: String) {
        val savedPlatforms = getPlatforms().toMutableList()
        val newPlatform = platform.trim()

        if (savedPlatforms.contains(newPlatform)) throw DuplicatedPlatformException()

        savedPlatforms.add(newPlatform)
        preferences.savePlatform(savedPlatforms.sortedBy { it })
    }

    suspend fun getPlatforms(): List<String> {
        return preferences.getPlatforms()
    }

    suspend fun deletePlatform(list: List<String>) {
        preferences.savePlatform(list)
    }

    suspend fun editPlatform(oldName: String, newName: String) {
        val list = getPlatforms().toMutableList()
        val index = list.indexOf(oldName)
        if (index != -1) {
            list[index] = newName
            preferences.savePlatform(list)
        }
    }
}