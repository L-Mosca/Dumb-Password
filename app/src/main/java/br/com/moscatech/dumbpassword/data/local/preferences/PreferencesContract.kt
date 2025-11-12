package br.com.moscatech.dumbpassword.data.local.preferences

interface PreferencesContract {

    suspend fun saveGroup(newGroups: List<String>)
    suspend fun getGroups(): List<String>

    suspend fun savePlatform(newPlatforms: List<String>)
    suspend fun  getPlatforms(): List<String>
}