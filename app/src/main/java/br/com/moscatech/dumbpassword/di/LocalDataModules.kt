package br.com.moscatech.dumbpassword.di

import android.content.Context
import br.com.moscatech.dumbpassword.data.local.preferences.PreferencesContract
import br.com.moscatech.dumbpassword.data.local.preferences.PreferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModules {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): PreferencesContract {
        return PreferencesDataStore(context)
    }
}