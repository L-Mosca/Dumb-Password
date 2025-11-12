package br.com.moscatech.dumbpassword.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import br.com.moscatech.dumbpassword.BuildConfig
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataStore @Inject constructor(@ApplicationContext context: Context) :
    PreferencesContract {

    companion object {
        private const val NAME = "${BuildConfig.VERSION_NAME}.Preferences.${BuildConfig.BUILD_TYPE}"

        private val groups = stringPreferencesKey(name = "$NAME.Groups")
        private val platforms = stringPreferencesKey(name = "$NAME.Platforms")
    }

    private val Context.dataStore by preferencesDataStore(name = NAME)
    private val dataStore = context.dataStore

    override suspend fun saveGroup(newGroups: List<String>) {
        dataStore.edit { pref ->
            pref[groups] = Gson().toJson(newGroups)
        }
    }

    override suspend fun getGroups(): List<String> {
        return dataStore.getData<List<String>>(groups) ?: emptyList()
    }

    override suspend fun savePlatform(newPlatforms: List<String>) {
        dataStore.edit { pref ->
            pref[platforms] = Gson().toJson(newPlatforms)
        }
    }

    override suspend fun getPlatforms(): List<String> {
        return dataStore.getData<List<String>>(platforms) ?: emptyList()
    }

    internal suspend inline fun <reified T> DataStore<Preferences>.getData(key: Preferences.Key<String>): T? {
        return runCatching {
            this.data.catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }.map { pref ->
                val data = pref[key]
                if (data.isNullOrEmpty()) return@map null
                else Gson().fromJson(data, T::class.java)
            }.first()
        }.getOrNull()
    }
}